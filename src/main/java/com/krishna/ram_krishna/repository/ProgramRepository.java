package com.krishna.ram_krishna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krishna.ram_krishna.model.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
}
