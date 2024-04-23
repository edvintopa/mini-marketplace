package com.example.minimarketplace.model.product.products.clothing;

import com.example.minimarketplace.model.product.*;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

/**
 * @author edvintopa, Tiffany Dizdar
 * @project mini-marketplace
 * @created 2024-04-08
 */

@Entity
@Table(name ="garment")
public class Clothing extends Product {

    //Clothing attributes
    @Enumerated(EnumType.STRING) //represented as string in database
    @Column(
            name = "season",
            nullable = false,
            columnDefinition = "varchar(50)"
    )
    private ClothingSeason season;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "sex",
            nullable = false,
            columnDefinition = "varchar(50)"
    )
    private ClothingSex sex;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "size",
            nullable = false,
            columnDefinition = "varchar(50)"
    )
    private ClothingSize size;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "type",
            nullable = false,
            columnDefinition = "varchar(50)"
    )
    private ClothingType type;

    public ClothingSeason getSeason() {
        return season;
    }

    public void setSeason(ClothingSeason season) {
        this.season = season;
    }

    public ClothingSex getSex() {
        return sex;
    }

    public void setSex(ClothingSex sex) {
        this.sex = sex;
    }

    public ClothingSize getSize() {
        return size;
    }

    public void setSize(ClothingSize size) {
        this.size = size;
    }

    public ClothingType getType() {
        return type;
    }

    public void setType(ClothingType type) {
        this.type = type;
    }


}
