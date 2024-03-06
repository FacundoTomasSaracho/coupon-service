package com.melichallenge.coupon.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BusinessException extends Exception {

    //TODO MeliClient & project should have their own error structure.

    private Integer code;
    private String message;
}
