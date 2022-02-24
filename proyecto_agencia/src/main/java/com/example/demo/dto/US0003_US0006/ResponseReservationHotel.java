package com.example.demo.dto.US0003_US0006;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author   Vanessa Rocha
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
/** Representa el objeto con los datos necesarios para retornar una reservacion de hoteles*/
public class ResponseReservationHotel {
    private String username;
    private double amount;
    private double interest;
    private double total;
/** Proviene de la clase BookingDTO*/
    private BookingDTO booking;
/** Proviene de la clase ResponseStatusCode*/
    private ResponseStatusCode statusCode;
}


