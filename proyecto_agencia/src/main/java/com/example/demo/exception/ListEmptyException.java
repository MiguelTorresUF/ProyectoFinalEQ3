package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "No se encontro el hotel ingresado", value = HttpStatus.BAD_REQUEST)
public class ListEmptyException extends RuntimeException {
    public ListEmptyException(String message){
        super(message);
    }
}
