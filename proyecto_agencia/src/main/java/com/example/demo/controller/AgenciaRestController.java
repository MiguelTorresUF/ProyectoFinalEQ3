package com.example.demo.controller;

import com.example.demo.dto.US0001_US0002.ResponseDTO;
import com.example.demo.dto.US0003_US0006.PayloadFlightsDTO;
import com.example.demo.dto.US0003_US0006.PayloadHotelsDTO;
import com.example.demo.dto.US0003_US0006.ResponseReservationFlight;
import com.example.demo.dto.US0003_US0006.ResponseReservationHotel;
import com.example.demo.dto.US0004_US0005.ResponseFlightsDTO;
import com.example.demo.service.hotel.HotelService;
import com.example.demo.service.vuelos.VuelosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/** Representa el RestController para los EndPoints
 * @author Vanessa Rocha
 */
@RestController
public class AgenciaRestController {
    /** Instancia a la clase HotelService
     */
    @Autowired
    HotelService hotelService;

    /** EndPoint para retornar todos los hoteles
     * @return una lista de hoteles
     */
    @GetMapping("/api/v1/hotels")
    public ResponseEntity<ResponseDTO> returnAllHotels(){
        return new ResponseEntity<ResponseDTO>(hotelService.getHotels(), HttpStatus.OK);
    }

    /** Endpoint para retorna una lista de hoteles con los parametros especificados
     * @param dateFrom hotel fecha de entrada
     * @param dateTo hotel fecha de salida
     * @param destination hotel de destino
     * @return una lista de hoteles
     */
    @GetMapping(path = "/api/v1/hotels", params = {"dateFrom", "dateTo", "destination"})
    public ResponseEntity<ResponseDTO> returnHotelsAvailable(
            @RequestParam()
            @DateTimeFormat(pattern="dd/MM/yyyy")
                     Date dateFrom,
            @RequestParam()
            @DateTimeFormat(pattern="dd/MM/yyyy")
                    Date dateTo,
            @RequestParam()
                    String destination
    ){
        return new ResponseEntity<>(hotelService.getHotelsAvailable(dateFrom, dateTo, destination), HttpStatus.OK);
    }

    /** Reservación de un hotel
     * @param payloadDTO el hotel que se desea reservar, con los datos del usuario y método de pago
     * @return la el precio por las noches a quedar, el interes si paga con tarjeta, el total y todos los datos del usuario
     */
    @PostMapping("/api/v1/booking")
    public ResponseEntity<ResponseReservationHotel> returnReservationHotel(@Valid @RequestBody PayloadHotelsDTO payloadDTO){
        return new ResponseEntity<ResponseReservationHotel>(hotelService.postBooking(payloadDTO, String.valueOf(HttpStatus.OK)), HttpStatus.OK);
    }



//--------------------------------------------------------------------------------------------------------------

    /** Instancia a la clase VuelosService
     */
    @Autowired
    VuelosService vuelosService;

    /** EndPoint para retornar todos los vuelos
     * @return una lista de vuelos
     */
    @GetMapping("/api/v1/flights")
    public ResponseEntity<ResponseFlightsDTO> returnAllFlights(){
        return new ResponseEntity<ResponseFlightsDTO>(vuelosService.getFlights(), HttpStatus.OK);
    }

    /** Endpoint para retorna una lista de vuelos con los parametros especificados
     * @param dateFrom hotel fecha de entrada
     * @param dateTo hotel fecha de salida
     * @param origin hotel de origen
     * @param destination hotel de destino
     * @return una lista de vuelos
     */
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
        System.out.println(dateFrom);
        return new ResponseEntity<>(vuelosService.getFlightsAvailable(dateFrom, dateTo, origin,  destination), HttpStatus.OK);
    }

    /** Reservación de un hotel
     * @param payloadFlightsDTO el vuelo que se desea reservar, con los datos del usuario y método de pago
     * @return el precio por las asientos que se compren, el interes si paga con tarjeta, el total y todos los datos del usuario
     */
    @PostMapping("/api/v1/flight-reservation")
    public ResponseEntity<ResponseReservationFlight> returnReservationFlight(@Valid @RequestBody PayloadFlightsDTO payloadFlightsDTO){
        return new ResponseEntity<ResponseReservationFlight>(vuelosService.postFlightReservation(payloadFlightsDTO, String.valueOf(HttpStatus.OK)), HttpStatus.OK);
    }

}
