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
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    public ProductController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;

    }

    @GetMapping(value = "/get")
    public ResponseEntity<List<Product>> getAllProducts(){
        try{
            System.out.println("Fetching all products");
            List<Product> products = new ArrayList<>();
            products = productRepository.findAll();
            return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getProduct/{productId}")
    public ResponseEntity getProductById(@PathVariable("productId") UUID productId){
        try{
            Clothing product = (Clothing) productRepository.findById(productId).orElse(null);

            if (product != null){
                return new ResponseEntity<>(product, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            return new ResponseEntity<>("Failed to fetch product", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/createClothing")
    public ResponseEntity createClothing(@RequestBody ClothingCreateRequest clothing, @RequestHeader("Authorization") java.lang.String token){
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
            ClothingCreateResponse response = new ClothingCreateResponse(newClothing.getProduct_id(), HttpStatus.CREATED);
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
