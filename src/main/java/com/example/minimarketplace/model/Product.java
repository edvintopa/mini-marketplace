package com.example.minimarketplace.model;
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

    @Column(name = "type")
    private ProductType type;

   /* @Column(name = "picture")
    private Picture picture;
    */
    @Column(name = "price")
    private double price;

    @Column(name = "status")
    private ProductStatus status;

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
