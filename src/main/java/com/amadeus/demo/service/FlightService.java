package com.amadeus.demo.service;

import com.amadeus.demo.exception.FlightNotFoundException;
import com.amadeus.demo.model.Airport;
import com.amadeus.demo.model.Flight;
import com.amadeus.demo.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final RestTemplate restTemplate;

    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight getFlightById(int id) {
        return flightRepository.findById(id).orElseThrow(() -> new FlightNotFoundException("No Flight found by id: " + id));
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public List<Flight> getFlightsForSearch(Airport from, Airport to, LocalDate date) {
        return flightRepository.getFlightsForSearch(from,
                to,
                date.atStartOfDay(),
                date.atTime(23,59,59));
    }

    public void deleteFlight(int id) {
        flightRepository.deleteById(id);
    }

    public Flight updateFlight(Flight flight) {
        Flight existingFlight = flightRepository.findById(flight.getId())
                .orElseThrow(() -> new FlightNotFoundException("No Flight found by id: " + flight.getId()));
        existingFlight.setDepartureAirport(flight.getDepartureAirport());
        existingFlight.setLandingAirport(flight.getLandingAirport());
        existingFlight.setDepartureTime(flight.getDepartureTime());
        existingFlight.setReturnTime(flight.getReturnTime());
        existingFlight.setPrice(flight.getPrice());
        return flightRepository.save(existingFlight);
    }


}
