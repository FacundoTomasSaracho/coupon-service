package com.melichallenge.coupon.client.mercadolibre.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemBody {
    private Double price;
    @JsonProperty("id")
    private String itemId;
    @JsonProperty("site_id")
    private String siteId;
}
