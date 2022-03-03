package com.example.demo.repository;

import com.example.demo.dto.ResponseTotalVentaPorFechas.ResponseTotalVentaPorDiaDTO;
import com.example.demo.model.Flight_reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface FlightReservationRepository extends JpaRepository<Flight_reservation,Integer> {
    @Query(nativeQuery = true)
    ResponseTotalVentaPorDiaDTO getAmountOneDate(Date date);
}
