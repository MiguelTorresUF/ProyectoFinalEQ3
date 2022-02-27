package com.example.demo.service.flight;

import com.example.demo.dto.flightsDTOS.ResponseFlightsDTO;
import com.example.demo.exception.BadDateException;
import com.example.demo.exception.ListEmptyException;
import com.example.demo.exception.NotDestinationException;
import com.example.demo.exception.NotOriginException;
import com.example.demo.model.Flights;
import com.example.demo.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class VuelosServiceImpl implements VuelosService{
    @Autowired
    FlightRepository flightRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Flights> findAll() {
        return flightRepository.findAll();
    }


    @Override
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

    @Override
    public Flights save(Flights flights) {
        return flightRepository.save(flights);
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
