package com.amadeus.demo;

import com.amadeus.demo.model.Airport;
import com.amadeus.demo.model.Flight;
import com.amadeus.demo.repository.AirportRepository;
import com.amadeus.demo.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {

    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        new Airport();
        Airport airport = airportRepository.save(Airport.builder().city("KCM").build());
        Airport airport1 = airportRepository.save(Airport.builder().city("IST").build());

        Flight flight = flightRepository.save(Flight.builder()
                .departureAirport(airport)
                .landingAirport(airport1)
                .departureTime(LocalDateTime.now())
                .price(20000)
                .build());

        Flight flight1 = flightRepository.save(Flight.builder()
                .departureAirport(airport1)
                .landingAirport(airport)
                .departureTime(LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.now()))
                .returnTime(LocalDateTime.now().plusHours(2))
                .price(10000)
                .build());

        System.out.println(airport);
        System.out.println(airport1);
        System.out.println(flight);
        System.out.println(flight1);
    }

    @Bean
    public RestTemplate createRestTemplate() {
        return new RestTemplate();
    }

}
