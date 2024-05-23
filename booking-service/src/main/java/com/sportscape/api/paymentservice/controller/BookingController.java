package com.sportscape.api.paymentservice.controller;

import com.sportscape.api.clients.user.UserClient;
import com.sportscape.api.clients.user.UserResponse;
import com.sportscape.api.paymentservice.dto.BookingRequestDto;
import com.sportscape.api.paymentservice.dto.BookingResponseDto;
import com.sportscape.api.paymentservice.model.Booking;
import com.sportscape.api.paymentservice.model.Invoice;
import com.sportscape.api.paymentservice.model.Status;
import com.sportscape.api.paymentservice.service.BookingService;
import com.sportscape.api.paymentservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/booking")
@Slf4j
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private UserClient userClient;

    @GetMapping
    public ResponseEntity<?> getAllUserBooking(@RequestBody BookingRequestDto bookingDto) {
        log.info("Get all user booking request body {}", bookingDto);
        if (bookingDto.getUserId() == null ) {
            return new ResponseEntity<>("User id must present in the request body", HttpStatus.BAD_REQUEST);
        }
        // to-do check if user id refer to user
        // send request to user service
        UserResponse user = userClient.getUser(bookingDto.getUserId());
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        // if okay return all booking
        List<Booking> bookings = bookingService.getBookingsByUserId(bookingDto.getUserId());
        return new ResponseEntity<>(
                bookings.stream().map(this::mapBookingToBookingDto),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<BookingResponseDto> getBookingById(@PathVariable Long id) {
        Optional<Booking> booking = bookingService.getBookingById(id);
        return booking
                .map(value -> new ResponseEntity<>(mapBookingToBookingDto(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto bookingDto) {
        if (bookingDto.getUserId() == null ||
                bookingDto.getFacilityId() ==null ||
                bookingDto.getStartTime() ==null ||
                bookingDto.getEndTime() ==null ||
                bookingDto.getPaymentMethod() == null ||
                bookingDto.getTotalAmount() == null
        ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // check if the userId exist

        // check if facilityId exist

        // create Booking object
        Booking bookingData = mapBookingRequestToBooking(bookingDto);
        bookingData.setStatus(Status.PENDING);
        Booking savedBooking = bookingService.saveBooking(bookingData);

        // pass Payment

        // create Invoice
        Invoice invoice = Invoice.builder()
                .invoiceNumber(UUID.randomUUID().toString())
                .issueDate(LocalDateTime.now())
                .totalAmount(bookingData.getTotalAmount())
                .booking(savedBooking)
                .build();
        Invoice savedInvoice = invoiceService.saveInvoice(invoice);

        return new ResponseEntity<>(mapBookingToBookingDto(savedBooking), HttpStatus.CREATED);
    }

    private Booking mapBookingRequestToBooking(BookingRequestDto bookingDto) {
        return Booking.builder()
                .userId(bookingDto.getUserId())
                .facilityId(bookingDto.getFacilityId())
                .paymentMethod(bookingDto.getPaymentMethod())
                .startTime(bookingDto.getStartTime())
                .endTime(bookingDto.getEndTime())
                .totalAmount(bookingDto.getTotalAmount())
                .build();
    }

    private BookingResponseDto mapBookingToBookingDto(Booking booking) {
        return BookingResponseDto.builder()
                .id(booking.getId())
                .userId(booking.getUserId())
                .facilityId(booking.getFacilityId())
                .paymentMethod(booking.getPaymentMethod())
                .startTime(booking.getStartTime())
                .endTime(booking.getEndTime())
                .status(booking.getStatus())
                .totalAmount(booking.getTotalAmount())
                .build();
    }
}
