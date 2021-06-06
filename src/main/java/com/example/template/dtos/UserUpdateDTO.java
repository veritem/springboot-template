package com.example.template.dtos;


import com.example.template.enums.EGender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserUpdateDTO {
    @Size(max = 100)
    @Email
    private  String email;

    @NotBlank
    @Size(min = 4, max = 40)
    private  String firstName;

    @NotBlank
    @Size(min = 4, max = 40)
    private  String lastName;

    @NotBlank
    @Size(min = 10, max = 12)
    @Pattern(regexp = "[0-9]{12}")
    private  String mobile;


    private EGender gender;
}
