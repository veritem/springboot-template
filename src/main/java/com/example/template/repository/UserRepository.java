package com.example.template.repository;

import com.example.template.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailOrMobile(String email, String mobile);
    Optional<User> findById(UUID userID);
    Boolean existsByMobile(String mobile);
}
