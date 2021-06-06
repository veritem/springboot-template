package com.example.template.controllers;

import com.example.template.dtos.SignInDTO;
import com.example.template.dtos.SignUpDTO;
import com.example.template.exceptions.ApiRequestException;
import com.example.template.models.Role;
import com.example.template.models.User;
import com.example.template.payload.JwtAuthenticationResponse;
import com.example.template.repository.RoleRepository;
import com.example.template.repository.UserRepository;
import com.example.template.security.JwtTokenProvider;
import com.example.template.services.UserService;
import com.example.template.util.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.ZonedDateTime;
import java.util.Collections;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/signin")
    @ApiOperation(value = "signin into your account")
    public ResponseEntity<JwtAuthenticationResponse> signin(@Valid @RequestBody SignInDTO signInRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getEmail(),
                        signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = null;

        try{
            jwt = jwtTokenProvider.generateToken(authentication);
        }catch (Exception e){
            e.printStackTrace();
        }

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping(path = "/signup")
    @ApiOperation(value = "create new user", response = User.class)
    public ResponseEntity<Response> signup(@RequestBody @Valid SignUpDTO signUpRequest){

        User user = new User(signUpRequest.getEmail(),signUpRequest.getFirstName(),signUpRequest.getLastName(),signUpRequest.getMobile(),signUpRequest.getGender(),signUpRequest.getPassword());

        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if(user.getEmail() != null && userExists){
            throw new ApiRequestException("Email is already in use");
        }

        if(userRepository.existsByMobile(user.getMobile())){
            throw new ApiRequestException("Phone number already in use");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        Role userRole = roleRepository.findByName(signUpRequest.getRole()).orElseThrow(()-> new ApiRequestException("User Role not set"));

        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);

        return new ResponseEntity<>(new Response("Registered successfully", ZonedDateTime.now(), true), HttpStatus.CREATED);
    }
}
