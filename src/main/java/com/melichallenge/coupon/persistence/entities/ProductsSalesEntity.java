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

  // TODO Dejar bien documentado nivel entidad.
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_name")
  private String productName;

  @Column(name = "quantity")
  private Long quantity;

  // TODO: Should exist a table that contains all the MLA products.

  public ProductsSalesEntity(String productName, Long quantity) {
    this.productName = productName;
    this.quantity = quantity;
  }
}
