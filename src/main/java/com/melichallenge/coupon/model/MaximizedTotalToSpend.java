package com.melichallenge.coupon.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MaximizedTotalToSpend {
  private List<String> itemsIds;
  private Double total;
}
