package com.melichallenge.coupon.persistence.repository;

import com.melichallenge.coupon.model.BestRedeemedProducts;
import java.util.List;

public interface ProductsSalesRepository {

  List<BestRedeemedProducts> findBestRedeemedProducts();

  void saveProductIdAndQuantity(List<String> itemIds);
}
