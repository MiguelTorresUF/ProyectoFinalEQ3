package com.example.demo.repository.hotel;

import com.example.demo.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Vanessa Rocha
 */
/** Representa la interface de HotelRepository*/
public interface HotelRepository {
/** Representa el método del HotelRepository
 * @return la lista de hoteles de la base de datos*/
    public List<Hotel> getDatabaseHotel();
}
