package com.example.reservation_service.service;

import com.example.reservation_service.client.FlightServiceClient;
import com.example.reservation_service.dto.FlightDto;
import com.example.reservation_service.entity.Reservation;
import com.example.reservation_service.entity.ReservationStatus;
import com.example.reservation_service.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Autowired
    private FlightServiceClient flightServiceClient;
    
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }
    
    public List<Reservation> getReservationsByEmail(String email) {
        return reservationRepository.findByPassengerEmail(email);
    }
    
    public List<Reservation> getReservationsByFlightId(Long flightId) {
        return reservationRepository.findByFlightId(flightId);
    }
    
    public Reservation createReservation(Reservation reservation) {
        try {
            // Flight bilgilerini al
            ResponseEntity<FlightDto> flightResponse = flightServiceClient.getFlightById(reservation.getFlightId());
            
            if (flightResponse.getBody() == null) {
                throw new RuntimeException("Flight not found");
            }
            
            FlightDto flight = flightResponse.getBody();
            
            // Koltuk rezervasyonu yap
            ResponseEntity<String> reserveResponse = flightServiceClient.reserveSeats(
                reservation.getFlightId(), 
                reservation.getNumberOfSeats()
            );
            
            if (!reserveResponse.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Unable to reserve seats");
            }
            
            // Flight bilgilerini reservation'a kopyala
            reservation.setFlightNumber(flight.getFlightNumber());
            reservation.setAirline(flight.getAirline());
            reservation.setDepartureCity(flight.getDepartureCity());
            reservation.setArrivalCity(flight.getArrivalCity());
            reservation.setDepartureTime(flight.getDepartureTime());
            
            // Toplam fiyatı hesapla
            BigDecimal totalPrice = flight.getPrice().multiply(new BigDecimal(reservation.getNumberOfSeats()));
            reservation.setTotalPrice(totalPrice);
            reservation.setStatus(ReservationStatus.CONFIRMED);
            
            // Tarih ve durum kontrolü
            if (reservation.getReservationDate() == null) {
                reservation.setReservationDate(LocalDateTime.now());
            }
            
            return reservationRepository.save(reservation);
            
        } catch (Exception e) {
            reservation.setStatus(ReservationStatus.CANCELLED);
            throw new RuntimeException("Reservation failed: " + e.getMessage());
        }
    }
    
    public Reservation updateReservation(Long id, Reservation reservation) {
        Optional<Reservation> existingReservation = reservationRepository.findById(id);
        if (existingReservation.isPresent()) {
            reservation.setId(id);
            return reservationRepository.save(reservation);
        }
        throw new RuntimeException("Reservation not found");
    }
    
    public void cancelReservation(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            Reservation res = reservation.get();
            res.setStatus(ReservationStatus.CANCELLED);
            reservationRepository.save(res);
        }
    }
    
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}