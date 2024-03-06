package com.melichallenge.coupon.client.mercadolibre.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private Integer code;
    @JsonProperty("body")
    private ItemBody itemBody;
}
