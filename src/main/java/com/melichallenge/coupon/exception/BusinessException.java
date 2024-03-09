package com.melichallenge.coupon.exception;

import com.melichallenge.coupon.exception.enums.ResponseCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends Exception {

  // TODO MeliClient & project should have their own error structure.

  public BusinessException(ResponseCode responseCode) {
    this.code = responseCode.getCode();
    this.message = responseCode.getMessage();
  }

  private Integer code;
  private String message;
}
