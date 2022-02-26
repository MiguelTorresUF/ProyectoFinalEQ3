package com.example.demo.repository;

import com.example.demo.model.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Vanessa Rocha
 */
/** Representa la interface de HotelRepository*/

@Repository
public interface HotelRepository extends JpaRepository<Hotels,Integer> {
}

