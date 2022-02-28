package com.example.demo.service.booking;

import com.example.demo.dto.ResponseHotelBookingDTO;
import com.example.demo.dto.US0003_US0006.BookingDTO;
import com.example.demo.dto.US0003_US0006.PayloadHotelsDTO;
import com.example.demo.dto.US0003_US0006.PaymentMethodDTO;
import com.example.demo.dto.US0003_US0006.PeopleDTO;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelBookingServiceImpl implements HotelBookingService{
    @Autowired
    HotelBookingRepository hotelBookingRepository;
    @Autowired
    PaymentMethodRepository paymentMethodRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    PeopleRepository peopleRepository;
    @Autowired
    HotelPeopleRepository hotelPeopleRepository;

    @Override
    public BookingDTO postHotelBooking(PayloadHotelsDTO payloadHotelsDTO, String status) {
        // Extract data and save user in db
        Users user = new Users();
        user.setId_user(user.getId_user());
        user.setUserName(payloadHotelsDTO.getUsername());
        usersRepository.save(user);
        // Para el método de pago
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setType(payloadHotelsDTO.getBooking().getPaymentMethod().getType());
        paymentMethod.setNumber(payloadHotelsDTO.getBooking().getPaymentMethod().getNumber());
        paymentMethod.setDues(payloadHotelsDTO.getBooking().getPaymentMethod().getDues());
        paymentMethodRepository.save(paymentMethod);
        // Para el booking
        Hotel_booking booking = new Hotel_booking();
        booking.setDateFrom(payloadHotelsDTO.getBooking().getDateFrom());
        booking.setDateTo(payloadHotelsDTO.getBooking().getDateTo());
        booking.setDestination(payloadHotelsDTO.getBooking().getDestination());
        booking.setHotelCode(payloadHotelsDTO.getBooking().getHotelCode());
        booking.setPeopleAmount(payloadHotelsDTO.getBooking().getPeopleAmount());
        booking.setRoomType(payloadHotelsDTO.getBooking().getRoomType());
        booking.setPaymentMethodH(paymentMethod);
        booking.setUserH(user);
        hotelBookingRepository.save(booking);
        // Listing people
        List<PeopleDTO> peopleList = payloadHotelsDTO.getBooking().getPeople();
        peopleList.forEach(p -> {
            People person = new People();
            person.setDni(p.getDni());
            person.setName(p.getName());
            person.setLastname(p.getLastname());
            person.setBirthDate(p.getBirthDate());
            person.setMail(p.getMail());
            peopleRepository.save(person);
            Hotel_people person2 = new Hotel_people();
            person2.setHotel_booking_p(booking);
            person2.setPeople_hb(person);
            hotelPeopleRepository.save(person2);
        });
        ResponseHotelBookingDTO response = new ResponseHotelBookingDTO("Reserva de hotel dada de alta correctamente.");
        return null;
    }
}
