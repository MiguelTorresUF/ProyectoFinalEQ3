package com.example.demo.controller;

import com.example.demo.dto.packagesDTOS.TouristicPackageDTO;
import com.example.demo.model.Flights;
import com.example.demo.service.flight.VuelosService;
import com.example.demo.service.touristicPackage.TouristicPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TouristicPackageRestController {
    @Autowired
    TouristicPackageService touristicPackageService;

    //Alta de un nuevo vuelo.
    @PostMapping("/api/v1/touristicpackage/new")
    public ResponseEntity<?> returnNewCase(@Valid @RequestBody TouristicPackageDTO touristicPackageDTO){
        return new ResponseEntity<>(touristicPackageService.save(touristicPackageDTO), HttpStatus.CREATED);
    }
}
