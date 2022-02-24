package com.example.demo.repository.hotel;

import com.example.demo.entity.Hotel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vanessa Rocha
 */
@Data
@Repository
public class HotelRepositoryImpl implements HotelRepository {
    private List<Hotel> databaseHotel;

    /** Metodo que asigna el retorno del metodo loadDataBase() a la variable databaseHotel */
    public HotelRepositoryImpl(){
        this.databaseHotel = loadDataBase();
    }

    /** Metodo que retorna una lista de hoteles proveniente del JSON
     * @return lista de hoteles buscada del json*/
    private List<Hotel> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:hotels.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Hotel>> typeRef = new TypeReference<>() {};
        List<Hotel> hotelsList = null;
        try {
            hotelsList = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hotelsList;
    }

    /** Método get
     * @return la lista de hoteles en una variable para manipularla en el servicio */
    @Override
    public List<Hotel> getDatabaseHotel() {
        return databaseHotel;
    }
}
