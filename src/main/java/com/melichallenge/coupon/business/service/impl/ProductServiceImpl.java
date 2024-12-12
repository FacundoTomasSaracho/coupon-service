package com.melichallenge.coupon.business.service.impl;

import com.melichallenge.coupon.business.service.ProductService;
import com.melichallenge.coupon.model.BestRedeemedProducts;
import com.melichallenge.coupon.persistence.repository.service.ProductRepositoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

  private final ProductRepositoryService productRepositoryService;

  @Override
  public List<BestRedeemedProducts> findTopRedeemedProducts() {
    return productRepositoryService.findTopFiveRedeemedProducts();
  }
}
