package com.example.demo.repository;

import com.example.demo.model.Flights;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vanessa Rocha
 */
/** Representa la interface de VuelosRepository*/
@Repository
public interface FlightRepository extends JpaRepository<Flights,Integer> {
}
