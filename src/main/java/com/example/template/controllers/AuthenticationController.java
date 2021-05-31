package com.example.template.controllers;

import com.example.template.util.JwtUtil;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth")
@NoArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(path="register")
    public String register(){
        return "Register";
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return "Confirm";
    }

    @GetMapping(path = "current_user")
    public String getCurrentlyLoggedInUser(){
        return "foo";
    }
}
