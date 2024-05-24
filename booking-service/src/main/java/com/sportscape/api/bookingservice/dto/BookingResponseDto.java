package com.sportscape.api.bookingservice.dto;

import com.sportscape.api.bookingservice.model.PaymentMethod;
import com.sportscape.api.bookingservice.model.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponseDto {
    private Long id;
    private Long userId;
    private Long facilityId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Double totalAmount;
    private PaymentMethod paymentMethod;
    private Status status;
}
