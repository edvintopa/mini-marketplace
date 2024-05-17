package com.example.minimarketplace.controller.order;

import com.example.minimarketplace.auth.JwtUtil;
import com.example.minimarketplace.model.communication.request.cart.AddToCartRequest;
import com.example.minimarketplace.model.communication.response.ErrorResponse;
import com.example.minimarketplace.model.communication.response.cart.AddToCartResponse;
import com.example.minimarketplace.model.communication.response.order.GetOrdersResponse;
import com.example.minimarketplace.model.order.Order;
import com.example.minimarketplace.model.user.User;
import com.example.minimarketplace.repository.order.OrderRepository;
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

    private JwtUtil jwtUtil;
    private final UserCartService cartService;
    private final TokenResolverService tokenResolverService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    public OrderController(JwtUtil jwtUtil, UserCartService cartService, TokenResolverService tokenResolverService) {
        this.jwtUtil = jwtUtil;
        this.cartService = cartService;
        this.tokenResolverService = tokenResolverService;
    }

    /**
     * This method is mapped to the "/myorders" endpoint and is responsible for fetching all orders associated with the authenticated user.
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

            List<GetOrdersResponse> response = new ArrayList<>();

            for (Order order : orders) {

                User seller = userRepository.findByUserId(order.getSellerId());

                response.add(new GetOrdersResponse(
                        seller.getUsername(),
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

    @PostMapping("/addtocart")
    public ResponseEntity addToCart(@RequestHeader("Authorization") String token, @RequestBody AddToCartRequest request) {

        try {
            UUID userId = tokenResolverService.resolveTokenToUserId(token);
            cartService.addToCart(userId, request.getProductId());

            AddToCartResponse addToCartResponse = new AddToCartResponse(request.getProductId(), userId);
            return ResponseEntity.status(HttpStatus.OK).body(addToCartResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/mycart")
    public ResponseEntity getCart(@RequestHeader("Authorization") String token) {

        try {
            UUID userId = tokenResolverService.resolveTokenToUserId(token);
            List<UUID> cartItems = cartService.getCart(userId);

            return ResponseEntity.status(HttpStatus.OK).body(cartItems);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
