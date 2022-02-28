package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "El origen ingresado no existe.", value = HttpStatus.BAD_REQUEST)
public class NotOriginException extends RuntimeException {
    public NotOriginException(String message){
        super(message);
    }
}
