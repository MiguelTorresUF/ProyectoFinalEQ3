package com.example.demo.controller;

import com.example.demo.dto.ResponseFlightReservationDTO;
import com.example.demo.dto.US0003_US0006.PayloadFlightsDTO;
import com.example.demo.dto.US0003_US0006.ResponseReservationFlight;
import com.example.demo.service.reservation.FlightReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class FlightReservationRestController {
    @Autowired
    FlightReservationService flightReservationService;

    @PostMapping("/api/v1/flight-reservation/new")
    public ResponseEntity<ResponseFlightReservationDTO> returnReservationFlight(@Valid @RequestBody PayloadFlightsDTO payloadFlightsDTO){
        return new ResponseEntity<>(flightReservationService.postFlightReservation(payloadFlightsDTO, String.valueOf(HttpStatus.OK)), HttpStatus.OK);
    }

}
