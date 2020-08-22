package com.example.restjwt.repository;

import com.example.restjwt.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlantsRepository extends JpaRepository<Plant, UUID> {
    Optional<Plant> findById(UUID id);
    Optional<Plant> findByCommonName(String commonName);
}
