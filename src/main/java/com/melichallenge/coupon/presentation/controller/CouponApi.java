package com.melichallenge.coupon.presentation.controller;


import com.melichallenge.coupon.exception.BusinessException;
import com.melichallenge.coupon.presentation.controller.impl.CouponController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Benefits")
public interface CouponApi {

    @Operation(
            operationId = "maximizeCoupon",
            summary = "Maximize the use a client can give to a discount coupon")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    @ArraySchema(schema = @Schema(implementation = CouponController.ItemsAndAmount.class))
    @PostMapping({"coupon"})
    ResponseEntity<CouponController.ItemsAndAmount> maximizeCouponBenefit(@RequestBody @Valid CouponController.ItemsAndAmount request) throws BusinessException;
}
