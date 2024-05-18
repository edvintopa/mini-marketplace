package com.example.minimarketplace.controller.order;

import com.example.minimarketplace.model.communication.request.order.OrderRequest;
import com.example.minimarketplace.model.communication.response.ErrorResponse;
import com.example.minimarketplace.model.communication.response.order.OrderResponse;
import com.example.minimarketplace.model.order.Order;
import com.example.minimarketplace.model.product.Product;
import com.example.minimarketplace.model.product.ProductStatus;
import com.example.minimarketplace.model.user.User;
import com.example.minimarketplace.repository.order.OrderRepository;
import com.example.minimarketplace.repository.product.ProductRepository;
import com.example.minimarketplace.repository.user.UserRepository;
import com.example.minimarketplace.service.TokenResolverService;
import com.example.minimarketplace.service.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-17
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {

    private final UserCartService cartService;
    private final TokenResolverService tokenResolverService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public OrderController(UserCartService cartService, TokenResolverService tokenResolverService) {
        this.cartService = cartService;
        this.tokenResolverService = tokenResolverService;
    }

    /**
     * This method is mapped to the "/get" endpoint and is responsible for fetching all orders associated with the authenticated user.
     * It uses the JWT token from the request header to identify the user.
     *
     * @param token The JWT token from the request header. It is used to authenticate and identify the user.
     * @return A ResponseEntity containing a list of OrderResponse objects if successful, or an ErrorResponse object if an exception occurs.
     * <p>
     * @author edvintopa
     */
    @GetMapping("/get")
    public ResponseEntity getOrders(@RequestHeader("Authorization") String token) {
        try {
            UUID buyerId = tokenResolverService.resolveTokenToUserId(token);
            List<Order> orders = orderRepository.findAllByBuyerId(buyerId); //Get users orders

            List<OrderResponse> response = new ArrayList<>();

            for (Order order : orders) {

                response.add(new OrderResponse(
                        order.getOrderId(),
                        order.getOrderDate(),
                        order.getTotal(),
                        order.isConfirmed(),
                        order.getProductId()
                ));
            }

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestHeader("Authorization") String token, @RequestBody OrderRequest request) {
        try {
            UUID buyerId = tokenResolverService.resolveTokenToUserId(token);
            if (cartService.removeFromCart(buyerId, request.getId())) {  //If cartItem found

                Product product = productRepository.findByProductId(request.getId());
                product.setProductStatus(ProductStatus.ON_HOLD);
                productRepository.save(product);

                orderRepository.save(new Order(
                        buyerId,
                        product.getPrice(),
                        request.getId()
                ));

                return ResponseEntity.status(HttpStatus.CREATED).body("Order created");
            } else {
                ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Cart item not found");
                return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
            }
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
        }
    }

    @PostMapping("/cancel")
    public ResponseEntity cancelOrder(@RequestBody OrderRequest request) {
        try {
            Order order = orderRepository.findOrderByOrderId(request.getId());

            if (order.isConfirmed()) {  //if order is confirmed, user may not cancel
                ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Order is confirmed");
                return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
            } else {
                orderRepository.delete(order);  //delete order

                Product product = productRepository.findByProductId(order.getProductId());
                product.setProductStatus(ProductStatus.AVAILABLE);
                productRepository.save(product);    //change to available

                return ResponseEntity.status(HttpStatus.OK).body("Order removed");
            }
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
        }
    }

    @GetMapping("/sellorder")
    public ResponseEntity getSellOrders(@RequestHeader("Authorization") String token) {
        try {
            UUID sellerId = tokenResolverService.resolveTokenToUserId(token);
            User seller = userRepository.findByUserId(sellerId);
            List<Product> products = productRepository.findAllBySeller(seller);
            List<Order> sellOrders = new ArrayList<>();

            for (Product p : products) {
                Order sellOrder = orderRepository.findOrderByProductId(p.getProductId());

                if (sellOrder != null) {        //filter out all products that are not in an order
                    sellOrders.add(sellOrder);
                }
            }

            List<OrderResponse> response = new ArrayList<>();
            for (Order order : sellOrders) {
                response.add(new OrderResponse(
                        order.getOrderId(),
                        order.getOrderDate(),
                        order.getTotal(),
                        order.isConfirmed(),
                        order.getProductId()
                ));
            }

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
        }
    }

    @PostMapping("/confirm")
    public ResponseEntity confirmOrder(@RequestHeader("Authorization") String token, @RequestBody OrderRequest request) {
        try {
            UUID sellerId = tokenResolverService.resolveTokenToUserId(token);
            Order sellOrder = orderRepository.findOrderByOrderId(request.getId());
            Product product = productRepository.findByProductId(sellOrder.getProductId());
            User seller = product.getSeller();

            if (sellerId == seller.getUserId()) {
                product.setProductStatus(ProductStatus.NOT_AVAILABLE);
                sellOrder.setConfirmed(true);

                productRepository.save(product);
                orderRepository.save(sellOrder);

                return ResponseEntity.status(HttpStatus.OK).body("Order confirmed");
            } else {
                ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "User is not seller of product");
                return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
            }
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
        }
    }
}