package com.example.template.controllers;

import com.example.template.dtos.UserUpdateDTO;
import com.example.template.models.User;
import com.example.template.payload.ApiResponse;
import com.example.template.services.UserService;
import com.example.template.util.Constants;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> getUsers(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                               @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size) {
        return userService.getAllUsers(page,size);
    }

    @DeleteMapping(path = "/{userID}")
    public ResponseEntity<ApiResponse> deleteUser(@ApiParam(value="delete user",required = true) @PathVariable("userID") UUID userID){
        return ResponseEntity.ok(new ApiResponse(true,"user removed successfully",userService.deleteUser(userID)));
    }

    @PutMapping(path = "/{userID}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable("userID") UUID userID,@Valid @RequestBody UserUpdateDTO userdataRequest){

        userService.updateUser(userID,userdataRequest);

        User user = userService.getUser(userID);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{email}")
                .buildAndExpand(user.getEmail()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true,"user  updated successfully",user));
    }


    @GetMapping(path = "/{userID}")
    public ResponseEntity<ApiResponse> getUser(@ApiParam(value="Get user by id",required = true) @PathVariable("userID") UUID userID){
        return  ResponseEntity.ok(new ApiResponse(true,"user found",this.userService.getUser(userID)));
    }


}
