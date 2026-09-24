package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String flightNumber;

    private Timestamp departureDate;

    private Timestamp arrivalDate;

    private int estimatedPassengers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "airplane_id", nullable = false)
    private Airplane airplane;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "origin_airport_id")
    private Airport originAirport;

    // @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    // private Airport originAirport;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "destination_airport_id")
    private Airport destinationAirport;

    // @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    // private Airport destinationAirport;
    
    @ToString.Exclude
    @JsonIgnore 
    @OneToMany (mappedBy = "flight", cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<TicketFlight> ticketFlights = new ArrayList<>();
}
