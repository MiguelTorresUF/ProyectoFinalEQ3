package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="people")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int id_people;

    private String dni;

    private String name;
    private String lastname;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birth_date;

    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",message = "Email no valido.")
    private String mail;

    //mapeado por un objeto de la entidad
    @OneToMany(mappedBy = "people_fr", cascade = {CascadeType.ALL})
    private Set<Flight_people> flight_people;

    @OneToMany(mappedBy = "people_hb", cascade = {CascadeType.ALL})
    private Set<Hotel_people> hotel_people;


}
