package com.amadeus.demo.repository;

import com.amadeus.demo.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Integer> {
    Airport getAirportByCity(String city);
}
