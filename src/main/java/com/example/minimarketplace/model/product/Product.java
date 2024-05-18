package com.example.minimarketplace.model.product;
import com.example.minimarketplace.model.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

/**
 * @author Tiffany Dizdar
 * @project mini-marketplace
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name ="product")
public abstract class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator" //deprecated, find alternative (still works)
    )
    @Column(
            name = "product_id",
            updatable = false
    )
    private UUID product_id;

    @ManyToOne( //FK mapping (one seller, many products)
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "seller", //name in product table
            referencedColumnName = "user_id", //name in user table
            nullable = false,
            updatable = false
    )
    private User seller;

    @Column(
            name = "title",
            nullable = false
    )
    private java.lang.String title;

    @Column(
            name = "description",
            columnDefinition = "VARCHAR(50)"
    )
    private java.lang.String description;

    @Column(
            name = "manufacturer",
            nullable = false,
            columnDefinition = "VARCHAR(50)"
    )
    private java.lang.String manufacturer;

    @Column(
            name = "date_posted",
            nullable = false,
            updatable = false,
            columnDefinition = "DATE"
    )
    private Date datePosted;

    @Column(
            name = "price",
            nullable = false
    )
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "condition",
            nullable = false
    )
    private ProductCondition productCondition;


    @Enumerated(EnumType.STRING)
    @Column(
            name = "color",
            nullable = false
    )
    private ProductColor productColor;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "status",
            nullable = false
    )
    private ProductStatus productStatus;

    @Column(
            name = "product_image",
            nullable = false
    )
    private String imagePath;

    public Product(User seller,
                   String title,
                   String description,
                   String manufacturer,
                   Date datePosted,
                   double price,
                   ProductCondition productCondition,
                   ProductColor productColor,
                   ProductStatus productStatus,
                   String imagePath) {
        this.seller = seller;
        this.title = title;
        this.description = description;
        this.manufacturer = manufacturer;
        this.datePosted = datePosted;
        this.price = price;
        this.productCondition = productCondition;
        this.productColor = productColor;
        this.productStatus = productStatus;
        this.imagePath = imagePath;
    }

    public Product() {

    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public ProductColor getProductColor() {
        return productColor;
    }

    public void setProductColor(ProductColor productColor) {
        this.productColor = productColor;
    }

    public ProductCondition getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(ProductCondition productCondition) {
        this.productCondition = productCondition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDatePosted() {
        return datePosted;
    }

    public void setDatePosted(Date datePosted) {
        this.datePosted = datePosted;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller_id) {
        this.seller = seller_id;
    }

    public UUID getProduct_id() {
        return product_id;
    }

    public void setProduct_id(UUID product_id) {
        this.product_id = product_id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String productImage) {
        this.imagePath = productImage;
    }

}