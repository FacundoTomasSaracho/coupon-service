package com.melichallenge.coupon.persistence.entities;


import jakarta.persistence.*;
import lombok.Getter;

@Table(name = "products")
@Entity
@Getter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false, length = 50)
    private String productName;

    @Column(name = "description", length = 200)
    private String description;

    public ProductEntity (Long id){
        this.id = id;
    }
}


