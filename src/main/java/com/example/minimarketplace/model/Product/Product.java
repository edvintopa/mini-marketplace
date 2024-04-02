package com.example.minimarketplace.model.Product;
import com.example.minimarketplace.model.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;
@Entity
@Table(name ="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID productId;

    @Column(name = "seller")
    private User seller;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "dateOfProduction")
    private Date dateOfProduction;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductType type;

   /* @Column(name = "picture")
    private Picture picture;
    */

    @Column(name = "price")
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProductStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "color")
    private ProductColor productColor;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition")
    private ProductCondition productCondition;
    public UUID getProductId() {
        return productId;
    }

    public User getSeller() {
        return seller;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateOfProduction() {
        return dateOfProduction;
    }

    public ProductType getType() {
        return type;
    }

   /* public Picture getPicture() {
        return picture;
    }*/

    public double getPrice() {
        return price;
    }

    public ProductStatus getStatus() {
        return status;
    }
}
