package com.example.demo.service.flight;

import com.example.demo.dto.flightsDTOS.FlightsDTO;
import com.example.demo.dto.flightsDTOS.ResponseFlightsDTO;
import com.example.demo.dto.flightsDTOS.ResponsePPDFlightDTO;
import com.example.demo.exception.BadDateException;
import com.example.demo.exception.ListEmptyException;
import com.example.demo.exception.NotDestinationException;
import com.example.demo.exception.NotOriginException;
import com.example.demo.model.Flights;
import com.example.demo.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class VuelosServiceImpl implements VuelosService{
    @Autowired
    FlightRepository flightRepository;

    //BUSCAR TODOS
    @Override
    @Transactional(readOnly = true)
    public Iterable<Flights> findAll() {
        return flightRepository.findAll();
    }

    //BUSCAR POR FILTROS
    @Override
    @Transactional(readOnly = true)
    public ResponseFlightsDTO getFlightsAvailable(Date dateFrom, Date dateTo, String origin, String destination) {
        List<Flights> flight = flightRepository.findAll();

        if (flight.size() == 0){
            throw new ListEmptyException("La lista se encuentra vacia");
        }

        validFlightsParams(dateFrom, dateTo, origin, destination);
        List<Flights> flights = flight.stream().filter(vuelo ->
                (vuelo.getGoingDate().equals(dateFrom) || vuelo.getGoingDate().before(dateFrom)) &&
                        (vuelo.getReturnDate().equals(dateTo) || vuelo.getReturnDate().after(dateTo)) &&
                        vuelo.getOrigin().equals(origin) &&
                        vuelo.getDestination().equals(destination)).collect(Collectors.toList());

        ResponseFlightsDTO response = new ResponseFlightsDTO(flights);
        return response;
    }

    //AGREGAR
    @Override
    public Flights save(Flights flights) {
        return flightRepository.save(flights);
    }

    //ACTUALIZAR
    @Override
    public ResponsePPDFlightDTO update(FlightsDTO flights, String flightNumber) {
        List<Flights> list = flightRepository.findAll();
        Optional<Flights> listFilter = list.stream()
                .filter(
                    l -> l.getFlightNumber().equals(flightNumber)).findFirst();


        listFilter.get().setName(flights.getName());
        listFilter.get().setOrigin(flights.getOrigin());
        listFilter.get().setDestination(flights.getDestination());
        listFilter.get().setSeatType(flights.getSeatType());
        listFilter.get().setFlightPrice(flights.getFlightPrice());
        listFilter.get().setGoingDate(flights.getGoingDate());
        listFilter.get().setReturnDate(flights.getReturnDate());

        flightRepository.save(listFilter.get());

        ResponsePPDFlightDTO response = new ResponsePPDFlightDTO("Vuelo modificado correctamente");
        return response;
    }


    //BORRAR
    @Override
    public ResponsePPDFlightDTO deleteFlight(String flightNumber) {
        List<Flights> list = flightRepository.findAll();

        Optional<Flights> listFilter = list.stream()
                .filter(
                    l -> l.getFlightNumber().equals(flightNumber)).findFirst();


        if(listFilter.isEmpty()){
            ResponsePPDFlightDTO response = new ResponsePPDFlightDTO("El vuelo no se encuentra registrado.");
            return response;
        }

        int id = listFilter.get().getId_vuelo();
        flightRepository.deleteById(id);

        ResponsePPDFlightDTO response = new ResponsePPDFlightDTO("Vuelo eliminado correctamente");
        return response;
    }




    private void validFlightsParams(Date dateFrom, Date dateTo, String origin, String destination){
        if(destination == null || destination == ""){
            throw new NotDestinationException("El destino ingresado no existe.");
        }
        if(origin == null || origin == ""){
            throw new NotOriginException("El origen ingresado no existe.");
        }

        //Verifica el destino
        List<Flights> listFlights = flightRepository.findAll();

        List<Flights> newList = listFlights.stream().filter(
                        vuelo -> (vuelo.getDestination().equals(destination)) &&
                                vuelo.getOrigin().equals(origin))
                .collect(Collectors.toList());

        //Si no hay ningun destino que se llame como el que ingresamos, manda la excepcion
        if(newList.size() == 0)
        {
            throw new NotDestinationException("El destino u el origen ingresado no existen.");
        }

        //compara fechas
        if(dateFrom.compareTo(dateTo) > 0)
        {
            throw new BadDateException("Alguna fecha es incorrecta. La fecha de entrada tiene que ser menor que la de salida.");
        }
    }
}

