package com.example.demo.service.booking;

import com.example.demo.dto.ResponseHotelBookingDTO;
import com.example.demo.dto.US0003_US0006.PayloadHotelsDTO;
import com.example.demo.model.Hotel_booking;
import com.example.demo.model.Users;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public interface HotelBookingService {
    public List<Hotel_booking> getHotelBooking();
    public ResponseHotelBookingDTO postHotelBooking(PayloadHotelsDTO payloadHotelsDTO, String status);
    public ResponseHotelBookingDTO updateHotelBooking(PayloadHotelsDTO payloadHotelsDTO, int id);
    public ResponseHotelBookingDTO deleteHotelBooking(int id);
    public ObjectNode top3();

}
