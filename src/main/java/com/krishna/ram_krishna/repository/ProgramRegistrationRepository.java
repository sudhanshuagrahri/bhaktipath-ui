package com.krishna.ram_krishna.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krishna.ram_krishna.model.ProgramRegistration;

@Repository
public interface ProgramRegistrationRepository extends JpaRepository<ProgramRegistration, Long> {
    List<ProgramRegistration> findByUserUniqueId(String userUniqueId);
    boolean existsByUserUniqueIdAndProgramId(String userUniqueId, Long programId);
}
