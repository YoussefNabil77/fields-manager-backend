package com.fieldsmanager.fields_manager_backend.repository;

import com.fieldsmanager.fields_manager_backend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findByTeamId(Integer teamId);
    List<Booking> findByPlayerId(Integer playerId);
}
