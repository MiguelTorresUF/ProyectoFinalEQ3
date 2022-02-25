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
@Table(name="payment_method")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id_paymentMethod;

    private String type;
    private String number;
    private int dues;

    //mapeado por un objeto de la entidad
    @OneToMany(mappedBy = "paymentMethodF", cascade = {CascadeType.ALL})
    private Set<Flight_reservation> flight_reservation;

    @OneToMany(mappedBy = "paymentMethodH", cascade = {CascadeType.ALL})
    private Set<Hotel_booking> hotel_booking;
}
