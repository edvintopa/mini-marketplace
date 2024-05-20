package com.example.minimarketplace.controller.product;


import com.example.minimarketplace.auth.JwtUtil;
import com.example.minimarketplace.component.event.ProductPublisher;
import com.example.minimarketplace.model.communication.request.product.ClothingCreateRequest;
import com.example.minimarketplace.model.communication.request.product.ClothingFilterRequest;
import com.example.minimarketplace.model.communication.request.product.ClothingGetProductRequest;
import com.example.minimarketplace.model.communication.response.ErrorResponse;
import com.example.minimarketplace.model.communication.response.product.ClothingCreateResponse;
import com.example.minimarketplace.model.communication.response.product.ClothingGetProductResponse;
import com.example.minimarketplace.model.communication.response.product.ClothingGetResponse;
import com.example.minimarketplace.model.product.*;
import com.example.minimarketplace.model.user.User;
import com.example.minimarketplace.repository.product.ProductRepository;
import com.example.minimarketplace.repository.user.UserRepository;
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
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")

public class ProductController {

    private final JwtUtil jwtUtil;
    private final ProductPublisher productPublisher;
    private final TokenResolverService tokenResolverService;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;


    public ProductController(JwtUtil jwtUtil, ProductPublisher productPublisher, TokenResolverService tokenResolverService) {
        this.jwtUtil = jwtUtil;
        this.productPublisher = productPublisher;
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

            List<ClothingGetResponse> response = new ArrayList<>();
            for (Product product : products) {
                response.add(new ClothingGetResponse(
                        product.getProductId(),
                        product.getTitle(),
                        product.getPrice(),
                        product.getImagePath()
                ));
            }

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
        }
    }


    @GetMapping(value = "/get")
    public ResponseEntity<?> getAllProducts(){
        try{
            System.out.println("Fetching all products");
            List<Product> products = new ArrayList<>();
            products = productRepository.findAll();
            List<Clothing> AvailableProducts = new ArrayList<>();


            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getProductStatus().equals(ProductStatus.AVAILABLE)){
                    AvailableProducts.add((Clothing) products.get(i));
                }

            }
            List<ClothingGetResponse> response = new ArrayList<>();
            for (Product product : AvailableProducts) {
                ClothingGetResponse clothingGetResponse = new ClothingGetResponse(
                        product.getProductId(),
                        product.getTitle(),
                        product.getPrice(),
                        product.getImagePath()
                );
                response.add(clothingGetResponse);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping(value = "/getProduct/{productId}")
    public ResponseEntity getProductById(@PathVariable UUID productId){
        try{
            Clothing product = (Clothing) productRepository.findById(productId).orElse(null);

            if (product != null){
                ClothingGetProductResponse response = new ClothingGetProductResponse(productId,
                        product.getTitle(),
                        product.getPrice(),
                        product.getProductStatus().name(),
                        product.getSeller().getUsername(),
                        product.getDescription(),
                        product.getSize().name(),
                        product.getProductCondition().name(),
                        product.getDatePosted(),
                        product.getImagePath()
                 );

                return ResponseEntity.status(HttpStatus.OK).body(response);
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

            //ALL ENUMS
            ProductCondition productCondition = ProductCondition.valueOf(clothing.getProductcondition().toUpperCase());
            ProductColor productColor = ProductColor.valueOf(clothing.getProductcolor().toUpperCase());
            ClothingSeason season = ClothingSeason.valueOf(clothing.getSeason().toUpperCase());
            ClothingSex sex = ClothingSex.valueOf(clothing.getSex().toUpperCase());
            ClothingSize size = ClothingSize.valueOf(clothing.getSize().toUpperCase());
            ClothingType type = ClothingType.valueOf(clothing.getType().toUpperCase());
            //TODO: Save Image to directory
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
                    clothing.getproductImage(),
                    season,
                    sex,
                    size,
                    type)
            );

            //NOTIFY ALL USERS WITH MATCHING INTEREST
            productPublisher.notifyProductAvailability(newClothing.getType().name());

            ClothingCreateResponse response = new ClothingCreateResponse(newClothing.getProductId(), HttpStatus.CREATED);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (Exception e){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    @GetMapping(value ="/filter")
    public ResponseEntity<?> filterProducts(@RequestBody String filterCriteria) {
        try {
            System.out.println(filterCriteria);
            List<Product> products = productRepository.findAll();
            List<Product> filteredProducts = new ArrayList<>();

            for (ClothingType type : ClothingType.values()) {
                if (filterCriteria.equals(type.name())) {
                    filteredProducts = products.stream()
                            .filter(product -> product instanceof Clothing && ((Clothing) product).getType().equals(type))
                            .collect(Collectors.toList());
                    return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
                }
            }

            for (ProductCondition condition : ProductCondition.values()) {
                if (filterCriteria.equalsIgnoreCase(condition.name())) {
                    filteredProducts = products.stream()
                            .filter(product -> product.getProductCondition().equals(condition))
                            .collect(Collectors.toList());
                    return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
                }
            }

            if (filterCriteria.contains("-")) {
                String[] priceRange = filterCriteria.split("-");
                double minPrice = Double.parseDouble(priceRange[0]);
                double maxPrice = Double.parseDouble(priceRange[1]);
                filteredProducts = products.stream()
                        .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                        .collect(Collectors.toList());
                return new ResponseEntity<>(filteredProducts, HttpStatus.OK);
            }

            return new ResponseEntity<>("Invalid filter criteria", HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    @PostMapping(value ="/filterAll")
    public ResponseEntity<?> filterProductsAll(@RequestBody ClothingFilterRequest filterRequest) {
        try {
            List<Product> products = productRepository.findAll();

            if (filterRequest.getClothingType() != null) {
                ClothingType type = ClothingType.valueOf(filterRequest.getClothingType().toUpperCase());
                products = products.stream()
                        .filter(product -> product instanceof Clothing && ((Clothing) product).getType().equals(type))
                        .collect(Collectors.toList());
            }

            if (filterRequest.getProductCondition() != null) {
                ProductCondition condition = ProductCondition.valueOf(filterRequest.getProductCondition().toUpperCase());
                products = products.stream()
                        .filter(product -> product.getProductCondition().equals(condition))
                        .collect(Collectors.toList());
            }

            if (filterRequest.getMaxPrice() != 0) {
                double minPrice = filterRequest.getMinPrice();
                double maxPrice = filterRequest.getMaxPrice();
                products = products.stream()
                        .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                        .collect(Collectors.toList());
                System.out.println(minPrice + " " + maxPrice);
            }
            List<ClothingGetResponse> response = new ArrayList<>();
            for (Product product : products) {
                ClothingGetResponse clothingGetResponse = new ClothingGetResponse(
                        product.getProductId(),
                        product.getTitle(),
                        product.getPrice()
                );
                response.add(clothingGetResponse);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

//ORDER DOES THIS DIRECTLY
//    @PutMapping(value = "/updateStatus")
//    public ResponseEntity updateStatus(@RequestBody UUID productId){
//        try {
//            Product existingProduct = productRepository.findById(productId).orElse(null);
//
//            if (existingProduct == null){
//                return new ResponseEntity( "Product not found", HttpStatus.NOT_FOUND);
//            }
//            existingProduct.setProductStatus(ProductStatus.NOT_AVAILABLE);
//
//            productRepository.save(existingProduct);
//
//            ClothingCreateResponse response = new ClothingCreateResponse(productId, HttpStatus.OK);
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        }catch (Exception e){
//            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
//        }
//    }
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
