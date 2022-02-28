package com.example.demo.controller;

import com.example.demo.dto.US0003_US0006.PayloadHotelsDTO;
import com.example.demo.model.Hotel_booking;
import com.example.demo.service.booking.HotelBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelBookingRestController {

    @Autowired
    HotelBookingService hotelBookingService;

    @PostMapping(path = "/api/v1/hotel-booking/new")
    public ResponseEntity<?> addHotelReservation(@RequestBody PayloadHotelsDTO payloadHotelsDTO){
        System.out.println("EN EL CONTROLLER");
        System.out.println(payloadHotelsDTO);
        return new ResponseEntity<>(hotelBookingService.postHotelBooking(payloadHotelsDTO, "200"), HttpStatus.CREATED);
    }
}
