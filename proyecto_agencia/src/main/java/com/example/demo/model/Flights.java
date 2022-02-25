package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
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

    private String flightNumber;
    private String name;
    private String origin;
    private String destination;
    private String seatType;
    private double flightPrice;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private Date goingDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    private Date returnDate;
}
