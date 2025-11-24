package com.example.flight_service.service;

import com.example.flight_service.entity.Flight;
import com.example.flight_service.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    
    @Autowired
    private FlightRepository flightRepository;
    
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }
    
    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }
    
    public Optional<Flight> getFlightByFlightNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber);
    }
    
    public List<Flight> searchFlights(String departureCity, String arrivalCity) {
        return flightRepository.findByDepartureCityAndArrivalCity(departureCity, arrivalCity);
    }
    
    public List<Flight> getAvailableFlights() {
        return flightRepository.findByAvailableSeatsGreaterThan(0);
    }
    
    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }
    
    public boolean updateAvailableSeats(Long flightId, Integer seatsToReserve) {
        Optional<Flight> flightOpt = flightRepository.findById(flightId);
        if (flightOpt.isPresent()) {
            Flight flight = flightOpt.get();
            if (flight.getAvailableSeats() >= seatsToReserve) {
                flight.setAvailableSeats(flight.getAvailableSeats() - seatsToReserve);
                flightRepository.save(flight);
                return true;
            }
        }
        return false;
    }
    
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}