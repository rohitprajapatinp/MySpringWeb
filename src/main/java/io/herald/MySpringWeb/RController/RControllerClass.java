package io.herald.MySpringWeb.RController;

import io.herald.MySpringWeb.Component.JwtUtil;
import io.herald.MySpringWeb.Model.UserTable;
import io.herald.MySpringWeb.Repository.ImageRepository;
import io.herald.MySpringWeb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RControllerClass {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/getAllUsers")
    public List<UserTable> getAllUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/saveUser")
    public String saveUser(@RequestBody UserTable user){
        // @RequestBody -> JSON ma data aako cha vane, requestbody lekhna parxa
        userRepository.save(user);
        return "Saved Successfully";
    }

    @GetMapping("getOne/{id}")
    public UserTable getOne(@PathVariable int id){
//        return userRepository.findById(id).get();
        UserTable u = userRepository.findById(id).get();
        return u;
    }

    @GetMapping("/getId/{id}")
    public ResponseEntity<?> getId(@PathVariable int id){
        if(userRepository.findById(id).isPresent()){
            return ResponseEntity.ok(userRepository.findById(id).get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID NOT FOUND");
    }

    @PostMapping("/login")
    public ResponseEntity<?> apiLogin(@RequestParam String username, @RequestParam String password){
        UserTable user = userRepository.findByUsername(username);
        if(user != null && passwordEncoder.matches(password, user.getPassword())){
            return ResponseEntity.ok(jwtUtil.generateToken(username));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
    }
}
