package com.example.template.dtos;

import com.example.template.enums.EGender;
import com.example.template.enums.ERoleType;
import com.example.template.security.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignUpDTO {

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
    private  String mobile;


    private EGender gender;
    private ERoleType role;


    @ValidPassword
    private  String password;
}