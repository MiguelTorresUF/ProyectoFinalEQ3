package com.example.demo.service.hotel;

import com.example.demo.dto.US0001_US0002.ResponseDTO;
import com.example.demo.dto.US0003_US0006.PayloadHotelsDTO;
import com.example.demo.dto.US0003_US0006.ResponseReservationHotel;

import com.example.demo.dto.hotelsDTOS.HotelsDTO;
import com.example.demo.dto.hotelsDTOS.ResponseDataHotelsDTO;
import com.example.demo.dto.hotelsDTOS.ResponseHotelsDTO;
import com.example.demo.model.Flights;
import com.example.demo.model.Hotels;

import java.util.Date;

/**
 * @author Vanessa Rocha
 */
/** Representa la interface de HotelService*/
public interface HotelService {
    public Hotels save(Hotels hotels);
    public Iterable<Hotels> findAll();
    public ResponseHotelsDTO getHotelsAvailable(Date dateFrom, Date dateTo, String destination);
    public ResponseDataHotelsDTO update(HotelsDTO hotelsDTO, String hotelCode);
    public ResponseDataHotelsDTO delete(String hotelCode);
}
