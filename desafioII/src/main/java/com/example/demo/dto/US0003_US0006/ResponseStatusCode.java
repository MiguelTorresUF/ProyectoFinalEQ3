package com.example.demo.dto.US0003_US0006;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Vanessa Rocha
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
/** Representa el objeto con los datos necesarios para retornar un statusCode*/
public class ResponseStatusCode {
    private int code;
    private String message;
}
