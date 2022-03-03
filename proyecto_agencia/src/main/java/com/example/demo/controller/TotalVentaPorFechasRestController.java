package com.example.demo.controller;

import com.example.demo.dto.flightsDTOS.ResponseFlightsDTO;
import com.example.demo.service.totalVentaPorFechas.TotalVentaPorFechasService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
public class TotalVentaPorFechasRestController {
//    @Autowired
//    TotalVentaPorFechasService totalVentaPorFechasService;
//
//    @GetMapping(path = "/api/v1/income", params = {"date"})
//    public ResponseEntity<?> returnFlightsAvailable(
//            @RequestParam()
//                    String date
//    ){
//        Date datetransform = Date.valueOf(date);
//        return new ResponseEntity<>(totalVentaPorFechasService.getAmountOneDate(datetransform), HttpStatus.OK);
//    }
}
