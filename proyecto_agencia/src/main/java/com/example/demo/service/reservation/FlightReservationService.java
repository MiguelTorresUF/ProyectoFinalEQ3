package com.example.demo.service.reservation;

import com.example.demo.dto.ResponseFlightReservationDTO;
import com.example.demo.dto.US0003_US0006.FlightReservationDTO;
import com.example.demo.dto.US0003_US0006.PayloadFlightsDTO;
import com.example.demo.dto.US0003_US0006.ResponseReservationFlight;
import com.example.demo.model.Flight_reservation;
import com.example.demo.model.Users;

import java.util.List;

public interface FlightReservationService {
    public Iterable<Flight_reservation> findAll();
    public ResponseFlightReservationDTO postFlightReservation(PayloadFlightsDTO payloadDTO, String status);
    public ResponseFlightReservationDTO updateReservation(PayloadFlightsDTO payloadDTO, int idflight_reservation);
}
