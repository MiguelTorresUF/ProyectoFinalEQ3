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
@Table(name="hotel_booking")
public class Hotel_booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int booking_id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateFrom;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateTo;
    private String destination;
    private String hotelCode;
    private String roomType;
    private int peopleAmount;

    @ManyToOne(cascade = CascadeType.PERSIST)
    //fk
    @JoinColumn(name = "id_user")
    private Users userH;


    @ManyToOne(cascade = CascadeType.PERSIST)
    //fk
    @JoinColumn(name = "id_paymentMethod")
    private PaymentMethod paymentMethodH;


    //mapeado por un objeto de la entidad
    @OneToMany(mappedBy = "hotel_booking_p", cascade = {CascadeType.ALL})
    private Set<Hotel_people> hotel_people;

    //mapeado por un objeto de la entidad
    @OneToMany(mappedBy = "fk_id_booking", cascade = {CascadeType.ALL})
    private Set<BookingsOrReservations> booking;

}
