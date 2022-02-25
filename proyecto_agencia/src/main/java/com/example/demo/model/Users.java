package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id_user;
    private String userName;


    //mapeado por un objeto de la entidad
    @OneToMany(mappedBy = "userF", cascade = {CascadeType.ALL})
    private Set<Flight_reservation> flight_reservation;

    @OneToMany(mappedBy = "userH", cascade = {CascadeType.ALL})
    private Set<Hotel_booking> hotel_booking;
}
