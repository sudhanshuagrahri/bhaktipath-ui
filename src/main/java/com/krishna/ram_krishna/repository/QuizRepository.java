package com.krishna.ram_krishna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krishna.ram_krishna.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
