package com.example.banking_system.Bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user){
        userRepository.save(user);
        return "User registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user){
        User existing = userRepository.findByUsername(user.getUsername())
        .orElseThrow(()-> new ResourceNotFoundException("User not found"));

        if(!existing.getPassword().equals(user.getPassword())){
            throw new IllegalArgumentException("Invalid password");
        }
        return jwtUtil.generateToken(user.getUsername());
    }
}
