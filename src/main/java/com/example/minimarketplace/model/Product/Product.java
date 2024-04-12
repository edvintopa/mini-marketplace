package com.example.minimarketplace.model.Product;
import com.example.minimarketplace.model.User;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

/**
 * @author Tiffany Dizdar
 */
@Entity
@Table(name ="product")
public class Product {

    @Id //PK
    @SequenceGenerator( //create a sequence for id incrementation
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue( //specify usage of sequence
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    @Column(
            name = "product_id",
            updatable = false
    )
    private UUID productId;

    @OneToOne( //FK mapping
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "seller",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private User seller;

    @Column(
            name = "title",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String title;

    @Column(
            name = "description",
            columnDefinition = "TEXT"
    )
    private String description;

    @Column(
            name = "production_date",
            nullable = false,
            updatable = false,
            columnDefinition = "DATE" //Java DATE type
    )
    private Date dateOfProduction;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "type",
            nullable = false
    )
    private ProductType type;

    @Column(
            name = "price",
            nullable = false
    )
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "status",
            nullable = false
    )
    private ProductStatus status;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "color",
            nullable = false
    )
    private ProductColor productColor;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "condition",
            nullable = false
    )
    private ProductCondition productCondition;

    @Column(
            name = "manufacturer",
            columnDefinition = "TEXT"
    )
    private String manufacturer;

    @Column(
            name = "model"
    )
    private String model;

    @Column(
            name = "model_year"
    )
    private int modelYear; //int, only storing year

    /* @Column(name = "picture")
    private Picture picture;
    */ //TODO: make picture possible

    //id not included as it is auto generated.
    public Product(User seller, String title, String description, Date dateOfProduction,
                   ProductType type, double price, ProductColor color, ProductCondition condition,
                   String model, Date modeldate) {
        this.seller = seller;
        this.title = title;
        this.description = description;
        this.dateOfProduction = dateOfProduction;
        this.type = type;
        this.price = price;
        this.productColor = color;
        this.productCondition = condition;
    }

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

    public double getPrice() {
        return price;
    }

    public ProductStatus getStatus() {
        return status;
    }

    /* public Picture getPicture() {
        return picture;
    }*/
}
