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

    /**
     * This endpoint is used to add a product to a user's cart.
     * It takes the Authorization token from the request header, resolves it to a User ID, and uses it along with the product ID from the request body to add the product to the user's cart.
     * If the operation is successful, it returns a response with the status code OK and a CartResponse containing the ID of the added product.
     * If any exception occurs during this process, it returns an ErrorResponse with the status code INTERNAL_SERVER_ERROR and the exception message.
     *
     * @param token The Authorization token from the request header. It is used to identify the user.
     * @param request The request body, containing the ID of the product to be added to the user's cart.
     * @return ResponseEntity containing a CartResponse with the ID of the added product if successful, or an ErrorResponse if an exception occurs.
     * @author edvintopa
     */
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

    /**
     * This endpoint is used to remove an item from a user's cart.
     * It takes the Authorization token from the request header, resolves it to a User ID, and uses it along with the product ID from the request body to remove the item from the user's cart.
     * If the operation is successful, it returns a response with the status code OK and a CartResponse containing the ID of the removed product.
     * If the product is not found in the user's cart, it returns an ErrorResponse with the status code BAD_REQUEST and a message indicating that the item was not found.
     * If any other exception occurs during this process, it returns an ErrorResponse with the status code INTERNAL_SERVER_ERROR and the exception message.
     *
     * @param token The Authorization token from the request header. It is used to identify the user.
     * @param request The request body, containing the ID of the product to be removed from the user's cart.
     * @return ResponseEntity containing a CartResponse with the ID of the removed product if successful, an ErrorResponse with a message indicating that the item was not found if the product is not in the user's cart, or an ErrorResponse with the exception message if any other exception occurs.
     * @author edvintopa
     */
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

    /**
     * This endpoint is used to retrieve all items in a user's cart.
     * It takes the Authorization token from the request header, resolves it to a User ID, and uses it to fetch the user's cart.
     * If the operation is successful, it returns a response with the status code OK and a list of UUIDs representing the items in the cart.
     * If any exception occurs during this process, it returns an ErrorResponse with the status code INTERNAL_SERVER_ERROR and the exception message.
     *
     * @param token The Authorization token from the request header. It is used to identify the user.
     * @return ResponseEntity containing a list of UUIDs representing the items in the user's cart if successful, or an ErrorResponse if an exception occurs.
     * @author edvintopa
     */
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

    /**
     * This endpoint is used to clear all items from a user's cart.
     * It takes the Authorization token from the request header, resolves it to a User ID, and uses it to clear the user's cart.
     * If the operation is successful, it returns a response with the status code OK and a message indicating that the cart has been cleared.
     * If any exception occurs during this process, it returns an ErrorResponse with the status code INTERNAL_SERVER_ERROR and the exception message.
     *
     * @param token The Authorization token from the request header. It is used to identify the user.
     * @return ResponseEntity with a message indicating that the cart has been cleared if successful, or an ErrorResponse if an exception occurs.
     * @author edvintopa
     */
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
