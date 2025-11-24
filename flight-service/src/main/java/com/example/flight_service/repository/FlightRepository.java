package com.example.flight_service.repository;

import com.example.flight_service.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findByFlightNumber(String flightNumber);
    List<Flight> findByDepartureCityAndArrivalCity(String departureCity, String arrivalCity);
    List<Flight> findByAvailableSeatsGreaterThan(Integer availableSeats);
}