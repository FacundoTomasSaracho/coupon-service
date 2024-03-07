package com.melichallenge.coupon.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
public record FavouritesClientItemsWithCouponDto(
    @NotNull Integer amount, @JsonProperty("items_ids") @NotBlank List<String> itemsIds) {

}
