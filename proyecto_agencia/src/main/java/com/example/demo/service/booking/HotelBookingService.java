package com.example.demo.service.booking;

import com.example.demo.dto.US0003_US0006.BookingDTO;
import com.example.demo.dto.US0003_US0006.PayloadHotelsDTO;

public interface HotelBookingService {
    public BookingDTO postHotelBooking(PayloadHotelsDTO payloadHotelsDTO, String status);
}
