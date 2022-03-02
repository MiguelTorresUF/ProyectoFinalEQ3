package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="touristic_package")
public class TouristicPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id_package_number;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date creation_date;

    //private String name;

    //fk
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private Users userid;

    //fk
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "bookings_or_reservations")
    private TourBOR fk_bookings_or_reservations;
}
