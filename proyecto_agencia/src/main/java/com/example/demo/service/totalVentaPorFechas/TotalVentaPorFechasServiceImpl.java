package com.example.demo.service.totalVentaPorFechas;

import com.example.demo.dto.ResponseTotalVentaPorFechas.ResponseTotalVentaPorDiaDTO;
import com.example.demo.model.Flight_reservation;
import com.example.demo.repository.FlightReservationRepository;
import com.example.demo.repository.HotelBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class TotalVentaPorFechasServiceImpl implements TotalVentaPorFechasService{
    @Autowired
    HotelBookingRepository hotelBookingRepository;

    @Autowired
    FlightReservationRepository flightReservationRepository;

//    @Override
//    public ResponseTotalVentaPorDiaDTO getAmountOneDate(Date date) {
//        System.out.println(flightReservationRepository.getAmountOneDate(date).toString());
//        ResponseTotalVentaPorDiaDTO responseTotalVentaPorDiaDTO = flightReservationRepository.getAmountOneDate(date);

//        ResponseTotalVentaPorDiaDTO responseTotalVentaPorDiaDTO = responseTotalVentaPorDiaDTOList;
//        System.out.println();
//        return null;
//    }
}
