package com.example.demo.repository;

import com.example.demo.model.Flight_reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightReservationRepository extends JpaRepository<Flight_reservation,Integer> {
}
