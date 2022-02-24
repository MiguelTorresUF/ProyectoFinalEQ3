package com.example.demo.service.hotel;

import com.example.demo.dto.US0001_US0002.ResponseDTO;
import com.example.demo.dto.US0003_US0006.PayloadHotelsDTO;
import com.example.demo.dto.US0003_US0006.ResponseReservationHotel;
import com.example.demo.dto.US0003_US0006.ResponseStatusCode;
import com.example.demo.entity.Hotel;
import com.example.demo.exception.*;
import com.example.demo.repository.hotel.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Vanessa Rocha
 */
/** Servicio de Hotel implementa de HotelService*/
@Service
public class HotelServiceImpl implements HotelService{
    /** Instancia a la clase HotelRepository*/

    @Autowired
    HotelRepository hotelRepository;

//    public HotelServiceImpl(@Autowired HotelRepository hotelRepository) {
//        this.hotelRepository = hotelRepository;
//    }

    /** Método para obtener todos los hoteles
     * @return una lista de hoteles */
    @Override
    public ResponseDTO getHotels(){
        List<Hotel> hotels = hotelRepository.getDatabaseHotel();
        ResponseDTO response = new ResponseDTO(hotels);

        if (hotels.size() == 0){
            throw new ListEmptyException("La lista se encuentra vacia");
        }

        return response;
    }

    /** Método para obtener todos los hoteles disponibles segun los parametros
     * @param dateFrom fecha inicial
     * @param dateTo fecha final
     * @param destination destino
     * @return una lista de hoteles */
    @Override
    public ResponseDTO getHotelsAvailable(Date dateFrom, Date dateTo, String destination){
        List<Hotel> hotels = hotelRepository.getDatabaseHotel();

        if (hotels.size() == 0){
            throw new ListEmptyException("La lista se encuentra vacia");
        }

        validHotelsParams(dateFrom, dateTo, destination);

        /** Filtrado según los datos necesarios */
        List<Hotel> hoteles = hotels.stream().filter(hotel ->
                (hotel.getDateFrom().equals(dateFrom) || hotel.getDateFrom().before(dateFrom)) &&
                        (hotel.getDateTo().equals(dateTo) || hotel.getDateTo().after(dateTo)) &&
                        hotel.getDestination().equals(destination) && !hotel.isReserved()).collect(Collectors.toList());


        ResponseDTO response = new ResponseDTO(hoteles);
        return response;
    }

    /** Método para reservar un hotel
     * @param payloadDTO objeto con los datos necesarios pare reservar
     * @param status estatus de su reservación
     * @return un objeto con los datos que ingreso el usuario, su monto por las noches hospedado, su interes si paga con tarjeta y el total*/
    @Override
    public ResponseReservationHotel postBooking(PayloadHotelsDTO payloadDTO, String status){
        ResponseReservationHotel responseReservationHotel = new ResponseReservationHotel();
        ResponseStatusCode responseStatusCode = new ResponseStatusCode();

        validHotelsParams(payloadDTO.getBooking().getDateFrom(), payloadDTO.getBooking().getDateTo(), payloadDTO.getBooking().getDestination());

        //Restar dias
        Date dateFrom = payloadDTO.getBooking().getDateFrom();
        Date dateTo = payloadDTO.getBooking().getDateTo();
        long restaDias = dateTo.getTime()-dateFrom.getTime();

        TimeUnit time = TimeUnit.DAYS;
        long diffrence = time.convert(restaDias, TimeUnit.MILLISECONDS);

        //filtrado
        List<Hotel> h = hotelRepository.getDatabaseHotel();
        List<Hotel> hoteles = h.stream().filter(hotel ->
                (hotel.getDestination().equals(payloadDTO.getBooking().getDestination()) && hotel.getHotelCode().equals(payloadDTO.getBooking().getHotelCode())) &&
                        (hotel.getRoomType().equals(payloadDTO.getBooking().getRoomType())) && !hotel.isReserved()).collect(Collectors.toList());


        if(hoteles.size() == 0){
            throw new ListEmptyException("No se encontro el hotel ingresado");
        }

        //Validar tipo de cuarto con personas
        validTypeRoom(payloadDTO.getBooking().getPeopleAmount(), hoteles.stream().map(Hotel::getRoomType).findFirst().get());

        //Validar el tipo de metodo
        int interest = validPaymentMethod(payloadDTO.getBooking().getPaymentMethod().getType(),payloadDTO.getBooking().getPaymentMethod().getDues());

        //precio x noche
        int precioPorNoche =  hoteles.stream().mapToInt(Hotel::getAmount).findFirst().getAsInt();;
        double amount = precioPorNoche * diffrence;

        double porciento = interest*0.01;
        double total = (amount * porciento) + amount;

        //return
        responseReservationHotel.setStatusCode(responseStatusCode);
        String[] parts = status.split(" ");
        String codeString = parts[0];
        int codeInt = Integer.parseInt(codeString);

        if(codeInt == 200){
            responseStatusCode.setCode(codeInt);
            responseStatusCode.setMessage("El proceso terminó satisfactoriamente");
        }

        ResponseReservationHotel response = new ResponseReservationHotel(payloadDTO.getUsername(), amount, interest, total, payloadDTO.getBooking(), responseStatusCode);
        return response;
    }

    private void validHotelsParams(Date dateFrom, Date dateTo, String destination){


        if(destination == null || destination == ""){
                throw new NotDestinationException("El destino ingresado no existe.");
        }

        //Verifica el destino
        List<Hotel> listaHotel = hotelRepository.getDatabaseHotel();

        List<Hotel> newList = listaHotel.stream().filter(
                hotel -> (hotel.getDestination().equals(destination)))
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
