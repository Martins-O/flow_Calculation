package com.example.flowcalculator.data.repository;

import com.example.flowcalculator.data.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findUserByUsername(String username);
    boolean existsByUsername(String username);

    Optional<Object> findAppUserByUsername(String username);
}
