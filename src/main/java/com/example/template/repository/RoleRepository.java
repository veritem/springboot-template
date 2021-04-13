package com.example.template.repository;

import com.example.template.enums.ERoleType;
import com.example.template.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByName(ERoleType roleType);
}
