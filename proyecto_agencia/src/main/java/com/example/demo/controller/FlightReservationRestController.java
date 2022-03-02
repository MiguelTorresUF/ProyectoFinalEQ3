package com.example.demo.controller;

import com.example.demo.dto.ResponseFlightReservationDTO;
import com.example.demo.dto.US0003_US0006.PayloadFlightsDTO;
import com.example.demo.dto.US0003_US0006.ResponseReservationFlight;
import com.example.demo.model.Flight_reservation;
import com.example.demo.service.reservation.FlightReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class FlightReservationRestController {
    @Autowired
    FlightReservationService flightReservationService;

    @PostMapping("/api/v1/flight-reservation/new")
    public ResponseEntity<ResponseFlightReservationDTO> returnReservationFlight(@Valid @RequestBody PayloadFlightsDTO payloadFlightsDTO){
        return new ResponseEntity<>(flightReservationService.postFlightReservation(payloadFlightsDTO, String.valueOf(HttpStatus.OK)), HttpStatus.OK);
    }

    //Listado de reserva de vuelos.
   @GetMapping("/api/v1/flight-reservations/")
    public ResponseEntity<?> returnAllFlights(){
        return new ResponseEntity<>(flightReservationService.findAll(), HttpStatus.OK);
    }

    //Actualizar reserva de vuelos
    @PutMapping(path = "/api/v1/flight-reservation/edit", params = {"idflight_reservation"})
    public ResponseEntity<?> updateReservation(@Valid @RequestBody PayloadFlightsDTO payloadDTO, @RequestParam()int idflight_reservation){
        return new ResponseEntity<>(flightReservationService.updateReservation(payloadDTO, idflight_reservation), HttpStatus.OK);
    }

    //Eliminar reserva de vuelos
    @DeleteMapping(path = "/api/v1/flight-reservation/delete", params = {"idflight_reservation"})
    public ResponseEntity<?> deleteReservationFlight(@Valid @RequestParam()int idflight_reservation ){
        return new ResponseEntity<>(flightReservationService.deleteReservationFlight(idflight_reservation), HttpStatus.OK);
    }



}
