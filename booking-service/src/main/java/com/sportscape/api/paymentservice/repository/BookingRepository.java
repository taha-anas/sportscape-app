package com.sportscape.api.paymentservice.repository;

import com.sportscape.api.paymentservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByUserId(Long userId);
}
