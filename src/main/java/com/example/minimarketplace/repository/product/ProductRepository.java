package com.example.minimarketplace.repository.product;

import com.example.minimarketplace.model.product.Product;
import com.example.minimarketplace.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Override
    List<Product> findAll();

    Product findByProductId(UUID productId);
    List<Product> findAllBySeller(User seller);
}
