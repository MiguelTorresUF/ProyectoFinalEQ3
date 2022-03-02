package com.example.demo.dto.packagesDTOS;

import com.example.demo.model.TourBOR;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TouristicPackageDTO {
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date creation_date;
    @NotNull(message = "Se debe ingresar el 'userid'")
    private int userid;
    //@NotEmpty(message = "Se debe ingresar los id de reservacion")
    private TourBORDTO tourBOR;
}
