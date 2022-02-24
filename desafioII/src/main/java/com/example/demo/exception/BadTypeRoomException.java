package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "La habitacion no coincide con el numero de personas.", value = HttpStatus.BAD_REQUEST)
public class BadTypeRoomException extends RuntimeException {
    public BadTypeRoomException(String message){
        super(message);
    }
}
