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
@Table(name="hotels")
public class Hotels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id_hotel;

    private String hotelCode;
    private String name;
    private String place;
    private String roomType;
    private double roomPrice;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date disponibilityDateFrom;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date disponibilityDateTo;

    private boolean isBooking;
}
