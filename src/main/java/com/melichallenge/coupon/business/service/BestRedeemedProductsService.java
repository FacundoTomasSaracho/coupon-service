package com.melichallenge.coupon.business.service;

import com.melichallenge.coupon.model.BestRedeemedProducts;
import java.util.List;

public interface BestRedeemedProductsService {

  List<BestRedeemedProducts> findTopRedeemedProducts();
}
