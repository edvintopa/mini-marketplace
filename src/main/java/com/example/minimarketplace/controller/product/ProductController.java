package com.example.minimarketplace.controller.product;


import com.example.minimarketplace.auth.JwtUtil;
import com.example.minimarketplace.model.communication.request.product.ClothingCreateRequest;
import com.example.minimarketplace.model.communication.response.ErrorResponse;
import com.example.minimarketplace.model.communication.response.product.ClothingCreateResponse;
import com.example.minimarketplace.model.product.ProductColor;
import com.example.minimarketplace.model.product.ProductCondition;
import com.example.minimarketplace.model.product.ProductStatus;
import com.example.minimarketplace.model.user.User;
import com.example.minimarketplace.repository.product.ProductRepository;
import com.example.minimarketplace.repository.user.UserRepository;
import com.example.minimarketplace.model.product.Product;
import com.example.minimarketplace.model.product.products.clothing.*;
import com.example.minimarketplace.service.TokenResolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")

public class ProductController {

    private final JwtUtil jwtUtil;
    private final TokenResolverService tokenResolverService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    public ProductController(JwtUtil jwtUtil, TokenResolverService tokenResolverService) {
        this.jwtUtil = jwtUtil;
        this.tokenResolverService = tokenResolverService;
    }

    /**
     * This method is used to get all the products sold by a specific seller.
     * It takes the Authorization token from the request header, resolves it to a User ID, and fetches the seller from the database.
     * It then fetches all the products sold by this seller from the database and returns them in the response.
     * If any exception occurs during this process, it returns an ErrorResponse with the status code INTERNAL_SERVER_ERROR and the exception message.
     *
     * @param token The Authorization token from the request header. It is used to identify the seller.
     * @return ResponseEntity containing a list of products sold by the seller if successful, or an ErrorResponse if an exception occurs.
     * @author edvintopa
     */
    @GetMapping("/getmy")
    public ResponseEntity getSellerProducts(@RequestHeader("Authorization") String token) {
        try {
            UUID userId = tokenResolverService.resolveTokenToUserId(token);
            User seller = userRepository.findByUserId(userId);
            List<Product> products = productRepository.findAllBySeller(seller);

            return ResponseEntity.status(HttpStatus.OK).body(products);     //TODO: Create appropriate response. A lot of unnecessary info is sent.
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
        }
    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Product>> getAllProducts(){
        try{
            System.out.println("Fetching all products");
            List<Product> products = new ArrayList<>();
            products = productRepository.findAll();
            return new ResponseEntity<List<Product>>(products,HttpStatus.OK);   //TODO: Create appropriate response. A lot of unnecessary info is sent.
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getProduct")
    public ResponseEntity getProductById(@RequestBody UUID productId){
        try{
            Clothing product = (Clothing) productRepository.findById(productId).orElse(null);

            if (product != null){
                return new ResponseEntity<>(product, HttpStatus.OK);    //TODO: Create appropriate response. A lot of unnecessary info is sent.
            }else{
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            return new ResponseEntity<>("Failed to fetch product", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/createClothing")
    public ResponseEntity createClothing(@RequestBody ClothingCreateRequest clothing, @RequestHeader("Authorization") String token){
        try{
            String username = jwtUtil.getBearer(token.replace("Bearer ", ""));
            User user = userRepository.findByUsername(username);
            System.out.println(clothing);
            //ALL ENUMS
            ProductCondition productCondition = ProductCondition.valueOf(clothing.getProductcondition().toUpperCase());
            ProductColor productColor = ProductColor.valueOf(clothing.getProductcolor().toUpperCase());
            ClothingSeason season = ClothingSeason.valueOf(clothing.getSeason().toUpperCase());
            ClothingSex sex = ClothingSex.valueOf(clothing.getSex().toUpperCase());
            ClothingSize size = ClothingSize.valueOf(clothing.getSize().toUpperCase());
            ClothingType type = ClothingType.valueOf(clothing.getType().toUpperCase());

            Clothing newClothing = productRepository.save(new Clothing(
                    user,
                    clothing.getTitle(),
                    clothing.getDescription(),
                    clothing.getManufacturer(),
                    new Date(),
                    clothing.getPrice(),
                    productCondition,
                    productColor,
                    ProductStatus.AVAILABLE,
                    season,
                    sex,
                    size,
                    type)
            );

            //productPublisher.notifyProductAvailability(savedProduct.getName(), savedProduct.getType());

            System.out.println(newClothing);
            ClothingCreateResponse response = new ClothingCreateResponse(newClothing.getProductId(), HttpStatus.CREATED);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
//    @PostMapping(value = "/editClothing")
//    public ResponseEntity editClothing(@RequestBody UUID productID, Product updatedClothing){
//        try{
//            Clothing existingClothing = (Clothing) productRepository.getReferenceById(productID);
//            Clothing newClothing = (Clothing) updatedClothing;
//
//            if(existingClothing == null) {
//                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
//            }
//            // Update all fields of the existing clothing product with the new data
//            existingClothing.setSeason(newClothing.getSeason());
//            existingClothing.setSex(newClothing.getSex());
//            existingClothing.setSize(newClothing.getSize());
//            existingClothing.setType(newClothing.getType());
//            existingClothing.setSeller(newClothing.getSeller());
//            existingClothing.setTitle(newClothing.getTitle());
//            existingClothing.setDescription(newClothing.getDescription());
//            existingClothing.setManufacturer(newClothing.getManufacturer());
//            existingClothing.setDatePosted(newClothing.getDatePosted());
//            existingClothing.setPrice(newClothing.getPrice());
//            existingClothing.setProductCondition(newClothing.getProductCondition());
//            existingClothing.setProductColor(newClothing.getProductColor());
//            existingClothing.setProductStatus(newClothing.getProductStatus());
//
//            productRepository.save(existingClothing);
//            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
