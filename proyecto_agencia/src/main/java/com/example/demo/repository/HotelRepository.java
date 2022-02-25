package com.example.demo.repository;

import com.example.demo.model.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Vanessa Rocha
 */
/** Representa la interface de HotelRepository*/
public interface HotelRepository extends JpaRepository<Hotels,Integer> {
}
