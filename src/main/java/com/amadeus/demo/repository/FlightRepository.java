package com.amadeus.demo.repository;

import com.amadeus.demo.model.Airport;
import com.amadeus.demo.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> getFlightsByDepartureAirportAndLandingAirport(Airport departureAirport, Airport landingAirport);

    @Query("select f from Flight f where f.departureAirport = :from and f.landingAirport = :to " +
            "and f.departureTime between :startDate and :endDate")
    List<Flight> getFlightsForSearch(@Param("from") Airport from,
                                     @Param("to") Airport to,
                                     @Param("startDate") LocalDateTime startDate,
                                     @Param("endDate") LocalDateTime endDate);
}
