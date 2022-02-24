package com.example.demo.repository.vuelos;

import com.example.demo.entity.Flights;
import com.example.demo.entity.Hotel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * @author Vanessa Rocha
 */
@Data
@Repository
public class VuelosRepositoryImpl implements VuelosRepository{
    private List<Flights> databaseFlights;

    /** Metodo que asigna el retorno del metodo loadDataBase() a la variable databaseFlights */
    public VuelosRepositoryImpl(){
        this.databaseFlights = loadDataBase();
    }

    /** Metodo que retorna una lista de vuelos proveniente del JSON
     * @return la lista de hoteles del JSON*/
    private List<Flights> loadDataBase() {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:flights.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Flights>> typeRef = new TypeReference<>() {};
        List<Flights> hotelsList = null;
        try {
            hotelsList = objectMapper.readValue(file, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hotelsList;
    }

    /** Método get
     * @return la lista de vuelos en una variable para manipularla en el servicio */
    @Override
    public List<Flights> getDatabaseFlights() {
        return databaseFlights;
    }
}
