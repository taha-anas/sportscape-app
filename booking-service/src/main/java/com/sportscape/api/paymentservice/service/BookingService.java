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

    public Booking updateBooking(Long id,Booking savedBooking) {
        Optional<Booking> oldBooking = bookingRepository.findById(id);
        if(oldBooking.isEmpty()) {
            throw new RuntimeException("Booking not found");
        }
        return bookingRepository.save(savedBooking);
    }
}
