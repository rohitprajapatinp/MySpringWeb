package io.herald.MySpringWeb.Controller;

import io.herald.MySpringWeb.Model.UserTable;
import io.herald.MySpringWeb.Repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
// Controller handles the http requests and responses
public class SignupController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    // Autowired annotation helps in dependency injection,
    // When Autowired is present, all the necessary dependency files are
    // provided to the autowired class
    // Also, new keyword is not required to satisfy the oop rule to
    // create an object.
    private UserRepository uRepo;



    @GetMapping("/signup")
    public String signUp(){
        return "signup.html";
    }

    @PostMapping("/signup")
    public String postSignup(HttpServletRequest request, Model m){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // MD5 Hashing - Crackable
        String hashPassword = DigestUtils.md5DigestAsHex(password.getBytes());

        UserTable uc = new UserTable();
        uc.setUsername(username);
        uc.setPassword(hashPassword);

        uRepo.save(uc);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Signup Successful!");
        message.setText("Welcome to the club:" + username + "!!!!!");
//        mailSender.send(message);

        System.out.println(username);
        System.out.println(password);

        // Model ko m bhanne object le message lera gako -> login.html lai
        // Message lai attribute bhanincha model ko bhasa ma

        // m.addAttribute(msgtitle, msg);
        m.addAttribute("signupSuccess","You have successfully signed up! Please Login!");
        return "login.html";
    }
}
