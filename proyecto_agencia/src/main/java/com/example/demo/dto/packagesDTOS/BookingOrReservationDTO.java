package com.example.demo.dto.packagesDTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingOrReservationDTO {
    private Integer id_booking;
    private Integer id_reservation;
}
