package com.example.demo.dto.US0003_US0006;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.util.Date;

/**
 * @author Vanessa Rocha
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
/** Representa el objeto con los datos necesarios de una persona*/
public class PeopleDTO {
    private String dni;
    private String name;
    private String lastname;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDate;
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",message = "Email no valido.")
    private String mail;
}
