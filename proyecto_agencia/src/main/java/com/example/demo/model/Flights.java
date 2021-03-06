package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="flights")
public class Flights {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id_vuelo;

    @Column(unique = true)
    @NotBlank(message = "El campo 'flightNumber' no debe estar vacio")
    //@Unique(message = "El campo 'flightNumber' se esta duplicando")
    private String flightNumber;

    @NotBlank(message = "El campo 'name' no debe estar vacio")
    private String name;

    @NotBlank(message = "El campo 'origin' no debe estar vacio")
    private String origin;

    @NotBlank(message = "El campo 'destination' no debe estar vacio")
    private String destination;

    @NotBlank(message = "El campo 'seatType' no debe estar vacio")
    private String seatType;

    @NotNull(message = "El campo 'flightPrice' no debe estar vacio")
    private double flightPrice;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    @NotNull(message = "Se debe ingresar el campo 'goingDate'")
    private Date goingDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    @NotNull(message = "Se debe ingresar el campo 'returnDate'")
    private Date returnDate;
}
