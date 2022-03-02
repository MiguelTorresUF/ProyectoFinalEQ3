package com.example.demo.repository;

import com.example.demo.model.Flight_people;
import com.example.demo.model.TouristicPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristicPackageRepository extends JpaRepository<TouristicPackage,Integer> {
}
