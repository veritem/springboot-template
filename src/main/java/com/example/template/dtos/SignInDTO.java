package com.example.template.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class SignInDTO {

    @NotBlank
    @Email
    private  String email;

    @NotBlank
    private  String password;
}
