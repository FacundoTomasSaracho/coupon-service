package com.melichallenge.coupon.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;

@JsonPropertyOrder({"itemsId", "total"})
public record MaximizedTotalToSpendDto(@JsonProperty("items_ids") List<String> itemsIds, Double total) {}
