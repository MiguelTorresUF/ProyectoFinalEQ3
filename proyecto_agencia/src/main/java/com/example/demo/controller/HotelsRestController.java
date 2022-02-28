package com.example.demo.controller;

import com.example.demo.model.Hotels;
import com.example.demo.service.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelsRestController {
    @Autowired
    HotelService hotelService;

    //AGREGAR HOTEL
    @PostMapping("/api/v1/hotels/new")
    public ResponseEntity<?> returnNewHotel(@RequestBody Hotels hotels){
        return new ResponseEntity<>(hotelService.save(hotels), HttpStatus.CREATED);
    }

}
