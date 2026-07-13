package io.herald.MySpringWeb.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
// Controller handles the http requests and responses
public class SignupController {
    @GetMapping("/signup")
    public String signUp(){
        return "signup.html";
    }

    @PostMapping("/signup")
    public String postSignup(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);

        return null;
    }
}
