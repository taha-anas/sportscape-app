package com.sportscape.api.paymentservice.service;

import com.sportscape.api.paymentservice.model.Booking;
import com.sportscape.api.paymentservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findAllByUserId(userId);
    }

    public boolean bookingExistsById(Long id){
        return bookingRepository.existsById(id);
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking saveBooking(Booking bookingData) {
        return bookingRepository.save(bookingData);
    }
}
