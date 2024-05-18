package com.example.minimarketplace.service;

import com.example.minimarketplace.auth.JwtUtil;
import com.example.minimarketplace.model.user.User;
import com.example.minimarketplace.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */

@Service
public class TokenResolverService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Autowired
    public TokenResolverService(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    public UUID resolveTokenToUserId(String token) {
        String username = jwtUtil.getBearer(token.replace("Bearer ", ""));
        User user = userRepository.findByUsername(username);
        return user.getUserId();
    }
}