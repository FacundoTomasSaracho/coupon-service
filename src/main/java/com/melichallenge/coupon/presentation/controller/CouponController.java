package com.melichallenge.coupon.presentation.controller;

import com.melichallenge.coupon.business.service.BestSellingItemsService;
import com.melichallenge.coupon.business.service.MaximizeCouponUseService;
import com.melichallenge.coupon.exception.BusinessException;
import com.melichallenge.coupon.model.BestRedeemedProducts;
import com.melichallenge.coupon.model.MaximizedTotalToSpend;
import com.melichallenge.coupon.presentation.dto.BestRedeemedProductsDto;
import com.melichallenge.coupon.presentation.dto.FavouritesClientItemsWithCouponDto;
import com.melichallenge.coupon.presentation.dto.MaximizedTotalToSpendDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.ArrayList;
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
  private final BestSellingItemsService bestSellingService;

  @Operation(
      operationId = "maximizeCoupon",
      summary = "Maximize the use I can give to a discount coupon.")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK")})
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
  @GetMapping("most-redeemed-products")
  ResponseEntity<List<BestRedeemedProductsDto>> getTopSellingProducts() {

    List<BestRedeemedProductsDto> bestSellingDto = new ArrayList<>();

    // Calling service
    List<BestRedeemedProducts> bestSellingProducts = bestSellingService.getTopSellingProducts();

    // TODO Use mapstruct
    for (BestRedeemedProducts b : bestSellingProducts) {
      bestSellingDto.add(new BestRedeemedProductsDto(b.getProductName(), b.getQuantity()));
    }
    return ResponseEntity.ok(bestSellingDto);
  }
}
