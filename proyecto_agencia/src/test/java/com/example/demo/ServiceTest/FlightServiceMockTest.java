package com.example.demo.ServiceTest;

import com.example.demo.dto.US0004_US0005.ResponseFlightsDTO;
import com.example.demo.dto.US0003_US0006.*;
import com.example.demo.model.Flights;
import com.example.demo.exception.*;
import com.example.demo.repository.FlightRepository;
import com.example.demo.service.flight.VuelosServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FlightServiceMockTest {
//    @Mock
//    FlightRepository vuelosRepository;
//
//    @InjectMocks
//    VuelosServiceImpl vuelosServiceImpl;
//
//    //Lista para testear
//    private List<Flights> flightsList(){
//        List<Flights> flightList = new ArrayList<>();
//        flightList.add(flight01());
//        flightList.add(flight02());
//        return flightList;
//    }
//
//    private Flights flight01(){
//        return new Flights("PIBA-1420","México","Canadá","Business",2320, new Date(2022,02,10),new Date(2022,03,15));
//    }
//
//    private Flights flight02(){
//        return new Flights("PIBA-1220","Puerto Iguazú","Buenos Aires","Business",3535, new Date(2022,02,11),new Date(2022,02,25));
//    }
//
//
//    @Test
//    @DisplayName("US 0004: Lista llena")
//    void testGetAllHotels()
//    {
//        //Arrange
//        ResponseFlightsDTO responseDTO;
//
//        //Act
//        when(vuelosRepository.getDatabaseFlights()).thenReturn(flightsList());
//        responseDTO = vuelosServiceImpl.getFlights();
//
//        // Assert
//        Assertions.assertTrue(!responseDTO.getFlights().isEmpty());
//    }
//
//    @Test
//    @DisplayName("US 0004: Lista vacia")
//    void testGetNotAllHotels()
//    {
//        //Arrange
//        List<Flights> vuelo = new ArrayList<>();
//
//        //Act
//        when(vuelosRepository.getDatabaseFlights()).thenReturn(vuelo);
//        //Se captura el error y se ingresa en una variable de tipo ListEmptyException
//        ListEmptyException listEmptyException = Assertions.assertThrows(
//                ListEmptyException.class,
//                ()-> vuelosServiceImpl.getFlights());
//
//        // Assert
//        Assertions.assertEquals("La lista se encuentra vacia", listEmptyException.getMessage());
//    }
//
//
//
//
//    //*************************************************************************************
//    //*************************************************************************************
//
//
//    @Test
//    @DisplayName("US 0005: Lista de hoteles disponibles")
//    void testGetAllHotelsAvailable()
//    {
//        //Arrange
//        ResponseFlightsDTO responseDTO;
//
//        //Act
//        when(vuelosRepository.getDatabaseFlights()).thenReturn(flightsList());
//        responseDTO = vuelosServiceImpl.getFlightsAvailable(
//                new Date(2022, 02,15),
//                new Date(2022, 02,20),
//                "México",
//                "Canadá");
//
//        System.out.println(responseDTO);
//        // Assert
//        Assertions.assertTrue(!responseDTO.getFlights().isEmpty());
//    }
//
//    @Test
//    @DisplayName("US 0005: El destino no existe.")
//    void testGetNotAllHotelsAvailable()
//    {
//        //Act
//        when(vuelosRepository.getDatabaseFlights()).thenReturn(flightsList());
//        NotDestinationException notDestinationException =
//                Assertions.assertThrows(NotDestinationException.class,
//                        ()-> vuelosServiceImpl.getFlightsAvailable(
//                                new Date(2022, 02,15),
//                                new Date(2022, 02,20),
//                                "México",
//                                "Cancun"
//                        )
//                );
//
//        // Assert
//        Assertions.assertEquals("El destino u el origen ingresado no existen.", notDestinationException.getMessage());
//    }
//
//
//    @Test
//    @DisplayName("US 0005: fechas mayores o menores.")
//    void testGetNotAllHotelsAvailable1()
//    {
//        //Arrange
//        String message = "Alguna fecha es incorrecta. La fecha de entrada tiene que ser menor que la de salida.";
//
//
//        //Act
//        when(vuelosRepository.getDatabaseFlights()).thenReturn(flightsList());
//
//        BadDateException badDateException =
//                Assertions.assertThrows(BadDateException.class,
//                        ()-> vuelosServiceImpl.getFlightsAvailable(
//                                new Date(2022, 04,03),
//                                new Date(2022, 02,20),
//                                "México",
//                                "Canadá"
//                        )
//                );
//
//        // Assert
//        Assertions.assertEquals(message, badDateException.getMessage());
//    }
//
//
//    @Test
//    @DisplayName("US 0005: Lista vacia.")
//    void testGetNotAllHotelsAvailable2()
//    {
//        //Arrange
//        List<Flights> vuelo = new ArrayList<>();
//
//        //Act
//        when(vuelosRepository.getDatabaseFlights()).thenReturn(vuelo);
//        ListEmptyException empty =
//                Assertions.assertThrows(ListEmptyException.class,
//                        ()-> vuelosServiceImpl.getFlightsAvailable(
//                                new Date(2022, 02,15),
//                                new Date(2022, 02,20),
//                                "México",
//                                "Canadá"));
//
//        // Assert
//        Assertions.assertEquals("La lista se encuentra vacia", empty.getMessage());
//    }
//
//
//
//    //*************************************************************************************
//    //*************************************************************************************
//
//    private PayloadFlightsDTO payloadFlightsDTO(){
//
//        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO(
//                "CREDITO",
//                "1233-3434-2321-1233",
//                3
//        );
//
//        ArrayList<PeopleDTO> peopleDTO = new ArrayList<>();
//        peopleDTO.add(new PeopleDTO(
//                "1233",
//                "Vanessa",
//                "Rocha",
//                new Date(2001, 05,22),
//                "vane@gmail.com"
//        ));
//
//        peopleDTO.add(new PeopleDTO(
//                "12331",
//                "Fernando",
//                "Vazquez",
//                new Date(2000, 03,18),
//                "fenando@gmail.com"
//        ));
//
//
//        FlightReservationDTO flightReservationDTO = new FlightReservationDTO();
//        flightReservationDTO.setDateFrom(new Date(2022, 02,15));
//        flightReservationDTO.setDateTo(new Date(2022, 02,20));
//        flightReservationDTO.setOrigin("México");
//        flightReservationDTO.setDestination("Canadá");
//        flightReservationDTO.setFlightNumber("PIBA-1420");
//        flightReservationDTO.setSeats(2);
//        flightReservationDTO.setSeatsType("Business");
//        flightReservationDTO.setPeople(peopleDTO);
//        flightReservationDTO.setPaymentMethod(paymentMethodDTO);
//
//        PayloadFlightsDTO payloadFlightlDTO = new PayloadFlightsDTO();
//        payloadFlightlDTO.setUserName("Vanessa");
//        payloadFlightlDTO.setFlightReservation(flightReservationDTO);
//        return payloadFlightlDTO;
//    }
//
//    @Test
//    @DisplayName("US 0003: Reserva de hotel")
//    void testPostBooking()
//    {
//        //Arrange
//        ResponseReservationFlight response;
//
//        //Act
//        when(vuelosRepository.getDatabaseFlights()).thenReturn(flightsList());
//        response = vuelosServiceImpl.postFlightReservation(payloadFlightsDTO(), String.valueOf(HttpStatus.OK));
//
//        System.out.println(response);
//
//        // Assert
//        Assertions.assertEquals(200, response.getStatusCode().getCode());
//    }
//
//    @Test
//    @DisplayName("US 0003: El destino no existe.")
//    void testNotPostBooking()
//    {
//
//        //Arrange
//        PayloadFlightsDTO payloadFlightsDTO = payloadFlightsDTO();
//        payloadFlightsDTO.getFlightReservation().setDestination("Perú");
//
//        //Act
//        when(vuelosRepository.getDatabaseFlights()).thenReturn(flightsList());
//        NotDestinationException notDestinationException =
//                Assertions.assertThrows(NotDestinationException.class,
//                        ()-> {
//                            vuelosServiceImpl.postFlightReservation(
//                                    payloadFlightsDTO,
//                                    String.valueOf(HttpStatus.OK)
//                            );
//                        }
//                );
//
//        // Assert
//        Assertions.assertEquals("El destino u el origen ingresado no existen.", notDestinationException.getMessage());
//    }
//
//
//    @Test
//    @DisplayName("US 0003: fechade entrada mayor.")
//    void testNotPostBooking1()
//    {
//        //Arrange
//        String message = "Alguna fecha es incorrecta. La fecha de entrada tiene que ser menor que la de salida.";
//        PayloadFlightsDTO payloadFlightsDTO = payloadFlightsDTO();
//        payloadFlightsDTO.getFlightReservation().setDateFrom(new Date(2022, 02,23));
//
//        //Act
//        when(vuelosRepository.getDatabaseFlights()).thenReturn(flightsList());
//
//        BadDateException badDateException =
//                Assertions.assertThrows(BadDateException.class,
//                        ()-> vuelosServiceImpl.postFlightReservation(
//                                payloadFlightsDTO,
//                                String.valueOf(HttpStatus.OK)
//                        )
//                );
//
//        // Assert
//        Assertions.assertEquals(message, badDateException.getMessage());
//    }
//
//
//    @Test
//    @DisplayName("US 0003: fecha de salida menor.")
//    void testNotPostBooking2()
//    {
//        //Arrange
//        String message = "Alguna fecha es incorrecta. La fecha de entrada tiene que ser menor que la de salida.";
//        PayloadFlightsDTO payloadFlightsDTO = payloadFlightsDTO();
//        payloadFlightsDTO.getFlightReservation().setDateTo(new Date(2022, 02,10));
//
//        //Act
//        when(vuelosRepository.getDatabaseFlights()).thenReturn(flightsList());
//
//        BadDateException badDateException =
//                Assertions.assertThrows(BadDateException.class,
//                        ()-> vuelosServiceImpl.postFlightReservation(
//                                payloadFlightsDTO,
//                                String.valueOf(HttpStatus.OK)
//                        )
//                );
//
//        // Assert
//        Assertions.assertEquals(message, badDateException.getMessage());
//    }
//
//    @Test
//    @DisplayName("US 0003: Hotel no encontrado")
//    void testNotPostBooking3()
//    {
//        //Arrange
//        String message = "No se encontro el vuelo ingresado";
//        PayloadFlightsDTO payloadFlightsDTO = payloadFlightsDTO();
//        payloadFlightsDTO.getFlightReservation().setSeatsType("Economy");
//
//        //Act
//        when(vuelosRepository.getDatabaseFlights()).thenReturn(flightsList());
//
//        ListEmptyException listEmpty =
//                Assertions.assertThrows(ListEmptyException.class,
//                        ()-> vuelosServiceImpl.postFlightReservation(
//                                payloadFlightsDTO,
//                                String.valueOf(HttpStatus.OK)
//                        )
//                );
//
//        // Assert
//        Assertions.assertEquals(message, listEmpty.getMessage());
//    }
//
//
//
//    @Test
//    @DisplayName("US 0003: Metodo de pago incompatible")
//    void testNotPostBooking5()
//    {
//        //Arrange
//        String message = "El Tipo de pago que hiciste fue con DEBITO por favor poner 1 en su couta de pago.";
//        PayloadFlightsDTO payloadFlightsDTO = payloadFlightsDTO();
//        payloadFlightsDTO.getFlightReservation().getPaymentMethod().setType("DEBITO");
//        payloadFlightsDTO.getFlightReservation().getPaymentMethod().setDues(6);
//
//        //Act
//        when(vuelosRepository.getDatabaseFlights()).thenReturn(flightsList());
//
//        PaymentMethodException paymentMethodException =
//                Assertions.assertThrows(PaymentMethodException.class,
//                        ()-> vuelosServiceImpl.postFlightReservation(
//                                payloadFlightsDTO,
//                                String.valueOf(HttpStatus.OK)
//                        )
//                );
//
//        // Assert
//        Assertions.assertEquals(message, paymentMethodException.getMessage());
//    }
//
//    @Test
//    @DisplayName("US 0003: Metodo de pago interes-tarjeta")
//    void testNotPostBooking6()
//    {
//        //Arrange
//        String message = "Ingrese un interes valido. (1-3, 4-6, 10-12, 16-18)";
//        PayloadFlightsDTO payloadFlightsDTO = payloadFlightsDTO();
//        payloadFlightsDTO.getFlightReservation().getPaymentMethod().setType("CREDITO");
//        payloadFlightsDTO.getFlightReservation().getPaymentMethod().setDues(19);
//
//        //Act
//        when(vuelosRepository.getDatabaseFlights()).thenReturn(flightsList());
//
//        InterestNotValidException interestNotValidException =
//                Assertions.assertThrows(InterestNotValidException.class,
//                        ()-> vuelosServiceImpl.postFlightReservation(
//                                payloadFlightsDTO,
//                                String.valueOf(HttpStatus.OK)
//                        )
//                );
//
//        // Assert
//        Assertions.assertEquals(message, interestNotValidException.getMessage());
//    }
//
//    @Test
//    @DisplayName("US 0003: Metodo de pago No valido")
//    void testNotPostBooking7()
//    {
//        //Arrange
//        String message = "El metodo de pago no es el correcto.";
//        PayloadFlightsDTO payloadFlightsDTO = payloadFlightsDTO();
//        payloadFlightsDTO.getFlightReservation().getPaymentMethod().setType("AGUACATE");
//        payloadFlightsDTO.getFlightReservation().getPaymentMethod().setDues(1);
//
//        //Act
//        when(vuelosRepository.getDatabaseFlights()).thenReturn(flightsList());
//
//        CashInvalidException cashInvalidException =
//                Assertions.assertThrows(CashInvalidException.class,
//                        ()-> vuelosServiceImpl.postFlightReservation(
//                                payloadFlightsDTO,
//                                String.valueOf(HttpStatus.OK)
//                        )
//                );
//
//        // Assert
//        Assertions.assertEquals(message, cashInvalidException.getMessage());
//    }

}
