package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="flight_reservation")
public class Flight_reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idflight_reservation;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date goingDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date returnDate;

    private String origin;
    private String destination;
    private String flightNumber;
    private int seats;
    private String seatType;

    //fk
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_user")
    private Users userF;


    //fk
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_paymentMethod")
    private PaymentMethod paymentMethodF;


    //mapeado por un objeto de la entidad
    @OneToMany(mappedBy = "flight_reservation_p", cascade = {CascadeType.ALL})
    private Set<Flight_people> flight_people;

    @OneToMany(mappedBy = "fk_id_reservation", cascade = {CascadeType.ALL})
    private Set<BookingsOrReservations> reservation;

}
