package com.example.demo.repository;

import com.example.demo.model.Airline;

import java.security.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAirlineRepository extends JpaRepository<Airline, Long> {

    // First Query
    @Query(
    """ 
        SELECT DISTINCT ap.airline
        FROM Flight f
        INNER JOIN f.airplane ap
        INNER JOIN f.originAirport origin
        INNER JOIN f.destinationAirport destination
        WHERE origin.name = :originName AND destination.name = :destinationName
    """
    )
    List<Airline> findByTrackAirplane(@Param("originName") String originName, @Param("destinationName") String destinationName);

    //Third Query
    @Query ("""
            
        SELECT DISTINCT ap.airline
        FROM Flight f
        INNER JOIN f.airplane ap
        WHERE f.originAirport.city = :cityName OR f.destinationAirport.city = :cityName

            """)
    List<Airline> findAirlineByCityAirport(@Param("cityName") String city);

    //Fouth Query
    @Query ("""
            
        SELECT DISTINCT f.airplane.airline
        FROM Flight f
        WHERE f.destinationAirport.name = : airportName 
            AND f.arrivalDate BETWEEN :startDate AND :endDate

            """)
    List<Airline> trackArrivalDate(@Param ("startDate") Timestamp startDate, @Param ("endDate") Timestamp endDate, @Param ("airportName") String airportName);
}
