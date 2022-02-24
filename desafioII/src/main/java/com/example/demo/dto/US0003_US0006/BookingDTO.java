package com.example.demo.dto.US0003_US0006;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Vanessa Rocha
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
/** Representa la lista reservación de hoteles con los principales datos
 */
public class BookingDTO {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateFrom;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateTo;
    private String destination;
    private String hotelCode;
    @Digits(fraction = 0, integer = 1, message = "La cantidad de personas debe ser un valor numerico.")
    @Max(value = 5, message = "El maximo de personas es 5.")
    private int peopleAmount;
    private String roomType;
    private ArrayList<@Valid PeopleDTO> people;
    @Valid
    private PaymentMethodDTO paymentMethod;
}
