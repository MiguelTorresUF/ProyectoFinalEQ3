package com.example.demo.dto.US0003_US0006;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

/**
 * @author Vanessa Rocha
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
/** Representa el objeto con los datos necesarios de una reservacion de vuelos*/
public class PayloadFlightsDTO {
    private String userName;
    /** Se obtiene de la clase FlightReservation*/
    @Valid
    private FlightReservationDTO flightReservation;
}
