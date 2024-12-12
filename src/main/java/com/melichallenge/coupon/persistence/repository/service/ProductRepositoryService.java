package com.melichallenge.coupon.persistence.repository.service;

import com.melichallenge.coupon.model.BestRedeemedProducts;
import java.util.List;

public interface ProductRepositoryService {

  List<BestRedeemedProducts> findTopFiveRedeemedProducts();

  List<String> saveProduct(List<String> itemIds);
}
