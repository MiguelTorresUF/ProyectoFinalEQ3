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
/** Representa el objeto con los datos necesarios para el metodo de pago*/
public class PaymentMethodDTO {
    private String type;
    private String number;
    private int dues;
}
