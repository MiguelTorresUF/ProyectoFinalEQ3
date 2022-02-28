package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Ingrese un interes valido. (1-3, 4-6, 10-12, 16-18)", value = HttpStatus.BAD_REQUEST)
public class InterestNotValidException extends RuntimeException{
    public InterestNotValidException(String message){
        super(message);
    }
}
