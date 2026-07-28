package io.herald.MySpringWeb.Controller;

import io.herald.MySpringWeb.Model.ImageTable;
import io.herald.MySpringWeb.Repository.ImageRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Controller
public class GalleryController {

    private ImageRepository imageRepo;
    @GetMapping("/gallery")
    public String galleryGet(HttpServletRequest req, Model m){
        HttpSession session = req.getSession();
        if (session.getAttribute("username")==null){
            m.addAttribute("message", "You are not logged in!");
            return "login";
        }
        return "galleryPage";
    }

    @PostMapping("/gallery")
    public String galleryPost(@RequestParam("image")MultipartFile image){
        try {
            byte[] imgBytes = image.getBytes();
            // We will use Base64 Encoder,
            // We will encode the byte information of file into string
            // To decode, we will again use the Base64 Decoder

            String imgString = Base64.getEncoder().encodeToString(imgBytes);
            ImageTable img = new ImageTable();
            img.setImage(imgString);
            imageRepo.save(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "galleryPage";
    }
}
