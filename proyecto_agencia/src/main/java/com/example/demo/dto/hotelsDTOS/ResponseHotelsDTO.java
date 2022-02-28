package com.example.demo.dto.hotelsDTOS;


import com.example.demo.model.Hotels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseHotelsDTO {

    private List<Hotels> hotels;

}
