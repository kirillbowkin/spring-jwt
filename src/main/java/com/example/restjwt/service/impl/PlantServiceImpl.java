package com.example.restjwt.service.impl;

import com.example.restjwt.model.Plant;
import com.example.restjwt.model.User;
import com.example.restjwt.repository.PlantsRepository;
import com.example.restjwt.service.PlantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class PlantServiceImpl implements PlantService {

    @Autowired
    PlantsRepository plantsRepository;

    @Override
    public void save(Plant plant) {
        plantsRepository.save(plant);
        log.info("IN save - plant: {} saved", plant);
    }

    @Override
    public List<Plant> getAll() {
        List<Plant> result = plantsRepository.findAll();
        log.info("IN getAll - {} plants found", result.size());
        return result;
    }

    @Override
    public Plant findByCommonName(String commonName) {
        Plant result = plantsRepository.findByCommonName(commonName).orElse(null);

        if(result == null){
            log.warn("IN findByCommonName - no plant found by common name: {}", commonName);
            return null;
        }

        log.info("IN findByCommonName - plant: {} found by common name: {}", result.toString(), commonName);
        return result;
    }

    @Override
    public Plant findById(UUID id) {
        Plant result = plantsRepository.findById(id).orElse(null);

        if(result == null){
            log.warn("IN findById - no plant found by id: {}", id);
            return null;
        }

        log.info("IN findById - plant: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void deleteById(UUID id) {
        Plant result = plantsRepository.findById(id).orElse(null);

        if(result == null){
            log.warn("IN deleteById - no plant found by id: {}", id);
        }

        plantsRepository.delete(result);
        log.info("IN deleteById - plant with id: {} successfully deleted", id);
    }
}
