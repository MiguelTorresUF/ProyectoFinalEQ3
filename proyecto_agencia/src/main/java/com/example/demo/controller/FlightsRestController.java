package com.example.demo.controller;

import com.example.demo.dto.flightsDTOS.FlightsDTO;
import com.example.demo.dto.flightsDTOS.ResponseFlightsDTO;
import com.example.demo.model.Flights;
import com.example.demo.service.flight.VuelosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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


    //Alta de un nuevo vuelo.
    @PostMapping("/api/v1/flights/new")
    public ResponseEntity<?> returnNewCase(@Valid @RequestBody Flights flights){
        return new ResponseEntity<>(vuelosService.save(flights), HttpStatus.CREATED);
    }

    //Actualizar un vuelo.
    @PutMapping(path = "/api/v1/flights/edit", params = {"flightNumber"})
    public ResponseEntity<?> updateFlights(@Valid @RequestBody FlightsDTO flights,
                                           @RequestParam()
                                           String flightNumber){
        return new ResponseEntity<>(vuelosService.update(flights, flightNumber), HttpStatus.CREATED);
    }

}
