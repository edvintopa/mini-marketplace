package com.example.minimarketplace.controller;

import com.example.minimarketplace.model.dto.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}
