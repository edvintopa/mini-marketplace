package com.example.minimarketplace.controller;


import com.example.minimarketplace.model.product.Product;
import com.example.minimarketplace.model.product.ProductColor;
import com.example.minimarketplace.model.product.ProductCondition;
import com.example.minimarketplace.model.product.ProductStatus;
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

    @Autowired
    ProductRepository productRepository;
    @GetMapping(value = "/get")
    public ResponseEntity<List<Product>> getAllProducts(){
        try{
            List<Product> products = new ArrayList<>();
            products = productRepository.findAll();

            return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getProduct/{productId}")
    public ResponseEntity getProductById(@RequestBody UUID productID){
        try{
            Clothing product = (Clothing) productRepository.getReferenceById(productID);

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
    public ResponseEntity createClothing(@RequestBody Product product){
        try{
            Clothing newClothing1 = (Clothing) product;

            Clothing newClothing = productRepository.save(new Clothing(((Clothing) product).getSeason(),
                    ((Clothing) product).getSex(),
                    ((Clothing) product).getSize(),
                    ((Clothing) product).getType(),
                    product.getSeller_id(),
                    product.getTitle(),
                    product.getDescription(),
                    product.getManufacturer(),
                    product.getDatePosted(),
                    product.getPrice(),
                    product.getProductCondition(),
                    product.getProductColor(),
                    product.getProductStatus()));
            return new ResponseEntity(product.getProduct_id().toString(), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(value = "/editClothing")
    public ResponseEntity editClothing(@RequestBody UUID productID, Product updatedClothing){
            try{
                Clothing existingClothing = (Clothing) productRepository.getReferenceById(productID);
                Clothing newClothing = (Clothing) updatedClothing;

                if(existingClothing == null) {
                    return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
                }
                // Update all fields of the existing clothing product with the new data
                existingClothing.setSeason(newClothing.getSeason());
                existingClothing.setSex(newClothing.getSex());
                existingClothing.setSize(newClothing.getSize());
                existingClothing.setType(newClothing.getType());
                existingClothing.setSeller_id(newClothing.getSeller_id());
                existingClothing.setTitle(newClothing.getTitle());
                existingClothing.setDescription(newClothing.getDescription());
                existingClothing.setManufacturer(newClothing.getManufacturer());
                existingClothing.setDatePosted(newClothing.getDatePosted());
                existingClothing.setPrice(newClothing.getPrice());
                existingClothing.setProductCondition(newClothing.getProductCondition());
                existingClothing.setProductColor(newClothing.getProductColor());
                existingClothing.setProductStatus(newClothing.getProductStatus());

                productRepository.save(existingClothing);
                return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
            }catch (Exception e){
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

}
