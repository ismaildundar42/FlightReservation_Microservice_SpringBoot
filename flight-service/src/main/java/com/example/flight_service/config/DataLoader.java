package com.example.flight_service.config;

import com.example.flight_service.entity.Flight;
import com.example.flight_service.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void run(String... args) throws Exception {
        if (flightRepository.count() == 0) {
            // Örnek uçuş verileri ekle
            Flight flight1 = new Flight();
            flight1.setFlightNumber("TK101");
            flight1.setAirline("Turkish Airlines");
            flight1.setDepartureCity("Istanbul");
            flight1.setArrivalCity("Ankara");
            flight1.setDepartureTime(LocalDateTime.now().plusHours(2));
            flight1.setArrivalTime(LocalDateTime.now().plusHours(3));
            flight1.setPrice(new BigDecimal("250.00"));
            flight1.setAvailableSeats(150);
            flight1.setTotalSeats(150);

            Flight flight2 = new Flight();
            flight2.setFlightNumber("PC202");
            flight2.setAirline("Pegasus");
            flight2.setDepartureCity("Ankara");
            flight2.setArrivalCity("Izmir");
            flight2.setDepartureTime(LocalDateTime.now().plusHours(4));
            flight2.setArrivalTime(LocalDateTime.now().plusHours(5));
            flight2.setPrice(new BigDecimal("180.00"));
            flight2.setAvailableSeats(120);
            flight2.setTotalSeats(120);

            Flight flight3 = new Flight();
            flight3.setFlightNumber("SUN303");
            flight3.setAirline("SunExpress");
            flight3.setDepartureCity("Istanbul");
            flight3.setArrivalCity("Antalya");
            flight3.setDepartureTime(LocalDateTime.now().plusHours(6));
            flight3.setArrivalTime(LocalDateTime.now().plusHours(7));
            flight3.setPrice(new BigDecimal("320.00"));
            flight3.setAvailableSeats(180);
            flight3.setTotalSeats(180);

            flightRepository.save(flight1);
            flightRepository.save(flight2);
            flightRepository.save(flight3);

            System.out.println("Sample flight data loaded!");
        }
    }
}