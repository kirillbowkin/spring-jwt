package com.example.restjwt.service.impl;

import com.example.restjwt.model.Role;
import com.example.restjwt.model.Status;
import com.example.restjwt.model.User;
import com.example.restjwt.repository.RoleRepository;
import com.example.restjwt.repository.UserRepository;
import com.example.restjwt.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username).orElse(null);

        if(result == null){
            log.warn("IN findByUsername - no user found by username: {}", username);
            return null;
        }

        log.info("IN findByUsername - user: {} found by username: {}", result.toString(), username);
        return result;
    }

    @Override
    public User findById(UUID id) {
        User result = userRepository.findById(id).orElse(null);

        if(result == null){
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void deleteById(UUID id) {
        User result = userRepository.findById(id).orElse(null);

        if(result == null){
            log.warn("IN deleteById - no user found by id: {}", id);
        }

        userRepository.delete(result);
        log.info("IN deleteById - user with id: {} successfully deleted", id);
    }
}
