package com.example.minimarketplace.service;

import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */
@Service
public class UserCartService {

    private Map<UUID, List<UUID>> cartMap;

    public UserCartService() {
        this.cartMap = new HashMap<>();
    }

    public void addToCart(UUID userId, UUID productId) {
        List<UUID> cart = cartMap.getOrDefault(userId, new ArrayList<>());   //if no cart, create one
        cart.add(productId);    //add product
        cartMap.put(userId, cart);   //put it in the map
    }

    public boolean removeFromCart(UUID userId, UUID productId) {
        List<UUID> cart = cartMap.getOrDefault(userId, new ArrayList<>());
        if (cart.remove(productId)) {
            cartMap.put(userId, cart);
            return true;
        } else {
            cartMap.put(userId, cart);
            return false;
        }
    }

    public List<UUID> getCart(UUID userId) {
        return cartMap.getOrDefault(userId, new ArrayList<>());  //if no cart, create one
    }

    public void clearCart(UUID userId) {
        cartMap.remove(userId);
    }

}
