package com.example.demo.repository.vuelos;

import com.example.demo.entity.Flights;

import java.util.List;

/**
 * @author Vanessa Rocha
 */
/** Representa la interface de VuelosRepository*/
public interface VuelosRepository {
    public List<Flights> getDatabaseFlights();
}
