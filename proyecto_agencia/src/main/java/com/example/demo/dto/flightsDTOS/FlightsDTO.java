package com.example.demo.dto.flightsDTOS;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightsDTO {
    @NotNull(message = "Se debe ingresar el campo 'name'")
    @NotBlank(message = "El campo 'name' no debe estar vacio")
    private String name;
    @NotNull(message = "Se debe ingresar el campo 'origin'")
    @NotBlank(message = "El campo 'origin' no debe estar vacio")
    private String origin;
    @NotNull(message = "Se debe ingresar el campo 'destination'")
    @NotBlank(message = "El campo 'destination' no debe estar vacio")
    private String destination;
    @NotNull(message = "Se debe ingresar el campo 'seatType'")
    @NotBlank(message = "El campo 'seatType' no debe estar vacio")
    private String seatType;
    @NotNull(message = "Se debe ingresar el campo 'flightPrice'")
    private double flightPrice;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Se debe ingresar el campo 'goingDate'")
    private Date goingDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Se debe ingresar el campo 'returnDate'")
    private Date returnDate;
}
