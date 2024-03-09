package com.melichallenge.coupon.presentation.exception;

import static com.melichallenge.coupon.exception.enums.ResponseCode.BAD_REQUEST;
import static com.melichallenge.coupon.exception.enums.ResponseCode.INTERNAL_SERVER_ERROR;

import com.melichallenge.coupon.exception.BusinessException;
import com.melichallenge.coupon.presentation.exception.dto.ErrorDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@ControllerAdvice
@Log4j2
public class RestExceptionHandler {

  // TODO add more errors - handle NotNull & NotBlank

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDto> handleException(Exception exception) {
    log.error("Exception: ", exception);
    return new ResponseEntity<>(
        new ErrorDto(INTERNAL_SERVER_ERROR.getCode(), INTERNAL_SERVER_ERROR.getMessage()),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorDto> handleException(HttpMessageNotReadableException exception) {
    log.error("HttpMessageNotReadableException: ", exception);
    return new ResponseEntity<>(
        new ErrorDto(BAD_REQUEST.getCode(), BAD_REQUEST.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<ErrorDto> handleException(BusinessException exception) {
    log.error("Business exception: ", exception);
    return new ResponseEntity<>(
        new ErrorDto(exception.getCode(), exception.getMessage()), HttpStatus.BAD_REQUEST);
  }
}
