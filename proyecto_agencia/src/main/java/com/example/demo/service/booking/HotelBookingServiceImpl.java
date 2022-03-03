package com.example.demo.service.booking;

import com.example.demo.dto.ResponseHotelBookingDTO;
import com.example.demo.dto.US0003_US0006.PayloadHotelsDTO;
import com.example.demo.dto.US0003_US0006.PeopleDTO;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.mysql.cj.xdevapi.JsonArray;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    FlightReservationRepository flightReservationRepository;

    @Override
    public List<Hotel_booking> getHotelBooking() {
        hotelBookingRepository.findAll();
        List<Hotel_booking> booking = hotelBookingRepository.findAll();
        for (int i=0; i< booking.size();i++){
            System.out.println(booking.get(i).getPaymentMethodH());
        }
        return booking;
    }

    @Override
    public ResponseHotelBookingDTO postHotelBooking(PayloadHotelsDTO payloadHotelsDTO, String status) {
        // Extract data and save user in db
        List<Users> usersList = usersRepository.findAll();
        Users user = new Users();
        for (Users u:usersList) {
            if(u.getUserName().equals(payloadHotelsDTO.getUsername())){
                user=u;
                break;
            }
        }
        user.setId_user(user.getId_user());
        user.setUserName(payloadHotelsDTO.getUsername());
        usersRepository.save(user);
        // Set paymentMethod
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setType(payloadHotelsDTO.getBooking().getPaymentMethod().getType());
        paymentMethod.setNumber(payloadHotelsDTO.getBooking().getPaymentMethod().getNumber());
        paymentMethod.setDues(payloadHotelsDTO.getBooking().getPaymentMethod().getDues());
        paymentMethodRepository.save(paymentMethod);
        // Set booking
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
            person.setBirth_date(p.getBirth_date());
            person.setMail(p.getMail());
            peopleRepository.save(person);
            Hotel_people person2 = new Hotel_people();
            person2.setHotel_booking_p(booking);
            person2.setPeople_hb(person);
            hotelPeopleRepository.save(person2);
        });
        return new ResponseHotelBookingDTO("Reserva de hotel dada de alta correctamente.");
    }

    @Override
    public ResponseHotelBookingDTO updateHotelBooking(PayloadHotelsDTO payloadHotelsDTO, int id) {
        Optional<Hotel_booking> booking = hotelBookingRepository.findById(id);
        if(!booking.isPresent()){
            throw new ObjectNotFoundException(1, "No se puede editar un registro inexistente.");
        }
        // Booking
        booking.get().setDateFrom(payloadHotelsDTO.getBooking().getDateFrom());
        booking.get().setDateTo(payloadHotelsDTO.getBooking().getDateTo());
        booking.get().setDestination(payloadHotelsDTO.getBooking().getDestination());
        booking.get().setHotelCode(payloadHotelsDTO.getBooking().getHotelCode());
        booking.get().setPeopleAmount(payloadHotelsDTO.getBooking().getPeopleAmount());
        booking.get().setRoomType(payloadHotelsDTO.getBooking().getRoomType());
        // Payment method
        booking.get().getPaymentMethodH().setDues(payloadHotelsDTO.getBooking().getPaymentMethod().getDues());
        booking.get().getPaymentMethodH().setNumber(payloadHotelsDTO.getBooking().getPaymentMethod().getNumber());
        booking.get().getPaymentMethodH().setType(payloadHotelsDTO.getBooking().getPaymentMethod().getType());
        // User
        booking.get().getUserH().setUserName(payloadHotelsDTO.getUsername());
        // People
        List<Hotel_people> allPeople = hotelPeopleRepository.findAll();
        List<Hotel_people> people =  allPeople.stream().filter(lp ->
                lp.getHotel_booking_p().getBooking_id() ==
                        (booking.get().getBooking_id())).collect(Collectors.toList());
        List<PeopleDTO> p = payloadHotelsDTO.getBooking().getPeople();
        for (int i=0; i < payloadHotelsDTO.getBooking().getPeopleAmount(); i++){
            Optional<People> person = peopleRepository.findById(people.get(i).getPeople_hb().getId_people());
            person.get().setDni(p.get(i).getDni());
            person.get().setName(p.get(i).getName());
            person.get().setLastname(p.get(i).getLastname());
            person.get().setBirth_date(p.get(i).getBirth_date());
            person.get().setMail(p.get(i).getMail());
            peopleRepository.save(person.get());
        }
        hotelBookingRepository.save(booking.get());
        return new ResponseHotelBookingDTO("Reserva de hotel modificada correctamente.");
    }

    @Override
    public ResponseHotelBookingDTO deleteHotelBooking(int id) {
        Optional<Hotel_booking> booking = hotelBookingRepository.findById(id);
        if(!booking.isPresent()){
            throw new ObjectNotFoundException(1, "No se puede eliminar un registro inexistente.");
        }
        int idUser = booking.get().getUserH().getId_user();
        int idPaymentMethod = booking.get().getPaymentMethodH().getId_paymentMethod();
        List<Hotel_people> listpeople = hotelPeopleRepository.findAll();
        List<Hotel_people> people =  listpeople.stream().filter(lp ->
                lp.getHotel_booking_p().getBooking_id() ==
                        (booking.get().getBooking_id())).collect(Collectors.toList());
        for (int i=0; i < people.size(); i++){
            Optional<People> person = peopleRepository.findById(people.get(i).getPeople_hb().getId_people());
            peopleRepository.deleteById(person.get().getId_people());
        }
        // Delete booking
        hotelBookingRepository.deleteById(id);
        // Delete payment
        paymentMethodRepository.deleteById(idPaymentMethod);
        // Delete user
        usersRepository.deleteById(idUser);
        // Delete people_booking
        return new ResponseHotelBookingDTO("Reserva de hotel dada de baja correctamente.");
    }

    // Req #4
    @Override
    public ObjectNode top3(){
        List<Users> usersList = new ArrayList<>();
        List<Hotel_booking> bookingsList = new ArrayList<>();
        List<Flight_reservation> flightReservationsList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode rootNode = mapper.createObjectNode();
        ArrayList<ObjectNode> array = new ArrayList<ObjectNode>(10);
        for (Hotel_booking booking:hotelBookingRepository.findAll()) {
            booking.setPaymentMethodH(null);
            booking.getUserH().setHotel_booking(null);
            booking.setHotel_people(null);
            bookingsList.add(booking);
        }
        for (Flight_reservation reservation:flightReservationRepository.findAll()) {
            reservation.setPaymentMethodF(null);
            reservation.getUserF().setFlight_reservation(null);
            reservation.getUserF().setHotel_booking(null);
            reservation.setFlight_people(null);
            flightReservationsList.add(reservation);
        }
        int helperIndex = 0;
        for (Users user:usersRepository.findAll()) {
            user.setHotel_booking(null);
            user.setFlight_reservation(null);
            user.setTouristicPackagesid(null);
            usersList.add(user);
            int bookingQuantity = 0;
            for (int i = 0; i < bookingsList.size(); i++) {
                if (bookingsList.get(i).getUserH().getId_user()==user.getId_user()){
                    bookingQuantity++;
                }
            }
            for (int i = 0; i < flightReservationsList.size(); i++) {
                if (flightReservationsList.get(i).getUserF().getId_user()==user.getId_user()){
                    bookingQuantity++;
                }
            }
            rootNode.set(String.valueOf(helperIndex), mapper.createObjectNode()
                    .put("booking_quantity", bookingQuantity)
                    .put("client_id", user.getId_user())
                    .put("client_name", user.getUserName())
                    .put("client_lastname", user.getId_user()));
            helperIndex++;
        }
        System.out.println(rootNode.getClass());
        /*{
            "clients": [
                {
                    "top_number": int,
                    ***** "year": int,
                    "booking_quantity": int,
                    ***** "total_amount": Double,
                    "client_id": int,
                    "client_name": String,
                    "client_lastname": String
                }
            ]
        }
        */
        return rootNode;
    }
}
