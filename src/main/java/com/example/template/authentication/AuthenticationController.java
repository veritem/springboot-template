package com.example.template.authentication;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthenticationController {

    @PostMapping(path = "/login")
    public String register(){
        return "Hello world";
    }
}
