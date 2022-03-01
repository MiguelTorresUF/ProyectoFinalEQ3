package com.example.demo.service.booking;

import com.example.demo.dto.ResponseHotelBookingDTO;
import com.example.demo.dto.US0003_US0006.PayloadHotelsDTO;

public interface HotelBookingService {
    public ResponseHotelBookingDTO getHotelBooking();
    public ResponseHotelBookingDTO postHotelBooking(PayloadHotelsDTO payloadHotelsDTO, String status);
    public ResponseHotelBookingDTO updateHotelBooking(PayloadHotelsDTO payloadHotelsDTO, int id);
    public ResponseHotelBookingDTO deleteHotelBooking(int id);
}
