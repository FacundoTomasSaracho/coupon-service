package com.melichallenge.coupon.business.service.impl;

import com.melichallenge.coupon.business.service.BestSellingItemsService;
import com.melichallenge.coupon.model.BestRedeemedProducts;
import com.melichallenge.coupon.persistence.repository.ProductsSalesRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class BestSellingItemsServiceImpl implements BestSellingItemsService {

  private final ProductsSalesRepository productsSalesRepository;

  @Override
  public List<BestRedeemedProducts> getTopSellingProducts() {
    return productsSalesRepository.getBestSellingProducts();
  }
}
