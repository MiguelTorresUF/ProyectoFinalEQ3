package com.example.demo.service.touristicPackage;

import com.example.demo.dto.ResponseFlightReservationDTO;
import com.example.demo.dto.US0003_US0006.PayloadFlightsDTO;
import com.example.demo.dto.packagesDTOS.TouristicPackageDTO;
import com.example.demo.model.TourBOR;
import com.example.demo.model.TouristicPackage;

public interface TouristicPackageService {
    public TouristicPackage save(TouristicPackageDTO touristicPackageDTO);
}
