package com.fieldsmanager.fields_manager_backend.service;

import com.fieldsmanager.fields_manager_backend.dto.BookingDTO;
import com.fieldsmanager.fields_manager_backend.entity.Booking;
import com.fieldsmanager.fields_manager_backend.entity.FieldSlots;
import com.fieldsmanager.fields_manager_backend.entity.Team;
import com.fieldsmanager.fields_manager_backend.entity.User;
import com.fieldsmanager.fields_manager_backend.repository.BookingRepository;
import com.fieldsmanager.fields_manager_backend.repository.FieldSlotsRepository;
import com.fieldsmanager.fields_manager_backend.repository.TeamRepository;
import com.fieldsmanager.fields_manager_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final FieldSlotsRepository fieldSlotRepository;

    public BookingService(BookingRepository bookingRepository, UserRepository userRepository,
                          TeamRepository teamRepository, FieldSlotsRepository fieldSlotRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.fieldSlotRepository = fieldSlotRepository;
    }

    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();

        // fetch relationships
        User user = userRepository.findById(bookingDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Team team = teamRepository.findById(bookingDTO.getTeamId())
                .orElseThrow(() -> new RuntimeException("Team not found"));

        FieldSlots fieldSlot = fieldSlotRepository.findById(bookingDTO.getFieldSlotId())
                .orElseThrow(() -> new RuntimeException("FieldSlot not found"));

        // set fields
        booking.setPlayer(user);
        booking.setTeam(team);
        booking.setFieldSlots(fieldSlot);
        booking.setDate(bookingDTO.getDate());
        booking.setPrice(bookingDTO.getPrice());
        booking.setStatus(bookingDTO.getStatus());

        // save booking
        Booking savedBooking = bookingRepository.save(booking);

        // convert back to DTO
        BookingDTO responseDTO = new BookingDTO();
        responseDTO.setId(savedBooking.getId());
        responseDTO.setUserId(user.getId());
        responseDTO.setTeamId(team.getId());
        responseDTO.setFieldSlotId(fieldSlot.getId());
        responseDTO.setDate(savedBooking.getDate());
        responseDTO.setPrice(savedBooking.getPrice());
        responseDTO.setStatus(savedBooking.getStatus());

        return responseDTO;
    }

    public BookingDTO getBooking(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setUserId(booking.getPlayer().getId());
        dto.setTeamId(booking.getTeam().getId());
        dto.setFieldSlotId(booking.getFieldSlots().getId());
        dto.setDate(booking.getDate());
        dto.setPrice(booking.getPrice());
        dto.setStatus(booking.getStatus());

        return dto;
    }


}
