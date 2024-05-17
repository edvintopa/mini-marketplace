package com.example.minimarketplace.repository.user;

import com.example.minimarketplace.model.user.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */
public interface UserInterestRepository extends JpaRepository<UserInterest, UUID> {
    List<UUID> findUsersInterestedIn(String type);
}
