package com.example.template.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.template.models.User;
import com.example.template.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public UserDetails loadByUserId(UUID id){
        User user = userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("User not found with id: "+id));
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmailOrMobile(s,s).orElseThrow(()-> new UsernameNotFoundException("user not found with email or mobile of "+s));
        return UserPrincipal.create(user);
    }
}