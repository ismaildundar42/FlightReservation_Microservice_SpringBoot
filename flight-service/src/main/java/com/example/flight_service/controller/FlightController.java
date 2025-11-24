package com.example.flight_service.controller;

import com.example.flight_service.entity.Flight;
import com.example.flight_service.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    
    @Autowired
    private FlightService flightService;
    
    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable Long id) {
        Optional<Flight> flight = flightService.getFlightById(id);
        return flight.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/number/{flightNumber}")
    public ResponseEntity<Flight> getFlightByFlightNumber(@PathVariable String flightNumber) {
        Optional<Flight> flight = flightService.getFlightByFlightNumber(flightNumber);
        return flight.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity) {
        List<Flight> flights = flightService.searchFlights(departureCity, arrivalCity);
        return ResponseEntity.ok(flights);
    }
    
    @GetMapping("/available")
    public ResponseEntity<List<Flight>> getAvailableFlights() {
        List<Flight> flights = flightService.getAvailableFlights();
        return ResponseEntity.ok(flights);
    }
    
    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight) {
        Flight savedFlight = flightService.saveFlight(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable Long id, @RequestBody Flight flight) {
        Optional<Flight> existingFlight = flightService.getFlightById(id);
        if (existingFlight.isPresent()) {
            flight.setId(id);
            Flight updatedFlight = flightService.saveFlight(flight);
            return ResponseEntity.ok(updatedFlight);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}/reserve-seats")
    public ResponseEntity<String> reserveSeats(@PathVariable Long id, @RequestParam Integer seats) {
        boolean success = flightService.updateAvailableSeats(id, seats);
        if (success) {
            return ResponseEntity.ok("Seats reserved successfully");
        }
        return ResponseEntity.badRequest().body("Unable to reserve seats");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}