package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "El destino ingresado no existe.", value = HttpStatus.BAD_REQUEST)
public class NotDestinationException extends RuntimeException {
    public NotDestinationException(String message){
        super(message);
    }
}
