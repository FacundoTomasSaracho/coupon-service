package com.melichallenge.coupon.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonPropertyOrder({"itemsId", "total"})
@Getter
@Setter
@AllArgsConstructor
public class MaximizedTotalToSpendDto {
    @JsonProperty("items_ids")
    private List<String> itemsIds;
    private Double total;
}
