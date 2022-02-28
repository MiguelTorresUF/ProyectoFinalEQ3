package com.example.demo.service.hotel;

import com.example.demo.dto.hotelsDTOS.HotelsDTO;
import com.example.demo.dto.hotelsDTOS.ResponseDataHotelsDTO;
import com.example.demo.dto.hotelsDTOS.ResponseHotelsDTO;
import com.example.demo.exception.BadDateException;
import com.example.demo.exception.ListEmptyException;
import com.example.demo.exception.NotDestinationException;

import com.example.demo.model.Hotels;
import com.example.demo.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Vanessa Rocha
 */
/** Servicio de Hotel implementa de HotelService*/
@Service
public class HotelServiceImpl implements HotelService{
    @Autowired
    HotelRepository hotelRepository;

    @Override
    @Transactional()
    public Hotels save(Hotels hotels) {
        return hotelRepository.save(hotels);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Hotels> findAll() {
        return hotelRepository.findAll();
    }

    @Override
    public ResponseHotelsDTO getHotelsAvailable(Date dateFrom, Date dateTo, String destination) {
        List<Hotels> hotels = hotelRepository.findAll();

        if (hotels.size() == 0){
            throw new ListEmptyException("La lista se encuentra vacia");
        }

        validHotelsParams(dateFrom, dateTo, destination);
        List<Hotels> hotels1 = hotels.stream().filter(hotels2 ->
                (hotels2.getDisponibilityDateFrom().equals(dateFrom) || hotels2.getDisponibilityDateFrom().before(dateFrom)) &&
                        (hotels2.getDisponibilityDateTo().equals(dateTo) || hotels2.getDisponibilityDateTo().after(dateTo)) &&
                        hotels2.getPlace().equals(destination)).collect(Collectors.toList());

        ResponseHotelsDTO response = new ResponseHotelsDTO(hotels1);
        return response;
    }

    private void validHotelsParams(Date dateFrom, Date dateTo, String destination){
        if(destination == null || destination == ""){
            throw new NotDestinationException("El destino ingresado no existe.");
        }

        //compara fechas
        if(dateFrom.compareTo(dateTo) > 0)
        {
            throw new BadDateException("Alguna fecha es incorrecta. La fecha de entrada tiene que ser menor que la de salida.");
        }
    }

    @Override
    public ResponseDataHotelsDTO delete(String hotelCode){
        List<Hotels> list = hotelRepository.findAll();
        Optional<Hotels> listFilter= list.stream()
                .filter(
                        l -> l.getHotelCode().equals(hotelCode)).findFirst();

        if(listFilter.isEmpty()){
            ResponseDataHotelsDTO response = new ResponseDataHotelsDTO("la habitacion no se encuentra registrada");
            return response;
        }

        int id = listFilter.get().getId_hotel();
        hotelRepository.deleteById(id);

        ResponseDataHotelsDTO response = new ResponseDataHotelsDTO("Habitacion eliminada correctamente");
        return response;
    }



    //ACTUALIZAR
    @Override
    public ResponseDataHotelsDTO update(HotelsDTO hotelsDTO, String hotelCode) {
        List<Hotels> list = hotelRepository.findAll();
        Optional<Hotels> listFilter = list.stream()
                .filter(
                        l -> l.getHotelCode().equals(hotelCode)).findFirst();


        listFilter.get().setName(hotelsDTO.getName());;
        listFilter.get().setPlace(hotelsDTO.getPlace());
        listFilter.get().setRoomType(hotelsDTO.getRoomType());
        listFilter.get().setRoomPrice(hotelsDTO.getRoomPrice());
        listFilter.get().setDisponibilityDateFrom(hotelsDTO.getDisponibilityDateFrom());
        listFilter.get().setDisponibilityDateFrom(hotelsDTO.getDisponibilityDateFrom());

        hotelRepository.save(listFilter.get());

        ResponseDataHotelsDTO response = new ResponseDataHotelsDTO("Hotel modificado correctamente");
        return response;
    }



}
