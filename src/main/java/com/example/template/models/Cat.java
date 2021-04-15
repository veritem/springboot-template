package com.example.template.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "cat")
public class Cat {
private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Id
    public UUID getId() {
        return id;
    }
}
