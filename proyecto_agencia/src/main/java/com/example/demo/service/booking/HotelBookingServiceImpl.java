package com.example.demo.service.booking;

import com.example.demo.dto.ResponseHotelBookingDTO;
import com.example.demo.dto.US0003_US0006.PayloadHotelsDTO;
import com.example.demo.dto.US0003_US0006.PeopleDTO;
import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
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
    HotelRepository hotelRepository;

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
        validHotelsParams(payloadHotelsDTO.getBooking().getDateFrom(), payloadHotelsDTO.getBooking().getDateTo(), payloadHotelsDTO.getBooking().getDestination());

        //Restar dias
        Date dateFrom = payloadHotelsDTO.getBooking().getDateFrom();
        Date dateTo = payloadHotelsDTO.getBooking().getDateTo();
        long restaDias = dateTo.getTime()-dateFrom.getTime();

        TimeUnit time = TimeUnit.DAYS;
        long diffrence = time.convert(restaDias, TimeUnit.MILLISECONDS);

        //filtrado
        List<Hotels> h = hotelRepository.findAll();
        List<Hotels> hoteles = h.stream().filter(hotel ->
                (hotel.getPlace().equals(payloadHotelsDTO.getBooking().getDestination()) && hotel.getHotelCode().equals(payloadHotelsDTO.getBooking().getHotelCode())) &&
                        (hotel.getRoomType().equals(payloadHotelsDTO.getBooking().getRoomType())) && !hotel.isIsbooking()).collect(Collectors.toList());

        if(hoteles.size() == 0 ){
            throw new ListEmptyException("No se encontro el hotel ingresado");
        }

        //Validar tipo de cuarto con personas
        validTypeRoom(payloadHotelsDTO.getBooking().getPeopleAmount(), hoteles.stream().map(Hotels::getRoomType).findFirst().get());

        //Validar el tipo de metodo
        int interest = validPaymentMethod(payloadHotelsDTO.getBooking().getPaymentMethod().getType(),payloadHotelsDTO.getBooking().getPaymentMethod().getDues());

        //precio x noche
        double precioPorNoche =  hoteles.stream().mapToDouble(Hotels::getRoomPrice).findFirst().getAsDouble();
        double amount = precioPorNoche * diffrence;

        double porciento = interest*0.01;
        double total = (amount * porciento) + amount;

        // Extract data and save user in db
        Users user = new Users();
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
        booking.setAmount_booking(total);
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









    //---------validaciones
    private void validHotelsParams(Date dateFrom, Date dateTo, String destination){
        if(destination == null || destination == ""){
            throw new NotDestinationException("El destino ingresado no existe.");
        }

        //Verifica el destino
        List<Hotels> listaHotel = hotelRepository.findAll();

        List<Hotels> newList = listaHotel.stream().filter(
                        hotel -> (hotel.getPlace().equals(destination)))
                .collect(Collectors.toList());

        //Si no hay ningun destino que se llame como el que ingresamos, manda la excepcion
        if(newList.size() == 0)
        {
            throw new NotDestinationException("El destino ingresado no existe.");
        }

        //compara fechas
        if(dateFrom.compareTo(dateTo) > 0)
        {
            throw new BadDateException("Alguna fecha es incorrecta. La fecha de entrada tiene que ser menor que la de salida.");
        }
    }

    private void validTypeRoom(int peopleAmount, String typeRoom) {
        String message = "La habitacion no coincide con el numero de personas.";
        switch(peopleAmount)
        {
            case 1:
                if(!(typeRoom.equals("Single")))
                {
                    throw new BadTypeRoomException(message);
                }
                break;

            case 2:
                if(!(typeRoom.equals("Doble")))
                {
                    throw new BadTypeRoomException(message);
                }
                break;

            case 3:
                if(!(typeRoom.equals("Triple")))
                {
                    throw new BadTypeRoomException(message);
                }
                break;

            case 4:
            case 5:
                if(!(typeRoom.equals("Multiple")))
                {
                    throw new BadTypeRoomException(message);
                }
                break;
        }
    }

    private int validPaymentMethod(String type, int dues) {
        int interest = 0;
        if(Objects.equals(type,"DEBITO")) {
            switch (dues) {
                case 1:
                    interest = 0;
                    break;
                default: throw new PaymentMethodException("El Tipo de pago que hiciste fue con DEBITO por favor poner 1 en su couta de pago.");
            }
        }
        else if(Objects.equals(type, "CREDITO")){
            switch(dues) {
                case 1:
                case 2:
                case 3:
                    interest = 5;
                    break;

                case 4:
                case 5:
                case 6:
                    interest = 10;
                    break;

                case 10:
                case 11:
                case 12:
                    interest = 20;
                    break;

                case 16:
                case 17:
                case 18:
                    interest = 30;
                    break;

                default: throw new InterestNotValidException("Ingrese un interes valido. (1-3, 4-6, 10-12, 16-18)");
            }
        }else{
            throw new CashInvalidException("El metodo de pago no es el correcto.");
        }
        return interest;
    }
}
