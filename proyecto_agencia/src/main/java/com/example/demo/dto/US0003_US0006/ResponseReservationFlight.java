package com.example.demo.dto.US0003_US0006;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vanessa Rocha
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
/** Representa el objeto con los datos necesarios para retornar una reservacion de vuelos*/
public class ResponseReservationFlight {
    private String username;
/** Proviene de la clase FlightReservationDTO*/
    private FlightReservationDTO flightReservation;
/** Proviene de la clase ResponseStatusCode*/
   // private ResponseStatusCode statusCode;
}
