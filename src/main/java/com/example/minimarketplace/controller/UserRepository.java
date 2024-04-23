package com.example.minimarketplace.controller;

import com.example.minimarketplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-04-01
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByUsername(String username);
    List<User> findByEmail(String email);
}
