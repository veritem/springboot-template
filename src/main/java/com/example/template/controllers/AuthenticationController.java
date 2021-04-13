package com.example.template.controllers;

import com.example.template.dtos.LoginResponse;
import com.example.template.util.JwtUtil;
import com.example.template.dtos.LoginRequest;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth")
@NoArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(path = "login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("Invalid Password or email "+e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new LoginResponse(jwt));
    }

    @PostMapping(path="register")
    public String register(){
        return "Register";
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return "Confirm";
    }

    @GetMapping(path = "current-user")
    public String getCurrentlyLoggedInUser(){
        return "foo";
    }

}
