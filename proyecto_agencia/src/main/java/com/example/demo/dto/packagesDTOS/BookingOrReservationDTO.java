package com.example.demo.dto.packagesDTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingOrReservationDTO {
    private int id_booking;
    private int id_reservation;
}
