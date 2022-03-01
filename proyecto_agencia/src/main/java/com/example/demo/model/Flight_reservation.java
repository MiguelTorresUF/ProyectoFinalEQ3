package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
   // @NotNull(message = "Se debe ingresar el campo 'goingDate'")
    private Date goingDate;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
   // @NotNull(message = "Se debe ingresar el campo 'returnDate'")
    private Date returnDate;

    //@NotBlank(message = "El campo 'origin' no debe estar vacio")
    private String origin;

    //@NotBlank(message = "El campo 'destination' no debe estar vacio")
    private String destination;

   // @NotBlank(message = "El campo 'flightNumber' no debe estar vacio")
    private String flightNumber;

    //@NotBlank(message = "El campo 'seats' no debe estar vacio")
    private int seats;

   // @NotBlank(message = "El campo 'seatType' no debe estar vacio")
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

}
