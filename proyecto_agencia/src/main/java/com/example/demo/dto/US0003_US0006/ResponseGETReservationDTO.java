package com.example.demo.dto.US0003_US0006;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGETReservationDTO {
    private List<FlightReservationDTO> flightGetReservationDTOList;
}
