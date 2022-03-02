package com.example.demo.controller;

import com.example.demo.dto.hotelsDTOS.HotelsDTO;
import com.example.demo.dto.hotelsDTOS.ResponseHotelsDTO;
import com.example.demo.model.Hotels;
import com.example.demo.service.hotel.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.Optional;

@RestController
public class HotelsRestController {
    @Autowired
    HotelService hotelService;

    //AGREGAR HOTEL
    @PostMapping("/api/v1/hotels/new")
    public ResponseEntity<?> returnNewHotel(@Valid @RequestBody Hotels hotels){
        return new ResponseEntity<>(hotelService.save(hotels), HttpStatus.CREATED);

    }

    //LISTADO VUELOS
    @GetMapping("/api/v1/hotels")
    public ResponseEntity<?> returnAllFlights(){
        return new ResponseEntity<>(hotelService.findAll(), HttpStatus.OK);
    }

    //LISTADO VUELO CON FILTRO
    //Listado de vuelos según filtros
    @GetMapping(path = "/api/v1/hotels", params = {"dateFrom", "dateTo", "destination"})
    public ResponseEntity<ResponseHotelsDTO> returnHotelsAvailable(
            @RequestParam()
            @DateTimeFormat(pattern="dd/MM/yyyy")
                    Date dateFrom,
            @RequestParam()
            @DateTimeFormat(pattern="dd/MM/yyyy")
                    Date dateTo,
            @RequestParam()
                    String destination
    ){
        return new ResponseEntity<ResponseHotelsDTO>(hotelService.getHotelsAvailable(dateFrom, dateTo,  destination), HttpStatus.OK);
    }
//ESTO SUBIRLO EN RAMA DE ELIMINAR_HOTELS
    //Eliminar casos de prueba.

    @DeleteMapping(path ="/api/v1/hotels/delete" , params={"hotelCode"})
    public ResponseEntity<?> delete (@Valid @RequestParam() String hotelCode){

        return new ResponseEntity<>(hotelService.delete(hotelCode), HttpStatus.OK);
    }



//ESTO SUBIRLO EN RAMA EDITAR HOTELES
    //EDITAR HOTELES

    @PutMapping(path = "/api/v1/hotels/edit", params = {"hotelCode"})
    public ResponseEntity<?> updateHotels(@Valid @RequestBody HotelsDTO hotelsDTO,
                                           @RequestParam()
                                                   String hotelCode){
        return new ResponseEntity<>(hotelService.update(hotelsDTO, hotelCode), HttpStatus.CREATED);
    }



}
