package com.example.demo.dto.US0003_US0006;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Vanessa Rocha
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
/** Representa la lista reservación de vuelos con los principales datos
 */
public class FlightReservationDTO {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateFrom;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateTo;
    private String origin;
    private String destination;
    private String flightNumber;
    @Digits(fraction = 0, integer = 1, message = "La cantidad de personas debe ser un valor numerico.")
    private int seats;
    private String seatsType;
    /**Array de personas de la clase PeopleDTO*/
    private ArrayList<@Valid PeopleDTO> people;
    /**Proviene de la clase PaymentMethod*/
    @Valid
    private PaymentMethodDTO paymentMethod;
}
