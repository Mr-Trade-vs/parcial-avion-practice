package com.example.demo.repository;

import com.example.demo.model.Airport;

import java.security.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAirportRepository extends JpaRepository<Airport, Long> {

    //Second Query
    @Query ("""
        SELECT DISTINCT f.destinationAirport
        FROM Flight f
        WHERE f.originAirport.name = :originName
            """)
    List<Airport> findAiportTrackAgreetments(@Param("originName") String originAirportName);

    //Fifth query
    @Query("""
            
        SELECT f.destinationAirport
        FROM Flight f
        WHERE f.arrivalDate BETWEEN :startDate AND :endDate
        GROUP BY f.destinationAirport
        ORDER BY SUM(f.estimatedPassengers) DESC
        LIMIT 5
        
            """)
    List<Airport> findTopFiveTrafficPassengersAirport(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

}
