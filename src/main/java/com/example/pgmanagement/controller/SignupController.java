package com.example.pgmanagement.controller;

import com.example.pgmanagement.model.UserEntity;
import com.example.pgmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class SignupController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("signup")
    public String showSignupFrom(){
        return "signup";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password, Model model){

        System.out.println("Form submitted, user name: " + name + ", email: " + email);

        if (userService.existingUser(email)){
            model.addAttribute("message","Email is already registered");
            return "signup";
        }

        UserEntity user = new UserEntity();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        userService.saveUser(user);

        model.addAttribute("message","Registration successful please login.");
        return "signup";
    }
}
