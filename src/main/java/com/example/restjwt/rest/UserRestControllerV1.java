package com.example.restjwt.rest;

import com.example.restjwt.dto.UserDto;
import com.example.restjwt.model.Plant;
import com.example.restjwt.model.User;
import com.example.restjwt.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/plants")
@CrossOrigin
public class UserRestControllerV1 {

    @Autowired
    @Lazy
    PlantService plantService;

    @GetMapping()
    public ResponseEntity<List<Plant>> getPlants(){
        List<Plant> plants = plantService.getAll();

        if(plants == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<List<Plant>>(plants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getById(@PathVariable("id") UUID id){
        Plant plant = plantService.findById(id);

        if(plant == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Plant>(plant, HttpStatus.OK);
    }
}
