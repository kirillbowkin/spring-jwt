package com.example.restjwt.rest;

import com.example.restjwt.dto.UserDto;
import com.example.restjwt.model.Plant;
import com.example.restjwt.model.User;
import com.example.restjwt.service.PlantService;
import com.example.restjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/admin/")
@CrossOrigin
public class AdminRestControllerV1 {

    private final UserService userService;
    private final PlantService plantService;

    @Autowired
    public AdminRestControllerV1(UserService userService, PlantService plantService) {
        this.userService = userService;
        this.plantService = plantService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") UUID id){
        User user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<UserDto>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        List<User> users = userService.getAll();

        if(users == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserDto> result = users.stream().map(user -> UserDto.fromUser(user)).collect(Collectors.toList());

        return new ResponseEntity<List<UserDto>>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<UserDto> postUser(@RequestBody UserDto user){

        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        userService.register(user.toUser());

        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @PutMapping(value = "/users")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user){

        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        userService.register(user.toUser());

        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") UUID id){

        User user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        userService.deleteById(id);
        return new ResponseEntity<UserDto>(UserDto.fromUser(user), HttpStatus.OK);
    }


    @PostMapping(value = "/plants")
    public ResponseEntity<Plant> postPlant(@RequestBody Plant plant){

        if(plant == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        plantService.save(plant);

        return new ResponseEntity<Plant>(plant, HttpStatus.OK);
    }

    @PutMapping(value = "/plants")
    public ResponseEntity<Plant> updatePlant(@RequestBody Plant plant){

        if(plant == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        plantService.save(plant);

        return new ResponseEntity<Plant>(plant, HttpStatus.OK);
    }

    @DeleteMapping("/plants/{id}")
    public ResponseEntity<Plant> deletePlant(@PathVariable("id") UUID id){

        Plant plant = plantService.findById(id);

        if(plant == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        plantService.deleteById(id);
        return new ResponseEntity<Plant>(plant, HttpStatus.OK);
    }



}
