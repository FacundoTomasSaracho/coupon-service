package com.melichallenge.coupon.presentation.controller;

import com.melichallenge.coupon.business.service.BestRedeemedProductsService;
import com.melichallenge.coupon.business.service.MaximizeCouponUseService;
import com.melichallenge.coupon.exception.BusinessException;
import com.melichallenge.coupon.model.MaximizedTotalToSpend;
import com.melichallenge.coupon.presentation.dto.BestRedeemedProductsDto;
import com.melichallenge.coupon.presentation.dto.FavouritesClientItemsWithCouponDto;
import com.melichallenge.coupon.presentation.dto.MaximizedTotalToSpendDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@Tag(name = "Benefits")
public class CouponController {

  private final MaximizeCouponUseService maximizeCouponUseService;
  private final BestRedeemedProductsService bestRedeemedProductsService;

  @Operation(
      operationId = "maximizeCoupon",
      summary = "Maximize the use a client can give to a discount coupon")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
  @ArraySchema(schema = @Schema(implementation = MaximizedTotalToSpendDto.class))
  @PostMapping({"coupon"})
  ResponseEntity<MaximizedTotalToSpendDto> maximizeCouponBenefit(
      @RequestBody @Valid FavouritesClientItemsWithCouponDto request) throws BusinessException {

    // Calling service.
    MaximizedTotalToSpend maximizedTotalToSpent =
        maximizeCouponUseService.maximize(request.itemsIds(), request.amount());

    // returning maximized coupon.
    return ResponseEntity.ok(
        new MaximizedTotalToSpendDto(
            maximizedTotalToSpent.getItemsIds(), maximizedTotalToSpent.getTotal()));
  }

  // --------------------------------------------------------------------------------------------------------- //

  @Operation(operationId = "topProducts", summary = "Obtains a list of the most redeemed products.")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
  @ArraySchema(schema = @Schema(implementation = BestRedeemedProductsDto.class))
  @GetMapping("most-redeemed-products")
  ResponseEntity<List<BestRedeemedProductsDto>> findTopRedeemedProducts() {

    // TODO Use mapstruct mb.
    return ResponseEntity.ok(
        bestRedeemedProductsService.findTopRedeemedProducts().stream() // Calling Service
            .map(
                b ->
                    new BestRedeemedProductsDto(
                        b.getProductName(), b.getQuantity())) // Mapping to DTO.
            .toList());
  }
}
