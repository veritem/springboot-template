package com.example.template.exceptions;

public class ApiRequestException  extends RuntimeException{
    public ApiRequestException(String message) {
        super(message);
    }
}
