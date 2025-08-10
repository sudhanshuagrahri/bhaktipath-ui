package com.krishna.ram_krishna.repository;

import com.krishna.ram_krishna.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByMobileNumber(String mobileNumber);
    Optional<User> findByUniqueId(String uniqueId);
}

