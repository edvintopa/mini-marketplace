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

    private Map<String, List<UUID>> cartMap;

    public UserCartService() {
        this.cartMap = new HashMap<>();
    }

    public void addToCart(String token, UUID productId) {
        List<UUID> cart = cartMap.getOrDefault(token, new ArrayList<>());   //if no cart, create one
        cart.add(productId);    //add product
        cartMap.put(token, cart);   //put it in the map
    }

    public List<UUID> getCart(String token) {
        return cartMap.getOrDefault(token, new ArrayList<>());  //if no cart, create one
    }

    public void clearCart(String token) {
        cartMap.remove(token);
    }

}
