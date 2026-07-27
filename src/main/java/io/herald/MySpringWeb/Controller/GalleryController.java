package io.herald.MySpringWeb.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GalleryController {
    @GetMapping("/gallery")
    public String galleryGet(HttpServletRequest req, Model m){
        HttpSession session = req.getSession();
        if (session.getAttribute("username")==null){
            m.addAttribute("message", "You are not logged in!");
            return "login";
        }
        return "galleryPage";
    }
}
