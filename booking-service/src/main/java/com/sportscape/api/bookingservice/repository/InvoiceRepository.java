package com.sportscape.api.bookingservice.repository;

import com.sportscape.api.bookingservice.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByBookingId(Long bookingId);
}
