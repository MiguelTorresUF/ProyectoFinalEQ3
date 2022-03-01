package com.example.demo.controller;

import com.example.demo.dto.US0003_US0006.PayloadHotelsDTO;
import com.example.demo.model.Hotel_booking;
import com.example.demo.service.booking.HotelBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HotelBookingRestController {

    @Autowired
    HotelBookingService hotelBookingService;

    @GetMapping(path = "/api/v1/hotel-bookings/")
    public ResponseEntity<?> listBookings(){
        System.out.println("GET ENDPOINT");
        return new ResponseEntity<>(hotelBookingService.getHotelBooking(), HttpStatus.OK);
    }

    @PostMapping(path = "/api/v1/hotel-booking/new")
    public ResponseEntity<?> addHotelReservation(@RequestBody PayloadHotelsDTO payloadHotelsDTO){
        System.out.println("POST ENDPOINT");
        System.out.println(payloadHotelsDTO);
        return new ResponseEntity<>(hotelBookingService.postHotelBooking(payloadHotelsDTO, "200"), HttpStatus.CREATED);
    }

    @PutMapping(path="/api/v1/hotel-booking/edit", params = {"id"})
    public ResponseEntity<?> updateHotelReservation(@RequestBody PayloadHotelsDTO payloadHotelsDTO, @RequestParam int id){
        return new ResponseEntity<>(hotelBookingService.updateHotelBooking(payloadHotelsDTO, id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/api/v1/hotel-booking/delete", params = {"id"})
    public ResponseEntity<?> deleteHotelBooking(@RequestParam int id){
        return new ResponseEntity<>(hotelBookingService.deleteHotelBooking(id),HttpStatus.OK);
    }
}
