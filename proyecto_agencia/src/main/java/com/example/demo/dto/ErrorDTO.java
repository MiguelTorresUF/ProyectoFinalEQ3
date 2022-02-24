package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vanessa Rocha
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
/** Representa el objeto con los datos necesarios de un error para las excepciones*/
public class ErrorDTO {
    private String error;
    private String description;
}
