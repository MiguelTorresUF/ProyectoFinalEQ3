package com.example.demo.dto.US0001_US0002;

import com.example.demo.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Vanessa Rocha
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
/** Representa la lista de hoteles que se va a retornar en el primer y segundo endpoint
 */
public class ResponseDTO {
    private List<Hotel> hotels;
}
