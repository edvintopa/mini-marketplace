package com.example.minimarketplace.repository.order;

import com.example.minimarketplace.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<Order> findAllByBuyerId(UUID userId);

    Order findOrderByBuyerId(UUID userId);

    Order findOrderByOrderId(UUID orderId);

    Order findOrderByProductId(UUID productId);
}
