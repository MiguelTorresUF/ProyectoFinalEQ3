package com.example.demo.service.hotel;

import com.example.demo.dto.US0001_US0002.ResponseDTO;
import com.example.demo.dto.US0003_US0006.PayloadHotelsDTO;
import com.example.demo.dto.US0003_US0006.ResponseReservationHotel;

import java.util.Date;

/**
 * @author Vanessa Rocha
 */
/** Representa la interface de HotelService*/
public interface HotelService {
    /** Método para obtener todos los hoteles
     * @return la lista de todos los hoteles*/
    public ResponseDTO getHotels();
    /** Método para obtener todos los hoteles disponibles segun los parametros
     * @param dateFrom fecha inicial
     * @param dateTo fecha final
     * @param destination destino
     * @return lista de hoteles según los parámetros ingresados*/
    public ResponseDTO getHotelsAvailable(Date dateFrom, Date dateTo, String destination);
    /** Método para reservar un hotel
     * @param payloadDTO objeto con los datos necesarios pare reservar
     * @param status estatus de su reservación
     * @return los datos del hotel que se reservo con los montos*/
    public ResponseReservationHotel postBooking(PayloadHotelsDTO payloadDTO, String status);
}
