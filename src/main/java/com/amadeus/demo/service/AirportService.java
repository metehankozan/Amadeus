package com.amadeus.demo.service;

import com.amadeus.demo.exception.AirportNotFoundException;
import com.amadeus.demo.model.Airport;
import com.amadeus.demo.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;

    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport getAirportById(int id) {
        return airportRepository.findById(id).orElseThrow(() -> new AirportNotFoundException("No Airport found by id: " + id));
    }

    public Airport getAirportByCity(String city) {
        return airportRepository.getAirportByCity(city);
    }

    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public void deleteAirport(int id) {
        airportRepository.deleteById(id);
    }

    public Airport updateAirport(Airport airport) {
        Airport existingAirport = airportRepository.findById(airport.getId())
                .orElseThrow(() -> new AirportNotFoundException("No Airport found by id: " + airport.getId()));
        existingAirport.setCity(airport.getCity());
        return airportRepository.save(existingAirport);
    }
}
