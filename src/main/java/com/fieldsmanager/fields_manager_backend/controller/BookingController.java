package com.fieldsmanager.fields_manager_backend.controller;

import com.fieldsmanager.fields_manager_backend.dto.BookingDTO;
import com.fieldsmanager.fields_manager_backend.entity.Booking;
import com.fieldsmanager.fields_manager_backend.security.JwtUtil;
import com.fieldsmanager.fields_manager_backend.service.BookingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final JwtUtil jwtUtil;

    public BookingController(BookingService bookingService, com.fieldsmanager.fields_manager_backend.security.JwtUtil jwtUtil) {
        this.bookingService = bookingService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/create")
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingDTO bookingDTO) {
        BookingDTO savedBooking = bookingService.createBooking(bookingDTO);
        return ResponseEntity.ok(savedBooking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBooking(@PathVariable Integer id) {
        BookingDTO booking = bookingService.getBooking(id);
        return ResponseEntity.ok(booking);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/decision")
    public ResponseEntity<BookingDTO> approveOrRejectBooking(
            @PathVariable Integer id,
            @RequestParam String decision) {

        BookingDTO booking = bookingService.getBooking(id);
        if (decision.equalsIgnoreCase("approve")) {
            booking.setStatus("APPROVED");
        } else if (decision.equalsIgnoreCase("reject")) {
            booking.setStatus("REJECTED");
        } else {
            throw new RuntimeException("Invalid decision");
        }


        BookingDTO updated = bookingService.updateBookingStatus(id, booking.getStatus());
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<String> trackBooking(@PathVariable Integer id) {
        BookingDTO booking = bookingService.getBooking(id);
        return ResponseEntity.ok(booking.getStatus());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/status")
    public ResponseEntity<BookingDTO> updateStatus(
            @PathVariable Integer id,
            @RequestParam String status) {

        BookingDTO updated = bookingService.updateBookingStatus(id, status);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<BookingDTO>> getBookingHistory(@PathVariable Integer userId) {
        List<BookingDTO> bookings = bookingService.getBookingHistory(userId);
        return ResponseEntity.ok(bookings);
    }



}

