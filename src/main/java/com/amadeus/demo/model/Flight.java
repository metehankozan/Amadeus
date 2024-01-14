package com.amadeus.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Airport departureAirport;
    @ManyToOne
    private Airport landingAirport;
    private LocalDateTime departureTime;
    private LocalDateTime returnTime;
    private double price;

}
