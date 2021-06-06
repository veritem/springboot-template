package com.example.template.services;

import com.example.template.dtos.UserUpdateDTO;
import com.example.template.exceptions.ApiRequestException;
import com.example.template.exceptions.AppException;
import com.example.template.exceptions.ResourceNotFoundException;
import com.example.template.models.User;
import com.example.template.repository.RoleRepository;
import com.example.template.repository.UserRepository;
import com.example.template.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User getUser(UUID userID) {
        return this.userRepository.findById(userID).orElseThrow(()-> new ApiRequestException("User with id "+userID+" not found"));
    }

    public User deleteUser(UUID userID) {
        User user = userRepository.findById(userID).orElseThrow(() -> new AppException("user with id "+userID+ " not found"));
        this.userRepository.deleteById(userID);
        return user;
    }

    public Page<User> getAllUsers(int page, int size) {
        Constants.validatePageNumberAndSize(page,size);
        Pageable pageable = (Pageable) PageRequest.of(page,size, Sort.Direction.ASC,"firstName");
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }



    public User getLoggedInUser(){
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails){
            email = ((UserDetails) principal).getUsername();
        }else{
            email = principal.toString();
        }
        User findByEmail = userRepository.findByEmail(email).orElseThrow(() -> new ApiRequestException("User not found"));

        return findByEmail;
    }

    public User updateUser(UUID userID, UserUpdateDTO userdataRequest) {
        User user = userRepository.findById(userID).orElseThrow(()-> new ResourceNotFoundException("Get user by id", ""+ userID,new User()));

        if(getLoggedInUser().getId() != user.getId())
            throw new AppException("You are not authorized to update user");

        if(userRepository.existsByMobile(userdataRequest.getMobile()) && !(userdataRequest.getMobile().equalsIgnoreCase(user.getMobile()))){
            throw  new AppException("Phone number already in use.");
        }else {
            user.setMobile(userdataRequest.getMobile());
        }


        user.setFirstName(userdataRequest.getFirstName());
        user.setLastName(userdataRequest.getLastName());
        user.setGender(userdataRequest.getGender());
        user.setEmail(userdataRequest.getEmail());


        userRepository.save(user);

        return user;
    }
}
