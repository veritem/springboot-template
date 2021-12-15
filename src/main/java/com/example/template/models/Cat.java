package com.example.template.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "cat")
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    public Cat(String name) {
        this.name = name;
    }
}
