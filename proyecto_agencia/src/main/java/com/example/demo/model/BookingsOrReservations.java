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
@Table(name="bookings_or_reservations")
public class BookingsOrReservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int idbookings_or_reservations;

    //fk's
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_booking")
    private Hotel_booking fk_id_booking;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_reservation")
    private Flight_reservation fk_id_reservation;


    //mapeos
    @OneToMany(mappedBy = "fk_book_res_id1", cascade = {CascadeType.ALL})
    private Set<TourBOR> packages_bookRes1;

    @OneToMany(mappedBy = "fk_book_res_id2", cascade = {CascadeType.ALL})
    private Set<TourBOR> packages_bookRes2;
}
