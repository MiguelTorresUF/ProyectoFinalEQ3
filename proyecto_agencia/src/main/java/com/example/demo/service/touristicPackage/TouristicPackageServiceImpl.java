package com.example.demo.service.touristicPackage;

import com.example.demo.dto.packagesDTOS.BookingOrReservationDTO;
import com.example.demo.dto.packagesDTOS.ResponsePackageDTO;
import com.example.demo.dto.packagesDTOS.TourBORDTO;
import com.example.demo.dto.packagesDTOS.TouristicPackageDTO;
import com.example.demo.exception.DuplicatedColumnsException;
import com.example.demo.exception.ListEmptyException;
import com.example.demo.model.*;
import com.example.demo.repository.FlightReservationRepository;
import com.example.demo.repository.HotelBookingRepository;
import com.example.demo.repository.TouristicPackageRepository;
import com.example.demo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TouristicPackageServiceImpl implements TouristicPackageService{

    @Autowired
    TouristicPackageRepository touristicPackageRepository;

    @Autowired
    HotelBookingRepository hotelBookingRepository;

    @Autowired
    UsersRepository usersRepository;


    @Autowired
    FlightReservationRepository flightReservationRepository;


    //AGREGAR PAQUETE
    @Override
    public ResponsePackageDTO save(TouristicPackageDTO touristicPackageDTO) {

       //BookingOrReservationDTO id1 = touristicPackageDTO.getTourBOR().getBook_res_id1();
       //BookingOrReservationDTO id2 = touristicPackageDTO.getTourBOR().getBook_res_id2();
        //Extraer datos para date y name e insertalos
        TouristicPackage touristicPackage = new TouristicPackage();
        touristicPackage.setCreation_date(touristicPackageDTO.getCreation_date());
        touristicPackage.setTotal(touristicPackageDTO.getTotal());
        Optional<Users> users =usersRepository.findById(touristicPackageDTO.getUserid());
        touristicPackage.setBook_res_id1(touristicPackageDTO.getBook_res_id1());
        touristicPackage.setBook_res_id2(touristicPackageDTO.getBook_res_id2());
        TourBOR tourBOR = new TourBOR();
        BookingsOrReservations bookingsOrReservations = new BookingsOrReservations();
        TourBORDTO tourBORDTO = new TourBORDTO();
        tourBORDTO.setBook_res_id1(tourBORDTO.getBook_res_id2());
        tourBORDTO.setBook_res_id2(tourBORDTO.getBook_res_id2());


        //touristicPackage.setBook_res_id2(touristicPackage.getBook_res_id2());




/*
           Integer a2 = id2.getId_booking();
        Integer vuelosreservation2 = id2.getId_reservation();
        Integer vuelosreservation1 = id1.getId_reservation();
        Integer a= id1.getId_booking();
        System.out.println("ESTE ES :>>> a1"+a);
        int count=0;
       if (a>=1){
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
        System.out.println("ESTE ES :>>>v1"+vuelosreservation1);
       if(vuelosreservation1>=1){
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

        System.out.println("ESTE ES :>>>a2"+a2);
           if (a2>=1) {
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

        System.out.println("ESTE ES :>>>v2"+vuelosreservation2);
           if(vuelosreservation2>=1){

               Optional<Flight_reservation> flight_reservation = flightReservationRepository.findById(id2.getId_reservation());
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

        //Extraer datos para date y name e insertalos
        TouristicPackage touristicPackage = new TouristicPackage();
        touristicPackage.setCreation_date(touristicPackageDTO.getCreation_date());
        Optional<Users> users =usersRepository.findById(touristicPackageDTO.getUserid());
        TourBOR tourBOR = new TourBOR();
        BookingsOrReservations bookingsOrReservations = new BookingsOrReservations();


        Hotel_booking hotelBooking1 =hotelBookingRepository.findById(id1.getId_booking()).orElse(null);
        bookingsOrReservations.setFk_id_booking(hotelBooking1);
        Flight_reservation flight_reservation1 =flightReservationRepository.findById(id1.getId_reservation()).orElse(null);
        bookingsOrReservations.setFk_id_reservation(flight_reservation1);
        /////
        Hotel_booking hotelBooking2 =hotelBookingRepository.findById(id2.getId_booking()).orElse(null);
        bookingsOrReservations.setFk_id_booking(hotelBooking2);
        Flight_reservation flight_reservation2 =flightReservationRepository.findById(id2.getId_reservation()).orElse(null);
        bookingsOrReservations.setFk_id_reservation(flight_reservation2);
        System.out.println("ID1 VALE TANTO"+id1.getId_booking());
        System.out.println("ID2 VALE TANTO"+id1.getId_reservation());
        System.out.println("ID3 VALE TANTO"+id2.getId_booking());
        System.out.println("ID4 VALE TANTO"+id2.getId_reservation());
        if(id1.getId_booking() !=0 || id2.getId_booking() !=0)
        {
           // id1.getId_booking()=
            //tourBOR.getFk_book_res_id1().setFk_id_booking();
            //FORMA LLAMAR DATOS DE LA TABLA BOOKINGS OR RESERVATIONS Y INSERTARLOS DIRECTAMENTE LOS QUE SALGAN
            //touristicPackage.setFk_bookings_or_reservations(tourBOR.getFk_book_res_id1()
           // touristicPackage.getFk_bookings_or_reservations().getFk_book_res_id1().setFk_id_booking(hotelBooking1);
            //touristicPackage.getFk_bookings_or_reservations().getFk_book_res_id2().setFk_id_booking(hotelBooking2);
            //touristicPackage.getFk_bookings_or_reservations().
            //touristicPackage.getFk_bookings_or_reservations().getFk_book_res_id1().setFk_id_reservation(hotelBooking1);
           // touristicPackage.getFk_bookings_or_reservations().getFk_book_res_id2().setFk_id_reservation(null);
        }*/









        touristicPackage.setUserid(users.get());
        touristicPackageRepository.save(touristicPackage);
        ResponsePackageDTO responsePackageDTO = new ResponsePackageDTO("Paquete Turístico dado de alta correctamente");
         return responsePackageDTO;



    }

}

