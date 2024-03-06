package com.melichallenge.coupon.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BestRedeemedProductsDto {

  private String productName;
  private Long quantity;
}
