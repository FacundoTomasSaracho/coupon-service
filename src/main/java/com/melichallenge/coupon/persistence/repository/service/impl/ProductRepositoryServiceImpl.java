package com.melichallenge.coupon.persistence.repository.service.impl;

import com.melichallenge.coupon.model.BestRedeemedProducts;
import com.melichallenge.coupon.persistence.entities.ProductEntity;
import com.melichallenge.coupon.persistence.entities.ProductsSalesEntity;
import com.melichallenge.coupon.persistence.repository.ProductRepository;
import com.melichallenge.coupon.persistence.repository.service.ProductRepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryServiceImpl implements ProductRepositoryService {

    private final ProductRepository productRepository;

    @Override
    public List<BestRedeemedProducts> findTopFiveRedeemedProducts() {

        log.info("Finding best redeemed products in DB.");
        return productRepository.findTopFiveRedeemedProducts().stream()
                .map(entity -> new BestRedeemedProducts(entity.getProduct().getProductName()))
                .toList();
    }

    @Override
    public List<String> saveProduct(List<String> itemsIds) {
        log.info("Saving products in DB.");
        productRepository.saveAll(itemsIds.stream()
                .map(itemId -> new ProductsSalesEntity(new ProductEntity(Long.parseLong(itemId)))).toList());
        return itemsIds;
    }
}
