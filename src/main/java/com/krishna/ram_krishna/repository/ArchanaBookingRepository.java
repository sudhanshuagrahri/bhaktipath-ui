package com.krishna.ram_krishna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krishna.ram_krishna.model.ArchanaBooking;

@Repository
public interface ArchanaBookingRepository extends JpaRepository<ArchanaBooking, Long> {
    List<ArchanaBooking> findByUserUniqueId(String userUniqueId);
}
