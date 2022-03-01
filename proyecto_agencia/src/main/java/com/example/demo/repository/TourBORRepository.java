package com.example.demo.repository;

import com.example.demo.model.TourBOR;
import com.example.demo.model.TouristicPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourBORRepository extends JpaRepository<TourBOR,Integer> {
}
