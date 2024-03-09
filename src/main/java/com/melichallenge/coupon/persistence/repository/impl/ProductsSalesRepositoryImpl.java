package com.melichallenge.coupon.persistence.repository.impl;

import com.melichallenge.coupon.model.BestRedeemedProducts;
import com.melichallenge.coupon.persistence.dao.ProductSalesDao;
import com.melichallenge.coupon.persistence.entities.ProductsSalesEntity;
import com.melichallenge.coupon.persistence.repository.ProductsSalesRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductsSalesRepositoryImpl implements ProductsSalesRepository {

  private final ProductSalesDao productSalesDao;

  @Override
  public List<BestRedeemedProducts> findBestRedeemedProducts() {

    // Finding top 5 in DB TODO Handle PersistenceException
    log.info("Finding best redeemed products in DB.");
    return productSalesDao.findTopFiveRedeemedProducts().stream()
        .map(entity -> new BestRedeemedProducts(entity.getProductName(), entity.getQuantity()))
        .toList();
  }

  @Override
  public void saveProductIdAndQuantity(List<String> itemsIds) {
    log.info("Saving products in DB.");
    productSalesDao.saveAll(modelToProductSalesEntity(itemsIds));
  }

  private List<ProductsSalesEntity> modelToProductSalesEntity(List<String> itemsIds) {
    return itemsIds.stream().map(s -> new ProductsSalesEntity(s, 1L)).toList();
  }
}
