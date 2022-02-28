package com.example.demo.dto.hotelsDTOS;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HotelsDTO {
    @NotNull(message = "Se debe ingresar el campo 'name'")
    @NotBlank(message = "El campo 'name' no debe estar vacio")
    private String name;

    @NotNull(message = "Se debe ingresar el campo 'place'")
    @NotBlank(message = "El campo 'place' no debe estar vacio")
    private String place;
    @NotNull(message = "Se debe ingresar el campo 'roomType'")
    @NotBlank(message = "El campo 'roomType' no debe estar vacio")
    private String roomType;
    @NotNull(message = "Se debe ingresar el campo 'roomPrice")
    private double roomPrice;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Se debe ingresar el campo 'disponibilityDateFrom'")
    private Date disponibilityDateFrom;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Se debe ingresar el campo 'disponibilityDateTo")
    private Date disponibilityDateTo;
}
