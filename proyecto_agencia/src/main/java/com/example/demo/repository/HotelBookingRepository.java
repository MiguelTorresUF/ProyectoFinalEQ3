package com.example.demo.repository;

import com.example.demo.model.Hotel_booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelBookingRepository extends JpaRepository<Hotel_booking,Integer> {
}
