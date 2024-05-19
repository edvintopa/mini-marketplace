package com.example.minimarketplace.controller.order;

import com.example.minimarketplace.model.communication.request.order.OrderRequest;
import com.example.minimarketplace.model.communication.response.ErrorResponse;
import com.example.minimarketplace.model.communication.response.order.OrderResponse;
import com.example.minimarketplace.model.order.Order;
import com.example.minimarketplace.model.product.Product;
import com.example.minimarketplace.model.product.ProductStatus;
import com.example.minimarketplace.model.product.products.clothing.Clothing;
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

    /**
     * This method is mapped to the "/create" endpoint and is responsible for creating a new order.
     * It uses the JWT token from the request header to identify the user (buyer).
     * It also takes an OrderRequest object from the request body which contains the ID of the product to be ordered.
     * <p>
     * The method first checks if the product is in the user's cart. If it is, the product is removed from the cart,
     * its status is set to ON_HOLD, and a new order is created and saved in the database.
     * <p>
     * If the product is not in the user's cart, an ErrorResponse is returned with the status code BAD_REQUEST and a message indicating that the cart item was not found.
     * <p>
     * If any exception occurs during this process, an ErrorResponse is returned with the status code INTERNAL_SERVER_ERROR and the exception message.
     *
     * @param token The JWT token from the request header. It is used to authenticate and identify the user (buyer).
     * @param request An OrderRequest object from the request body. It contains the ID of the product to be ordered.
     * @return A ResponseEntity containing a message indicating that the order was created if successful, or an ErrorResponse object if an exception occurs.
     * @author edvintopa
     */
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

    /**
     * This method is mapped to the "/cancel" endpoint and is responsible for cancelling an existing order.
     * It takes an OrderRequest object from the request body which contains the ID of the order to be cancelled.
     * <p>
     * The method first checks if the order is confirmed. If it is, an ErrorResponse is returned with the status code BAD_REQUEST and a message indicating that the order is confirmed and cannot be cancelled.
     * <p>
     * If the order is not confirmed, it is deleted from the database, the status of the product associated with the order is set to AVAILABLE, and a message indicating that the order was removed is returned in the response.
     * <p>
     * If any exception occurs during this process, an ErrorResponse is returned with the status code INTERNAL_SERVER_ERROR and the exception message.
     *
     * @param request An OrderRequest object from the request body. It contains the ID of the order to be cancelled.
     * @return A ResponseEntity containing a message indicating that the order was removed if successful, or an ErrorResponse object if an exception occurs.
     * @author edvintopa
     */
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

    /**
     * This method is mapped to the "/sellorder" endpoint and is responsible for fetching all orders associated with the authenticated user who is a seller.
     * It uses the JWT token from the request header to identify the user (seller).
     * <p>
     * The method first resolves the JWT token to a User ID and fetches the seller from the database.
     * It then fetches all the products sold by this seller from the database.
     * For each product, it checks if there is an order associated with it. If there is, it adds the order to a list of sell orders.
     * <p>
     * It then creates a list of OrderResponse objects from the list of sell orders and returns it in the response.
     * <p>
     * If any exception occurs during this process, it returns an ErrorResponse with the status code INTERNAL_SERVER_ERROR and the exception message.
     *
     * @param token The JWT token from the request header. It is used to authenticate and identify the user (seller).
     * @return ResponseEntity containing a list of OrderResponse objects if successful, or an ErrorResponse object if an exception occurs.
     * @author edvintopa
     */
    @GetMapping("/sellorder")
    public ResponseEntity getSellOrders(@RequestHeader("Authorization") String token) {
        try {
            UUID sellerId = tokenResolverService.resolveTokenToUserId(token);
            User seller = userRepository.findByUserId(sellerId);
            List<Clothing> products = productRepository.findAllBySeller(seller);
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

    /**
     * This method is mapped to the "/confirm" endpoint and is responsible for confirming an order.
     * It uses the JWT token from the request header to identify the user (seller).
     * It also takes an OrderRequest object from the request body which contains the ID of the order to be confirmed.
     * <p>
     * The method first resolves the JWT token to a User ID and fetches the order and the product associated with the order from the database.
     * It then checks if the ID of the seller of the product matches the User ID. If it does, it sets the status of the product to NOT_AVAILABLE,
     * confirms the order, and saves the product and the order in the database.
     * <p>
     * If the ID of the seller of the product does not match the User ID, it returns an ErrorResponse with the status code BAD_REQUEST and a message indicating that the user is not the seller of the product.
     * <p>
     * If any exception occurs during this process, it returns an ErrorResponse with the status code INTERNAL_SERVER_ERROR and the exception message.
     *
     * @param token The JWT token from the request header. It is used to authenticate and identify the user (seller).
     * @param request An OrderRequest object from the request body. It contains the ID of the order to be confirmed.
     * @return A ResponseEntity containing a message indicating that the order was confirmed if successful, or an ErrorResponse object if an exception occurs.
     * @author edvintopa
     */
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

    /**
     * This method is mapped to the "/reject" endpoint and is responsible for rejecting an order.
     * It uses the JWT token from the request header to identify the user (seller).
     * It also takes an OrderRequest object from the request body which contains the ID of the order to be rejected.
     *
     * The method first resolves the JWT token to a User ID and fetches the order and the product associated with the order from the database.
     * It then checks if the ID of the seller of the product matches the User ID. If it does, it sets the status of the product to AVAILABLE,
     * deletes the order, and saves the product in the database.
     *
     * If the ID of the seller of the product does not match the User ID, it returns an ErrorResponse with the status code BAD_REQUEST and a message indicating that the user is not the seller of the product.
     *
     * If any exception occurs during this process, it returns an ErrorResponse with the status code INTERNAL_SERVER_ERROR and the exception message.
     *
     * @param token The JWT token from the request header. It is used to authenticate and identify the user (seller).
     * @param request An OrderRequest object from the request body. It contains the ID of the order to be rejected.
     * @return A ResponseEntity containing a message indicating that the order was rejected and deleted if successful, or an ErrorResponse object if an exception occurs.
     * @author edvintopa
     */
    @PostMapping("/reject")
    public ResponseEntity rejectOrder(@RequestHeader("Authorization") String token, @RequestBody OrderRequest request) {
        try {
            UUID sellerId = tokenResolverService.resolveTokenToUserId(token);
            Order sellOrder = orderRepository.findOrderByOrderId(request.getId());
            Product product = productRepository.findByProductId(sellOrder.getProductId());
            User seller = product.getSeller();

            if (sellerId == seller.getUserId()) {
                product.setProductStatus(ProductStatus.AVAILABLE);

                productRepository.save(product);
                orderRepository.delete(sellOrder);

                return ResponseEntity.status(HttpStatus.OK).body("Order rejected and deleted");

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
