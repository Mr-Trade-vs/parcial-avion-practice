package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.ToString;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 
@Table (name = "airports")
public class Airport {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    private String country;

    private String iataCode;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "originAirport", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Flight> departingFlights = new ArrayList<>();

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "destinationAirport", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Ticket> destinationTickets = new ArrayList<>();
}
