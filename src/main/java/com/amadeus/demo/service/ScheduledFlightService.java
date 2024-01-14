package com.amadeus.demo.service;

import com.amadeus.demo.exception.FlightNotFoundException;
import com.amadeus.demo.model.Airport;
import com.amadeus.demo.model.Flight;
import com.amadeus.demo.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledFlightService {

    private final FlightService flightService;
    private final AirportRepository airportRepository;

    @Scheduled(cron = "0 0 0 * * *", zone="Europe/Istanbul")
    public void updateFlights(){
        Airport airport = airportRepository.getAirportByCity("KCM");
        Airport airport1 = airportRepository.getAirportByCity("IST");
        List<Flight> updatedFlights = Arrays.asList(Flight.builder().departureAirport(airport).landingAirport(airport1).departureTime(LocalDateTime.now()).price(20000).build(),
                Flight.builder().departureAirport(airport).landingAirport(airport1).departureTime(LocalDateTime.now().plusHours(2)).price(2000).build(),
                Flight.builder().departureAirport(airport1).landingAirport(airport).departureTime(LocalDateTime.now().plusDays(3)).price(20000).build(),
                Flight.builder().id(5).departureAirport(airport1).landingAirport(airport).departureTime(LocalDateTime.now().plusDays(3)).price(20000).build());

        for (Flight flight : updatedFlights) {
            try {
                flightService.updateFlight(flight);
                System.out.println("Updating: " + flight);
            } catch (FlightNotFoundException e) {
                flightService.save(flight);
                System.out.println("Inserting: " + flight);
            }
        }
    }
}