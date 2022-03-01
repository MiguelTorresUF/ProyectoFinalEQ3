package com.example.demo.repository;

import com.example.demo.model.BookingsOrReservations;
import com.example.demo.model.TourBOR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsOrReservationsRepository extends JpaRepository<BookingsOrReservations,Integer> {
}
