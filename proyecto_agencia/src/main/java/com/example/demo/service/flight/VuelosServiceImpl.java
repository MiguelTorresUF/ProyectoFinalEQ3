package com.example.demo.service.flight;

import org.springframework.stereotype.Service;

/**
 * @author Vanessa Rocha
 */
/** Servicio de Vuelos implementa de VuelosService*/
@Service
public class VuelosServiceImpl implements VuelosService{
//    /** Instancia a la clase VuelosRepository*/
//    @Autowired
//    VuelosRepository vuelosRepository;
//
//    /** Método para obtener todos los vuelos
//     * @return una lista de vuelos */
//    @Override
//    public ResponseFlightsDTO getFlights(){
//
//        List<Flights> flights = vuelosRepository.getDatabaseFlights();
//        ResponseFlightsDTO response = new ResponseFlightsDTO(flights);
//
//        if (flights.size() == 0){
//            throw new ListEmptyException("La lista se encuentra vacia");
//        }
//        return response;
//    }
//
//    /** Método para obtener todos los vuelos disponibles segun los parametros
//     * @param dateFrom fecha inicial
//     * @param dateTo fecha final
//     * @param origin luegar de origen
//     * @param destination lugar de destino
//     * @return una lista de vuelos */
//    @Override
//    public ResponseFlightsDTO getFlightsAvailable(Date dateFrom, Date dateTo, String origin, String destination){
//        List<Flights> flight = vuelosRepository.getDatabaseFlights();
//
//        if (flight.size() == 0){
//            throw new ListEmptyException("La lista se encuentra vacia");
//        }
//
//        validFlightsParams(dateFrom, dateTo, origin, destination);
//        List<Flights> flights = flight.stream().filter(hotel ->
//            (hotel.getDateFrom().equals(dateFrom) || hotel.getDateFrom().before(dateFrom)) &&
//            (hotel.getDateTo().equals(dateTo) || hotel.getDateTo().after(dateTo)) &&
//            hotel.getOrigin().equals(origin) &&
//            hotel.getDestination().equals(destination)).collect(Collectors.toList());
//
//        ResponseFlightsDTO response = new ResponseFlightsDTO(flights);
//        return response;
//    }
//
//    /** Método para reservar un vuelo
//     * @param payloadDTO objeto con los datos necesarios pare reservar
//     * @param status estatus de su reservación
//     * @return un objeto con los datos que ingreso el usuario, su monto por los asientos comprados, su interes si paga con tarjeta y el total*/
//    @Override
//    public ResponseReservationFlight postFlightReservation(PayloadFlightsDTO payloadDTO, String status){
//        ResponseStatusCode responseStatusCode = new ResponseStatusCode();
//
//        //Metodo para validar
//        validFlightsParams(payloadDTO.getFlightReservation().getDateFrom(), payloadDTO.getFlightReservation().getDateTo(), payloadDTO.getFlightReservation().getOrigin(), payloadDTO.getFlightReservation().getDestination());
//
//        //filtrado
//        int precioPorVuelo = vuelosRepository.getDatabaseFlights().stream().
//                filter(
//                flight ->
//                    (flight.getOrigin().equals(payloadDTO.getFlightReservation().getOrigin()) &&
//                    flight.getDestination().equals(payloadDTO.getFlightReservation().getDestination())) &&
//                    (flight.getSeatType().equals(payloadDTO.getFlightReservation().getSeatsType())) &&
//                    (flight.getFlightNumber().equals(payloadDTO.getFlightReservation().getFlightNumber())))
//                .mapToInt(Flights::getAmount).
//                sum();
//
//        if(precioPorVuelo == 0 ){
//            throw new ListEmptyException("No se encontro el vuelo ingresado");
//        }
//
//        //Validar el tipo de metodo
//        int interest = validPaymentMethod(payloadDTO.getFlightReservation().getPaymentMethod().getType(), payloadDTO.getFlightReservation().getPaymentMethod().getDues());
//
//
//        //precio x noche
//        double amount = precioPorVuelo * payloadDTO.getFlightReservation().getSeats();
//
////        if(Objects.equals(payloadDTO.getFlightReservation().getPaymentMethod().getType(), "CREDITO")){
////            interest = 5.5;
//        double porciento = interest*0.01;
//        double total = (amount * porciento) + amount;
////        }else {
////            interest = 0.0;
////            total = amount;
////        }
//
//        //return
//        String[] parts = status.split(" ");
//        String codeString = parts[0];
//        int codeInt = Integer.parseInt(codeString);
//
//        if(codeInt == 200){
//            responseStatusCode.setCode(codeInt);
//            responseStatusCode.setMessage("El proceso terminó satisfactoriamente");
//        }
//
//        ResponseReservationFlight response = new ResponseReservationFlight(payloadDTO.getUserName(), amount, interest, total, payloadDTO.getFlightReservation(), responseStatusCode);
//        return response;
//    }
//
//    private void validFlightsParams(Date dateFrom, Date dateTo, String origin, String destination){
//        if(destination == null || destination == ""){
//            throw new NotDestinationException("El destino ingresado no existe.");
//        }
//        if(origin == null || origin == ""){
//            throw new NotOriginException("El origen ingresado no existe.");
//        }
//
//        //Verifica el destino
//        List<Flights> listFlights = vuelosRepository.getDatabaseFlights();
//
//        List<Flights> newList = listFlights.stream().filter(
//                        vuelo -> (vuelo.getDestination().equals(destination)) &&
//                                vuelo.getOrigin().equals(origin))
//                .collect(Collectors.toList());
//
//        //Si no hay ningun destino que se llame como el que ingresamos, manda la excepcion
//        if(newList.size() == 0)
//        {
//            throw new NotDestinationException("El destino u el origen ingresado no existen.");
//        }
//
//        //compara fechas
//        if(dateFrom.compareTo(dateTo) > 0)
//        {
//            throw new BadDateException("Alguna fecha es incorrecta. La fecha de entrada tiene que ser menor que la de salida.");
//        }
//    }
//
//
//    private int validPaymentMethod(String type, int dues) {
//        int interest = 0;
//        if(Objects.equals(type,"DEBITO")) {
//            switch (dues) {
//                case 1:
//                    interest = 0;
//                    break;
//                default: throw new PaymentMethodException("El Tipo de pago que hiciste fue con DEBITO por favor poner 1 en su couta de pago.");
//            }
//        }
//        else if(Objects.equals(type, "CREDITO")){
//            switch(dues) {
//                case 1:
//                case 2:
//                case 3:
//                    interest = 5;
//                    break;
//
//                case 4:
//                case 5:
//                case 6:
//                    interest = 10;
//                    break;
//
//                case 10:
//                case 11:
//                case 12:
//                    interest = 20;
//                    break;
//
//                case 16:
//                case 17:
//                case 18:
//                    interest = 30;
//                    break;
//
//                default: throw new InterestNotValidException("Ingrese un interes valido. (1-3, 4-6, 10-12, 16-18)");
//            }
//        }else{
//            throw new CashInvalidException("El metodo de pago no es el correcto.");
//        }
//        return interest;
//    }
}
