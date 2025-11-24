package com.example.reservation_service.client;

import com.example.reservation_service.dto.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "flight-service")
public interface FlightServiceClient {
    
    @GetMapping("/api/flights/{id}")
    ResponseEntity<FlightDto> getFlightById(@PathVariable Long id);
    
    @PutMapping("/api/flights/{id}/reserve-seats")
    ResponseEntity<String> reserveSeats(@PathVariable Long id, @RequestParam Integer seats);
}