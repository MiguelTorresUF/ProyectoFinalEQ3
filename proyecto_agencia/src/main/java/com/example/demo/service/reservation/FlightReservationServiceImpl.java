package com.example.demo.service.reservation;

import com.example.demo.dto.ResponseFlightReservationDTO;
import com.example.demo.dto.US0003_US0006.*;
import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlightReservationServiceImpl implements FlightReservationService {

    @Autowired
    FlightReservationRepository flightReservationRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    FlightPeopleRepository flightPeopleRepository;

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PaymentMethodRepository paymentMethod;



    //Reservacion de un vuelo
    @Override
    public ResponseFlightReservationDTO postFlightReservation (PayloadFlightsDTO payloadDTO, String status){
        ResponseStatusCode responseStatusCode = new ResponseStatusCode();

        //Metodo para validar
        validFlightsParams(payloadDTO.getFlightReservation().getGoingDate(), payloadDTO.getFlightReservation().getReturnDate(), payloadDTO.getFlightReservation().getOrigin(), payloadDTO.getFlightReservation().getDestination());

        //filtrado
        List<Flights> flightsList = flightRepository.findAll();
        List<Flights> flightsListFilter = flightsList.stream().
                filter(
                        flight ->
                                (flight.getOrigin().equals(payloadDTO.getFlightReservation().getOrigin()) &&
                                        flight.getDestination().equals(payloadDTO.getFlightReservation().getDestination())) &&
                                        (flight.getSeatType().equals(payloadDTO.getFlightReservation().getSeatType())) &&
                                        (flight.getFlightNumber().equals(payloadDTO.getFlightReservation().getFlightNumber()))).collect(Collectors.toList());

        if(flightsListFilter.size() == 0 ){
            throw new ListEmptyException("No se encontro el vuelo ingresado");
        }

        double precioPorVuelo =  flightsListFilter.stream().mapToDouble(Flights::getFlightPrice).findFirst().getAsDouble();

        //Validar el tipo de metodo
        int interest = validPaymentMethod(payloadDTO.getFlightReservation().getPaymentMethod().getType(), payloadDTO.getFlightReservation().getPaymentMethod().getDues());


        //precio x noche
        double amount = precioPorVuelo * payloadDTO.getFlightReservation().getSeats();

        double porciento = interest*0.01;
        double total = (amount * porciento) + amount;


        //Return
        String[] parts = status.split(" ");
        String codeString = parts[0];
        int codeInt = Integer.parseInt(codeString);

        if(codeInt == 200){
            responseStatusCode.setCode(codeInt);
            responseStatusCode.setMessage("Reserva de vuelo dada de alta correctamente");

            PaymentMethod payment = new PaymentMethod();//modelo
            payment.setType(payloadDTO.getFlightReservation().getPaymentMethod().getType());
            payment.setNumber(payloadDTO.getFlightReservation().getPaymentMethod().getNumber());
            payment.setDues(payloadDTO.getFlightReservation().getPaymentMethod().getDues());

            // se inserta el usuario
            Users users = new Users(); //modelo
            users.setUserName(payloadDTO.getUserName());
            users.setId_user(users.getId_user());

            //se inserta la reservacion
            Flight_reservation aux = new Flight_reservation();
            aux.setDestination(payloadDTO.getFlightReservation().getDestination());
            aux.setOrigin(payloadDTO.getFlightReservation().getOrigin());
            aux.setFlightNumber(payloadDTO.getFlightReservation().getFlightNumber());
            aux.setGoingDate(payloadDTO.getFlightReservation().getGoingDate());
            aux.setSeatType(payloadDTO.getFlightReservation().getSeatType());
            aux.setReturnDate(payloadDTO.getFlightReservation().getReturnDate());
            aux.setSeats(payloadDTO.getFlightReservation().getSeats());
            aux.setAmount_reservation(total);
            aux.setUserF(users);
            aux.setPaymentMethodF(payment);

            //para isertar en people segun lo que se obtenga en el postman
            List<PeopleDTO> p = payloadDTO.getFlightReservation().getPeople();

            p.forEach(flight -> {
                People people = new People();
                System.out.println(flight);
                people.setDni(flight.getDni());
                people.setName(flight.getName());
                people.setLastname(flight.getLastname());
                people.setBirth_date(flight.getBirth_date());
                people.setMail(flight.getMail());

                List<People> people1 = new ArrayList<>();
                people1.add(people);

                System.out.println(people.getName());

                //se inserta el Relacion tabla people y reservation
                Flight_people flight_people = new Flight_people();
                flight_people.setFlight_reservation_p(aux);
                flight_people.setPeople_fr(people);

                flightPeopleRepository.save(flight_people);
            });
        }

        ResponseFlightReservationDTO response = new ResponseFlightReservationDTO("Reserva de vuelo dada de alta correctamente.");
        return response;
    }



    @Override
    //Editar una reserva de vuelo
    public ResponseFlightReservationDTO updateReservation(PayloadFlightsDTO payloadDTO, int idflight_reservation) {
        Optional<Flight_reservation> list = flightReservationRepository.findById(idflight_reservation);

        if (list.isEmpty()){
            throw new ListEmptyException("No se encontró el vuelo ingresado");
        }
        //Metodo para validar
        validFlightsParams(payloadDTO.getFlightReservation().getGoingDate(), payloadDTO.getFlightReservation().getReturnDate(), payloadDTO.getFlightReservation().getOrigin(), payloadDTO.getFlightReservation().getDestination());

        list.get().getPaymentMethodF().setType(payloadDTO.getFlightReservation().getPaymentMethod().getType());
        list.get().getPaymentMethodF().setNumber(payloadDTO.getFlightReservation().getPaymentMethod().getNumber());
        list.get().getPaymentMethodF().setDues(payloadDTO.getFlightReservation().getPaymentMethod().getDues());

        // se inserta el usuario
        list.get().getUserF().setUserName(payloadDTO.getUserName());


        //filtrado
        List<Flights> flightsList = flightRepository.findAll();
        List<Flights> flightsListFilter = flightsList.stream().
                filter(
                        flight ->
                                (flight.getOrigin().equals(payloadDTO.getFlightReservation().getOrigin()) &&
                                        flight.getDestination().equals(payloadDTO.getFlightReservation().getDestination())) &&
                                        (flight.getSeatType().equals(payloadDTO.getFlightReservation().getSeatType())) &&
                                        (flight.getFlightNumber().equals(payloadDTO.getFlightReservation().getFlightNumber()))).collect(Collectors.toList());

        if(flightsListFilter.size() == 0 ){
            throw new ListEmptyException("No se encontro el vuelo ingresado");
        }

        double precioPorVuelo =  flightsListFilter.stream().mapToDouble(Flights::getFlightPrice).findFirst().getAsDouble();

        //Validar el tipo de metodo
        int interest = validPaymentMethod(payloadDTO.getFlightReservation().getPaymentMethod().getType(), payloadDTO.getFlightReservation().getPaymentMethod().getDues());

        //precio x noche
        double amount = precioPorVuelo * payloadDTO.getFlightReservation().getSeats();

        double porciento = interest*0.01;
        double total = (amount * porciento) + amount;
        //se inserta la reservacion
        list.get().setDestination(payloadDTO.getFlightReservation().getDestination());
        list.get().setOrigin(payloadDTO.getFlightReservation().getOrigin());
        list.get().setFlightNumber(payloadDTO.getFlightReservation().getFlightNumber());
        list.get().setGoingDate(payloadDTO.getFlightReservation().getGoingDate());
        list.get().setSeatType(payloadDTO.getFlightReservation().getSeatType());
        list.get().setReturnDate(payloadDTO.getFlightReservation().getReturnDate());
        list.get().setSeats(payloadDTO.getFlightReservation().getSeats());
        list.get().setAmount_reservation(total);
        list.get().setUserF(list.get().getUserF());
        list.get().setPaymentMethodF(list.get().getPaymentMethodF());

        //para isertar en people segun lo que se obtenga en el postman
        List<Flight_people> listpeople = flightPeopleRepository.findAll();

        List<Flight_people> people =  listpeople.stream().filter(lp ->
                lp.getFlight_reservation_p().getIdflight_reservation() ==
                        (list.get().getIdflight_reservation())).collect(Collectors.toList());

        System.out.println(list.get().getIdflight_reservation() + " id user ");

        List<PeopleDTO> p = payloadDTO.getFlightReservation().getPeople();
        for (int i=0; i < people.size(); i++){
            Optional<People> pe = peopleRepository.findById(people.get(i).getPeople_fr().getId_people());

            pe.get().setDni(p.get(i).getDni());
            pe.get().setName(p.get(i).getName());
            pe.get().setLastname(p.get(i).getLastname());
            pe.get().setBirth_date(p.get(i).getBirth_date());
            pe.get().setMail(p.get(i).getMail());

            peopleRepository.save(pe.get());
        }
        flightReservationRepository.save(list.get());
        ResponseFlightReservationDTO response = new ResponseFlightReservationDTO("Reserva de vuelo modificada correctamente.");
        return response;
    }

    @Override
    //Delete una reserva de vuelo
    public ResponseFlightReservationDTO deleteReservationFlight(int idflight_reservation){
        Optional<Flight_reservation> list = flightReservationRepository.findById(idflight_reservation);

        Optional<PaymentMethod> pay = paymentMethod.findById( list.get().getPaymentMethodF().getId_paymentMethod());

        Optional<Users> users = usersRepository.findById(list.get().getUserF().getId_user());

        List<Flight_people> listpeople = flightPeopleRepository.findAll();
        List<Flight_people> people =  listpeople.stream().filter(lp ->
                lp.getFlight_reservation_p().getIdflight_reservation() ==
                        (list.get().getIdflight_reservation())).collect(Collectors.toList());

        for (int i=0; i < people.size(); i++){
            Optional<People> pe = peopleRepository.findById(people.get(i).getPeople_fr().getId_people());

            peopleRepository.deleteById(pe.get().getId_people());
        }

        int id = list.get().getIdflight_reservation();
        flightReservationRepository.deleteById(id);

        int pa = pay.get().getId_paymentMethod();
        paymentMethod.deleteById(pa);

        int u = users.get().getId_user();
        usersRepository.deleteById(u);

        ResponseFlightReservationDTO response = new ResponseFlightReservationDTO("Reserva de vuelo se elimino correctamente.");
        return response;

    }








    //-------validaciones
    //Validacion de destino
    public void validFlightsParams(Date goingDate, Date returnDate, String origin, String destination ){
        if(destination == null || destination == ""){
            throw new NotOriginException("El destino ingresado no existe");
        }
        if(origin == null || origin == ""){
            throw new NotOriginException("El origen ingresado no existe");
        }

        List<Flights> flight_reservation = flightRepository.findAll();

        List<Flights> newList = flight_reservation.stream().filter(
                        vuelo -> (vuelo.getDestination().equals(destination)) &&
                                vuelo.getOrigin().equals(origin))
                .collect(Collectors.toList());

        if(newList.size() == 0){
            throw new NotDestinationException("El destino u el origin ingresado no existen");
        }

        if (goingDate.compareTo(returnDate) > 0){
            throw new BadDateException("Alguna fecha es incorrecta. La fecha de entrada tiene que ser menor que la de salida");
        }
    }

    //Validacion del metodo de pago
    private int validPaymentMethod(String type, int dues) {
        int interest = 0;
        if(Objects.equals(type,"DEBITO") || Objects.equals(type,"DEBIT")) {
            switch (dues) {
                case 1:
                    interest = 0;
                    break;
                default: throw new PaymentMethodException("El Tipo de pago que hiciste fue con DEBITO por favor poner 1 en su couta de pago.");
            }
        }
        else if(Objects.equals(type, "CREDITO") || Objects.equals(type, "CREDIT")){
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
