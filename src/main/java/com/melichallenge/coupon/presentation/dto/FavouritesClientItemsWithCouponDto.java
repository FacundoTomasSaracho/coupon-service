package com.melichallenge.coupon.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FavouritesClientItemsWithCouponDto {
    @JsonProperty("items_ids")
    @NotBlank
    private List<String> itemsIds;
    @NotNull
    private Integer amount;

}
