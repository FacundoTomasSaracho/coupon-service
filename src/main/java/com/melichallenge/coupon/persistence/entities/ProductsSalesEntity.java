package com.melichallenge.coupon.persistence.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_sales")
@Getter
@Setter
@NoArgsConstructor
public class ProductsSalesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    public ProductsSalesEntity(ProductEntity product) {
        this.product = product;
    }
}
