package com.example.demo.repository;

import com.example.demo.model.Flight_people;
import com.example.demo.model.Flights;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightPeopleRepository extends JpaRepository<Flight_people,Integer> {
}