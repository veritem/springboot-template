package com.example.template.cat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cat {

    @Id
    @SequenceGenerator(
            name = "cat_sequence",
            sequenceName = "cat_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cat_sequence"
    )
    private Long id;
    private String name;
    private String breed;
    private boolean isVaccinated;


    public Cat(String name, String breed, boolean isVaccinated) {
        this.name = name;
        this.breed = breed;
        this.isVaccinated = isVaccinated;
    }


}

