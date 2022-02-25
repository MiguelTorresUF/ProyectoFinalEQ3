package com.example.demo.repository;

import com.example.demo.model.Flight_people;
import com.example.demo.model.Hotel_people;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelPeopleRepository extends JpaRepository<Hotel_people,Integer> {
}
