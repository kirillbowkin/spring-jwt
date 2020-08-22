package com.example.restjwt.repository;

import com.example.restjwt.model.Role;
import com.example.restjwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Role findByName(String name);
    Optional<Role> findById(UUID id);

}
