package com.example.demo.dto.US0004_US0005;

import com.example.demo.model.Flights;
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
/** Representa la lista de vuelos que se va a retornar en el cuarto y quinto endpoint
 */
public class ResponseFlightsDTO {
    private List<Flights> flights;
}
