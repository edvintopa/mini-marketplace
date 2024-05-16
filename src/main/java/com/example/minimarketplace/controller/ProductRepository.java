package com.example.minimarketplace.controller;

import com.example.minimarketplace.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Override
    List<Product> findAll();
}
