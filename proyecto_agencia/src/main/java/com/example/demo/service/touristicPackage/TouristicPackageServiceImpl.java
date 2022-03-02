package com.example.demo.service.touristicPackage;

import com.example.demo.dto.packagesDTOS.BookingOrReservationDTO;
import com.example.demo.dto.packagesDTOS.TouristicPackageDTO;
import com.example.demo.exception.DuplicatedColumnsException;
import com.example.demo.exception.ListEmptyException;
import com.example.demo.model.BookingsOrReservations;
import com.example.demo.model.Flight_reservation;
import com.example.demo.model.Hotel_booking;
import com.example.demo.model.TouristicPackage;
import com.example.demo.repository.FlightReservationRepository;
import com.example.demo.repository.HotelBookingRepository;
import com.example.demo.repository.TouristicPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class TouristicPackageServiceImpl implements TouristicPackageService{

    @Autowired
    TouristicPackageRepository touristicPackageRepository;

    @Autowired
    HotelBookingRepository hotelBookingRepository;

    @Autowired
    FlightReservationRepository flightReservationRepository;

    @Override
    public TouristicPackage save(TouristicPackageDTO touristicPackageDTO) {
       BookingOrReservationDTO id1 = touristicPackageDTO.getTourBOR().getBook_res_id1();

        int count=0;
       if (id1.getId_booking() != null){
          int idhotelbooking = id1.getId_booking();

          Optional<Hotel_booking> hotelBooking = hotelBookingRepository.findById(idhotelbooking);
           if(!hotelBooking.isEmpty()) {
               count += 1;

           }
           else
           {
               throw new ListEmptyException("NO SE ENCONTRO ID CON ESE HOTEL REGISTRADO");


           }
       }

       if(id1.getId_reservation() !=null){
           int idFlightReservation = id1.getId_reservation();
           Optional<Flight_reservation> flight_reservation = flightReservationRepository.findById(idFlightReservation);
           if(!flight_reservation.isEmpty()) {
               count += 1;
           }
           else
           {
               throw new ListEmptyException("NO SE ENCONTRO ID CON ESE VUELO REGISTRADO");
           }
       }
       if(count>=2 ||count<=0 ){
           System.out.println("ERROR");
           throw new DuplicatedColumnsException("Siguiendo la jerarquia un campo es necesario que se envie nulo");

       }
            count=0;
           BookingOrReservationDTO id2 = touristicPackageDTO.getTourBOR().getBook_res_id2();
           if (id2.getId_booking()!=null) {
               int idhotelbooking = id2.getId_booking();
               Optional<Hotel_booking> hotelBooking = hotelBookingRepository.findById(idhotelbooking);
               if(!hotelBooking.isEmpty()) {
                   count += 1;
               }
               else
               {
                   throw new ListEmptyException("NO SE ENCONTRO ID CON ESE HOTEL REGISTRADO");
               }
           }


           if(id2.getId_reservation()!=null){
               int idFlightReservation = id2.getId_reservation();
               Optional<Flight_reservation> flight_reservation = flightReservationRepository.findById(idFlightReservation);
               if(!flight_reservation.isEmpty()) {
                   count += 1;
               }
               else
               {
                   throw new ListEmptyException("NO SE ENCONTRO ID CON ESE VUELO REGISTRADO");
               }

           }
        if(count>=2 ||count<=0 ){
            System.out.println("ERROR");
            throw new DuplicatedColumnsException("Siguiendo la jerarquia un campo es necesario que se envie nulo");

        }
        return null;
    }
}

