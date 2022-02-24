package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "El metodo de pago no es el correcto.", value = HttpStatus.BAD_REQUEST)
public class CashInvalidException extends RuntimeException {
    public CashInvalidException(String message){
        super(message);
    }
}
