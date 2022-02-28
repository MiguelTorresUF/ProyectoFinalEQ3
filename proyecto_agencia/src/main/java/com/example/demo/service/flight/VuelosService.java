package com.example.demo.service.flight;


import com.example.demo.dto.flightsDTOS.FlightsDTO;
import com.example.demo.dto.flightsDTOS.ResponseFlightsDTO;
import com.example.demo.dto.flightsDTOS.ResponsePPDFlightDTO;
import com.example.demo.model.Flights;

import java.util.Date;


public interface VuelosService {
    public Iterable<Flights> findAll();
    public ResponseFlightsDTO getFlightsAvailable(Date dateFrom, Date dateTo, String origin, String destination);
    public Flights save(Flights flights);
    public ResponsePPDFlightDTO update(FlightsDTO flights, String flightNumber);
}
