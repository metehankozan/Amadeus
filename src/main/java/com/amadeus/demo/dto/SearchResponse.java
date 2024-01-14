package com.amadeus.demo.dto;

import com.amadeus.demo.model.Flight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {
    private List<Flight> departureFlights;
    private List<Flight> returnFlights;
}
