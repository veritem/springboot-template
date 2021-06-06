package com.example.template.util;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Response {
    private  String message;
    private ZonedDateTime timestamp;
    private boolean success;
}
