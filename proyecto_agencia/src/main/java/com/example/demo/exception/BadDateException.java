package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Alguna fecha es incorrecta. La fecha de entrada tiene que ser menor que la de salida.", value = HttpStatus.BAD_REQUEST)
public class BadDateException extends RuntimeException{
    public BadDateException(String message){
        super(message);
    }
}
