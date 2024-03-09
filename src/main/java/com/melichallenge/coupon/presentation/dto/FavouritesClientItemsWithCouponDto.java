package com.melichallenge.coupon.presentation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FavouritesClientItemsWithCouponDto(
        @NotNull @Schema(example = "100000") Integer amount,
        @JsonProperty("items_ids")
        @NotEmpty
        @Schema(
                example =
                        "[\"MLA1520547502\", \"MLA1520547502\", \"MLA1520547502\", \"MLA1520547502\", \"MLA869973285\", \"MLA901188385\", \"MLA869973285\", \"MLA901188385\", \"MLA869973285\", \"MLA901188385\"]")
        List<String> itemsIds) {
}
