package com.melichallenge.coupon.presentation.controller.impl;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.melichallenge.coupon.business.service.MaximizeCouponUseService;
import com.melichallenge.coupon.model.MaximizedTotalToSpend;
import com.melichallenge.coupon.presentation.controller.CouponApi;
import com.melichallenge.coupon.presentation.mapper.ItemsAndAmountMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CouponController implements CouponApi {

    private final MaximizeCouponUseService maximizeCouponUseService;

    @Override
    public ResponseEntity<ItemsAndAmount> maximizeCouponBenefit(@RequestBody @Valid ItemsAndAmount request) {

        MaximizedTotalToSpend maximizedTotalToSpent = maximizeCouponUseService.maximize(request.itemsIds(), request.totalAmount());

        return ResponseEntity.ok(ItemsAndAmountMapper.INSTANCE.modelToDto(maximizedTotalToSpent));
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public record ItemsAndAmount(@NotEmpty List<String> itemsIds, @NotEmpty @Positive Double totalAmount) {
    }

}
