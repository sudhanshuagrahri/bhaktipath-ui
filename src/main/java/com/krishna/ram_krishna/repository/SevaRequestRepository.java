package com.krishna.ram_krishna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krishna.ram_krishna.model.SevaRequest;

@Repository
public interface SevaRequestRepository extends JpaRepository<SevaRequest, Long> {
    List<SevaRequest> findByUserUniqueId(String userUniqueId);
}
