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
@Table(name="tours_bor")
public class TourBOR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id_tours_bor;

    //fk
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_res_id1")
    private BookingsOrReservations fk_book_res_id1;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "book_res_id2")
    private BookingsOrReservations fk_book_res_id2;

    //mapeos
    @OneToMany(mappedBy = "fk_bookings_or_reservations", cascade = {CascadeType.ALL})
    private Set<TouristicPackage> touristicPackages;
}
