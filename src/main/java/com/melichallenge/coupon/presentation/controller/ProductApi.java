package com.melichallenge.coupon.presentation.controller;

import com.melichallenge.coupon.presentation.controller.impl.ProductController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ProductApi {

    @Operation(operationId = "topProducts", summary = "Obtains a list of the most redeemed products.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
    @ArraySchema(schema = @Schema(implementation = ProductController.MostRedeemProductsDto.class))
    @GetMapping("most-redeemed-products")
    ResponseEntity<List<ProductController.MostRedeemProductsDto>> findTopRedeemedProducts();
}
