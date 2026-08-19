package com.example.praca_magisterska.controller;

import com.example.praca_magisterska.model.User;
import com.example.praca_magisterska.repository.UserRespository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserRespository userRespository;
    private final PasswordEncoder passwordEncoder;

    public LoginController(UserRespository userRespository, PasswordEncoder passwordEncoder) {
        this.userRespository = userRespository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password) {

        User user = new User(
                null,
                username,
                passwordEncoder.encode(password)
        );

        userRespository.save(user);

        return "redirect:/login";
    }

}
