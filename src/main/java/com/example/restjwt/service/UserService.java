package com.example.restjwt.service;

import com.example.restjwt.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(UUID id);

    void deleteById(UUID id);

}
