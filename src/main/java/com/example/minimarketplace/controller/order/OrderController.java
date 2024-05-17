package com.example.minimarketplace.controller.order;

import com.example.minimarketplace.auth.JwtUtil;
import com.example.minimarketplace.model.communication.response.ErrorResponse;
import com.example.minimarketplace.model.communication.response.order.OrderResponse;
import com.example.minimarketplace.model.order.Order;
import com.example.minimarketplace.model.user.User;
import com.example.minimarketplace.repository.order.OrderRepository;
import com.example.minimarketplace.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    public OrderController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
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
    @GetMapping("/myorders")
    public ResponseEntity getMyOrders(@RequestHeader("Authorization") String token) {
        try {
            String username = jwtUtil.getBearer(token.replace("Bearer ", ""));  //Resolve token
            User buyer = userRepository.findByUsername(username);   //Find user
            List<Order> orders = orderRepository.findAllByBuyerId(buyer.getUserId()); //Get users orders

            List<OrderResponse> response = new ArrayList<>();

            for (Order order : orders) {

                User seller = userRepository.findByUserId(order.getSellerId());

                response.add(new OrderResponse(
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



}
