package com.melichallenge.coupon.business.service;

import com.melichallenge.coupon.model.BestRedeemedProducts;
import java.util.List;

public interface ProductService {

  List<BestRedeemedProducts> findTopRedeemedProducts();
}
