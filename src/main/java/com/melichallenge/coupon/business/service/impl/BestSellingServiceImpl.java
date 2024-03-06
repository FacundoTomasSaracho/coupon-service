package com.melichallenge.coupon.business.service.impl;

import com.melichallenge.coupon.business.service.BestSellingService;
import com.melichallenge.coupon.model.BestRedeemedProducts;
import com.melichallenge.coupon.persistence.repository.ProductsSalesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class BestSellingServiceImpl implements BestSellingService {

  private final ProductsSalesRepository productsSalesRepository;

  @Override
  public List<BestRedeemedProducts> getTopSellingProducts() {
    log.info("Getting best selling products.");
    return productsSalesRepository.getBestSellingProducts();
  }
}
