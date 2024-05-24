package com.sportscape.api.bookingservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice {
    @Id
    @GeneratedValue
    private long id;
    private String invoiceNumber;
    @Column(name = "total_amount")
    private double totalAmount;
    @Column(name = "issue_date")
    private LocalDateTime issueDate;

    @OneToOne
    private Booking booking;
}
