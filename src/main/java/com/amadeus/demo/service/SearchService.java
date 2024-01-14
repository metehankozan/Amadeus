package com.amadeus.demo.service;

import com.amadeus.demo.dto.SearchResponse;
import com.amadeus.demo.model.Airport;
import com.amadeus.demo.model.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {
    private final AirportService airportService;
    private final FlightService flightService;

    public SearchResponse getFlights(String from, String to, LocalDate departureDate, LocalDate returnDate) {
        SearchResponse searchResponse = new SearchResponse();

        Airport fromAirport = airportService.getAirportByCity(from);
        Airport toAirport = airportService.getAirportByCity(to);

        searchResponse.setDepartureFlights(flightService.getFlightsForSearch(fromAirport, toAirport, departureDate));
        if (returnDate != null) {
            searchResponse.setReturnFlights(flightService.getFlightsForSearch(toAirport, fromAirport, returnDate));
        }
        return searchResponse;
    }
}
