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
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerFullName;

    private String bookingCode;

    private Timestamp purchaseDate;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "origin_airport_id")
    private Airport originAiport;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "destination_airport_id")
    private Airport destinationAirport;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany (mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketFlight> ticketFlights;

    // @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JoinColumn(name = "destination_airport_id", nullable = false)
    // private Airport originAirport;
    
    // @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JoinColumn(name = "origin_airport_id", nullable = false)
    // private Airport destinationAirport;

    // @ManyToOne(fetch = FetchType.LAZY)
    // private Ticket ticketFlights;
}
