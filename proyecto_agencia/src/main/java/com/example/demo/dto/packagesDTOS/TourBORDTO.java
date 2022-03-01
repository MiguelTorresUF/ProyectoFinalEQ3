package com.example.demo.dto.packagesDTOS;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourBORDTO {
    private BookingOrReservationDTO book_res_id1;
    private BookingOrReservationDTO book_res_id2;
}
