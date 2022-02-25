package com.example.demo.repository;

import com.example.demo.model.Flights;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Vanessa Rocha
 */
/** Representa la interface de VuelosRepository*/
public interface FlightRepository extends JpaRepository<Flights,Integer> {
}
