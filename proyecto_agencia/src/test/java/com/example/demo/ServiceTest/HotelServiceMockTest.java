package com.example.demo.ServiceTest;

import com.example.demo.dto.US0001_US0002.ResponseDTO;
import com.example.demo.dto.US0003_US0006.*;
import com.example.demo.model.Hotels;
import com.example.demo.exception.*;
import com.example.demo.repository.HotelRepository;
import com.example.demo.service.hotel.HotelServiceImpl;
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
public class HotelServiceMockTest {

//    @Mock
//    HotelRepository hotelRepository;
//
//    @InjectMocks
//    HotelServiceImpl hotelServiceImpl;
//
//    //Lista para testear
//    private List<Hotels> hotelList(){
//        List<Hotels> hotelList = new ArrayList<>();
//        hotelList.add(hotel01());
//        hotelList.add(hotel02());
//        return hotelList;
//    }
//
//    private Hotels hotel01(){
//        return new Hotels("CH-0002",
//                "Cataratas Hotel",
//                "Buenos Aires",
//                "Doble",
//                6300,
//                new Date(2022, 02,15),
//                new Date(2022, 02,20),
//                false);
//    }
//
//    private Hotels hotel02(){
//        return new Hotels("CH-0003",
//                "Cataratas Hotel 2",
//                "Puerto Iguazú",
//                "Triple",
//                8200,
//                new Date(2022, 02,10),
//                new Date(2022, 03,23),
//                false);
//    }
//
//
//    @Test
//    @DisplayName("US 0001: Lista llena")
//    void testGetAllHotels()
//    {
//        //Arrange
//        ResponseDTO responseDTO;
//
//        //Act
//        when(hotelRepository.getDatabaseHotel()).thenReturn(hotelList());
//        responseDTO = hotelServiceImpl.getHotels();
//
//        // Assert
//        Assertions.assertTrue(!responseDTO.getHotels().isEmpty());
//    }
//
//    @Test
//    @DisplayName("US 0001: Lista vacia")
//    void testGetNotAllHotels()
//    {
//        //Arrange
//        List<Hotels> hotels = new ArrayList<>();
//
//        //Act
//        when(hotelRepository.getDatabaseHotel()).thenReturn(hotels);
//        //Se captura el error y se ingresa en una variable de tipo ListEmptyException
//        ListEmptyException listEmptyException = Assertions.assertThrows(ListEmptyException.class, ()-> hotelServiceImpl.getHotels());
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
//    @DisplayName("US 0002: Lista de hoteles disponibles")
//    void testGetAllHotelsAvailable()
//    {
//        //Arrange
//        ResponseDTO responseDTO;
//
//        //Act
//        when(hotelRepository.getDatabaseHotel()).thenReturn(hotelList());
//        responseDTO = hotelServiceImpl.getHotelsAvailable(
//                new Date(2022, 02,15),
//                new Date(2022, 02,20),
//                "Puerto Iguazú");
//
//        System.out.println(responseDTO);
//        // Assert
//        Assertions.assertTrue(!responseDTO.getHotels().isEmpty());
//    }
//
//    @Test
//    @DisplayName("US 0002: El destino no existe.")
//    void testGetNotAllHotelsAvailable()
//    {
//        //Act
//        when(hotelRepository.getDatabaseHotel()).thenReturn(hotelList());
//        NotDestinationException notDestinationException =
//                Assertions.assertThrows(NotDestinationException.class,
//                        ()-> hotelServiceImpl.getHotelsAvailable(
//                                new Date(2022, 02,15),
//                                new Date(2022, 02,20),
//                                null));
//
//        // Assert
//        Assertions.assertEquals("El destino ingresado no existe.", notDestinationException.getMessage());
//    }
//
//
//    @Test
//    @DisplayName("US 0002: fechas mayores o menores.")
//    void testGetNotAllHotelsAvailable1()
//    {
//        //Arrange
//        String message = "Alguna fecha es incorrecta. La fecha de entrada tiene que ser menor que la de salida.";
//
//
//        //Act
//        when(hotelRepository.getDatabaseHotel()).thenReturn(hotelList());
//
//        BadDateException badDateException =
//                Assertions.assertThrows(BadDateException.class,
//                        ()-> hotelServiceImpl.getHotelsAvailable(
//                                new Date(2022, 02,20),
//                                new Date(2022, 02,15),
//                                "Puerto Iguazú"));
//
//        // Assert
//        Assertions.assertEquals(message, badDateException.getMessage());
//    }
//
//
//    @Test
//    @DisplayName("US 0002: Lista vacia.")
//    void testGetNotAllHotelsAvailable2()
//    {
//        //Arrange
//        List<Hotels> hotels = new ArrayList<>();
//
//
//        //Act
//        when(hotelRepository.getDatabaseHotel()).thenReturn(hotels);
//        ListEmptyException empty =
//                Assertions.assertThrows(ListEmptyException.class,
//                        ()-> hotelServiceImpl.getHotelsAvailable(
//                                new Date(2022, 02,15),
//                                new Date(2022, 02,20),
//                                "Puerto "));
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
//    private PayloadHotelsDTO payloadHotelDTO(){
//
//        PaymentMethodDTO paymentMethodDTO = new PaymentMethodDTO(
//                "CREDITO",
//                "1233-3434-2321-1233",
//                3
//        );
//
//        ArrayList<PeopleDTO> peopleDTO = new ArrayList<>();
//        peopleDTO.add(new PeopleDTO(
//                "12334324351",
//                "Vanessa",
//                "Rocha",
//                new Date(2001, 05,22),
//                "vane@gmail.com"
//        ));
//
//        peopleDTO.add(new PeopleDTO(
//                "12334324351",
//                "Fernando",
//                "Vazquez",
//                new Date(2000, 03,18),
//                "vane@gmail.com"
//        ));
//
//
//        BookingDTO bookingDTO = new BookingDTO();
//        bookingDTO.setDateFrom(new Date(2022, 02,15));
//        bookingDTO.setDateTo(new Date(2022, 02,20));
//        bookingDTO.setDestination("Buenos Aires");
//        bookingDTO.setHotelCode("CH-0002");
//        bookingDTO.setPeopleAmount(2);
//        bookingDTO.setRoomType("Doble");
//        bookingDTO.setPeople(peopleDTO);
//        bookingDTO.setPaymentMethod(paymentMethodDTO);
//
//        PayloadHotelsDTO payloadHotelDTO = new PayloadHotelsDTO();
//        payloadHotelDTO.setUsername("Vanessa");
//        payloadHotelDTO.setBooking(bookingDTO);
//        return payloadHotelDTO;
//    }
//
//    @Test
//    @DisplayName("US 0003: Reserva de hotel")
//    void testPostBooking()
//    {
//        //Arrange
//        ResponseReservationHotel response;
//
//        //Act
//        when(hotelRepository.getDatabaseHotel()).thenReturn(hotelList());
//        response = hotelServiceImpl.postBooking(payloadHotelDTO(), String.valueOf(HttpStatus.OK));
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
//        PayloadHotelsDTO payloadHotelDTO = payloadHotelDTO();
//        payloadHotelDTO.getBooking().setDestination("Mexico");
//
//        //Act
//        when(hotelRepository.getDatabaseHotel()).thenReturn(hotelList());
//        NotDestinationException notDestinationException =
//                Assertions.assertThrows(NotDestinationException.class,
//                        ()-> {
//                            hotelServiceImpl.postBooking(
//                                    payloadHotelDTO,
//                                    String.valueOf(HttpStatus.OK)
//                            );
//                        }
//                );
//
//        // Assert
//        Assertions.assertEquals("El destino ingresado no existe.", notDestinationException.getMessage());
//    }
//
//
//    @Test
//    @DisplayName("US 0003: fechade entrada mayor.")
//    void testNotPostBooking1()
//    {
//        //Arrange
//        String message = "Alguna fecha es incorrecta. La fecha de entrada tiene que ser menor que la de salida.";
//        PayloadHotelsDTO payloadHotelDTO = payloadHotelDTO();
//        payloadHotelDTO.getBooking().setDateFrom(new Date(2022, 02,23));
//
//        //Act
//        when(hotelRepository.getDatabaseHotel()).thenReturn(hotelList());
//
//        BadDateException badDateException =
//                Assertions.assertThrows(BadDateException.class,
//                        ()-> hotelServiceImpl.postBooking(
//                                payloadHotelDTO,
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
//        PayloadHotelsDTO payloadHotelDTO = payloadHotelDTO();
//        payloadHotelDTO.getBooking().setDateTo(new Date(2022, 02,10));
//
//        //Act
//        when(hotelRepository.getDatabaseHotel()).thenReturn(hotelList());
//
//        BadDateException badDateException =
//                Assertions.assertThrows(BadDateException.class,
//                        ()-> hotelServiceImpl.postBooking(
//                                payloadHotelDTO,
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
//        String message = "No se encontro el hotel ingresado";
//        PayloadHotelsDTO payloadHotelDTO = payloadHotelDTO();
//        payloadHotelDTO.getBooking().setRoomType("Triple");
//        payloadHotelDTO.getBooking().setPeopleAmount(3);
//
//        //Act
//        when(hotelRepository.getDatabaseHotel()).thenReturn(hotelList());
//
//        ListEmptyException listEmpty =
//                Assertions.assertThrows(ListEmptyException.class,
//                        ()-> hotelServiceImpl.postBooking(
//                                payloadHotelDTO,
//                                String.valueOf(HttpStatus.OK)
//                        )
//                );
//
//        // Assert
//        Assertions.assertEquals(message, listEmpty.getMessage());
//    }
//
//
//    @Test
//    @DisplayName("US 0003: Habitacion incompatible")
//    void testNotPostBooking4()
//    {
//        //Arrange
//        String message = "La habitacion no coincide con el numero de personas.";
//        PayloadHotelsDTO payloadHotelDTO = payloadHotelDTO();
//        payloadHotelDTO.getBooking().setRoomType("Doble");
//        payloadHotelDTO.getBooking().setPeopleAmount(3);
//
//        //Act
//        when(hotelRepository.getDatabaseHotel()).thenReturn(hotelList());
//
//        BadTypeRoomException badTypeRoom =
//                Assertions.assertThrows(BadTypeRoomException.class,
//                        ()-> hotelServiceImpl.postBooking(
//                                payloadHotelDTO,
//                                String.valueOf(HttpStatus.OK)
//                        )
//                );
//
//        // Assert
//        Assertions.assertEquals(message, badTypeRoom.getMessage());
//    }
//
//    @Test
//    @DisplayName("US 0003: Metodo de pago incompatible")
//    void testNotPostBooking5()
//    {
//        //Arrange
//        String message = "El Tipo de pago que hiciste fue con DEBITO por favor poner 1 en su couta de pago.";
//        PayloadHotelsDTO payloadHotelDTO = payloadHotelDTO();
//        payloadHotelDTO.getBooking().getPaymentMethod().setType("DEBITO");
//        payloadHotelDTO.getBooking().getPaymentMethod().setDues(6);
//
//        //Act
//        when(hotelRepository.getDatabaseHotel()).thenReturn(hotelList());
//
//        PaymentMethodException paymentMethodException =
//                Assertions.assertThrows(PaymentMethodException.class,
//                        ()-> hotelServiceImpl.postBooking(
//                                payloadHotelDTO,
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
//        PayloadHotelsDTO payloadHotelDTO = payloadHotelDTO();
//        payloadHotelDTO.getBooking().getPaymentMethod().setType("CREDITO");
//        payloadHotelDTO.getBooking().getPaymentMethod().setDues(19);
//
//        //Act
//        when(hotelRepository.getDatabaseHotel()).thenReturn(hotelList());
//
//        InterestNotValidException interestNotValidException =
//                Assertions.assertThrows(InterestNotValidException.class,
//                        ()-> hotelServiceImpl.postBooking(
//                                payloadHotelDTO,
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
//        PayloadHotelsDTO payloadHotelDTO = payloadHotelDTO();
//        payloadHotelDTO.getBooking().getPaymentMethod().setType("AGUACATE");
//        payloadHotelDTO.getBooking().getPaymentMethod().setDues(1);
//
//        //Act
//        when(hotelRepository.getDatabaseHotel()).thenReturn(hotelList());
//
//        CashInvalidException cashInvalidException =
//                Assertions.assertThrows(CashInvalidException.class,
//                        ()-> hotelServiceImpl.postBooking(
//                                payloadHotelDTO,
//                                String.valueOf(HttpStatus.OK)
//                        )
//                );
//
//        // Assert
//        Assertions.assertEquals(message, cashInvalidException.getMessage());
//    }

}
