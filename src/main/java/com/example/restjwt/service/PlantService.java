package com.example.restjwt.service;

import com.example.restjwt.model.Plant;
import com.example.restjwt.model.User;

import java.util.List;
import java.util.UUID;

public interface PlantService {

    List<Plant> getAll();

    Plant findByCommonName(String username);

    Plant findById(UUID id);

    void deleteById(UUID id);

    void save(Plant plant);
}
