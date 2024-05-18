package com.example.minimarketplace.controller.cart;

import com.example.minimarketplace.model.communication.request.cart.CartRequest;
import com.example.minimarketplace.model.communication.response.ErrorResponse;
import com.example.minimarketplace.model.communication.response.cart.CartResponse;
import com.example.minimarketplace.service.TokenResolverService;
import com.example.minimarketplace.service.UserCartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author edvintopa
 * @project mini-marketplace
 * @created 2024-05-18
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {

    private final UserCartService cartService;
    private final TokenResolverService tokenResolverService;

    public CartController(UserCartService cartService, TokenResolverService tokenResolverService) {
        this.cartService = cartService;
        this.tokenResolverService = tokenResolverService;
    }

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestHeader("Authorization") String token, @RequestBody CartRequest request) {

        try {
            UUID userId = tokenResolverService.resolveTokenToUserId(token);
            cartService.addToCart(userId, request.getProductId());

            CartResponse cartResponse = new CartResponse(request.getProductId());
            return ResponseEntity.status(HttpStatus.OK).body(cartResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @PostMapping("/remove")
    public ResponseEntity removeFromCart(@RequestHeader("Authorization") String token, @RequestBody CartRequest request) {

        try {
            UUID userId = tokenResolverService.resolveTokenToUserId(token);
            if (cartService.removeFromCart(userId, request.getProductId())) {
                CartResponse cartResponse = new CartResponse(request.getProductId());
                return ResponseEntity.status(HttpStatus.OK).body(cartResponse);
            } else {
                ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Item not found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            }
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping("/get")
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

    @GetMapping("/clear")
    public ResponseEntity clearCart(@RequestHeader("Authorization") String token) {

        try {
            UUID userId = tokenResolverService.resolveTokenToUserId(token);
            cartService.clearCart(userId);

            return ResponseEntity.status(HttpStatus.OK).body("Cart cleared");
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

}
