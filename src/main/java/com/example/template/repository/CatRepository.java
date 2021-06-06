package com.example.template.repository;


import com.example.template.models.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CatRepository extends JpaRepository<Cat, UUID> { }
