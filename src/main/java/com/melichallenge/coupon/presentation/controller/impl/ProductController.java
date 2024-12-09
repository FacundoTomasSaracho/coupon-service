package com.melichallenge.coupon.presentation.controller.impl;

import com.melichallenge.coupon.business.service.BestRedeemedProductsService;
import com.melichallenge.coupon.presentation.controller.ProductApi;
import com.melichallenge.coupon.presentation.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController implements ProductApi {

    private final BestRedeemedProductsService bestRedeemedProductsService;

    @Override
    @GetMapping("most-redeemed-products")
    public ResponseEntity<List<MostRedeemProductsDto>> findTopRedeemedProducts() {
        return ResponseEntity.ok(ProductMapper.INSTANCE.modelToDto(bestRedeemedProductsService.findTopRedeemedProducts()));
    }

    public record MostRedeemProductsDto(String productName, Long quantity) {
    }
}
