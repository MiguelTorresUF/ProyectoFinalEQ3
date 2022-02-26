package com.example.demo.controller;

import com.example.demo.dto.flightsDTOS.ResponseFlightsDTO;
import com.example.demo.service.flight.VuelosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class FlightsRestController {

    @Autowired
    VuelosService vuelosService;

    //Listado de todos los vuelos.
    @GetMapping("/api/v1/flights")
    public ResponseEntity<?> returnAllFlights(){
        return new ResponseEntity<>(vuelosService.findAll(), HttpStatus.OK);
    }

    //Listado de vuelos según filtros
    @GetMapping(path = "/api/v1/flights", params = {"dateFrom", "dateTo", "origin", "destination"})
    public ResponseEntity<ResponseFlightsDTO> returnFlightsAvailable(
            @RequestParam()
            @DateTimeFormat(pattern="dd/MM/yyyy")
                    Date dateFrom,
            @RequestParam()
            @DateTimeFormat(pattern="dd/MM/yyyy")
                    Date dateTo,
            @RequestParam()
                    String origin,
            @RequestParam()
                    String destination
    ){
        return new ResponseEntity<ResponseFlightsDTO>(vuelosService.getFlightsAvailable(dateFrom, dateTo, origin,  destination), HttpStatus.OK);
    }
}
