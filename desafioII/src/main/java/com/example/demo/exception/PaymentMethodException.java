package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "El Tipo de pago que hiciste fue con DEBITO por favor poner 1 en su couta de pago.", value = HttpStatus.BAD_REQUEST)
public class PaymentMethodException extends RuntimeException {
    public PaymentMethodException(String message){
        super(message);
    }
}
