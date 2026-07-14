package io.herald.MySpringWeb.Controller;

import io.herald.MySpringWeb.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // Handles HTTP requests like GET, POST, etc.
public class MappingClass {

    @Autowired
    private UserRepository uRepo;

    @GetMapping("/") // Url pattern fort mapping
    public String openFirstPage(){
        return "firstPage.html";
    }

    @GetMapping("/nextPage")
    public String openNextPage(){
        return "nextPage.html";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login.html";
    }

    @PostMapping("/login")
    public String loginPost(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String hashPassword = DigestUtils.md5DigestAsHex(password.getBytes());

        System.out.println(username);
        System.out.println(password);

        if(uRepo.existsByUsernameAndPassword(username,hashPassword)){
            return "home.html";
        }

        return "login.html";

        //When a form data does POST request, HttpServlet Request obtains those data as parameters in controller.
    }
}
