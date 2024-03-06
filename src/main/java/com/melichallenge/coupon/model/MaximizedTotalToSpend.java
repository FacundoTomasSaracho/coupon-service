package com.melichallenge.coupon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MaximizedTotalToSpend {
    private List<String> itemsIds;
    private Double total;

}
