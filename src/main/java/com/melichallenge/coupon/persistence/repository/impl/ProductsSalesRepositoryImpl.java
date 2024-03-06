package com.melichallenge.coupon.persistence.repository.impl;

import com.melichallenge.coupon.model.BestRedeemedProducts;
import com.melichallenge.coupon.persistence.dao.ProductSalesDao;
import com.melichallenge.coupon.persistence.entities.ProductsSalesEntity;
import com.melichallenge.coupon.persistence.repository.ProductsSalesRepository;
import java.util.ArrayList;
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
  public List<BestRedeemedProducts> getBestSellingProducts() {

    List<BestRedeemedProducts> bestSellingProducts = new ArrayList<>();
    List<ProductsSalesEntity> topSales = productSalesDao.findTop5SellingProducts();

    // TODO use mapstruct.
    for (ProductsSalesEntity p : topSales) {
      bestSellingProducts.add(new BestRedeemedProducts(p.getProductName(), p.getQuantity()));
    }

    return bestSellingProducts;
  }

  @Override
  public void saveSellings(List<String> itemsIds) {
    log.info("Saving sales in DB.");
    productSalesDao.saveAll(map(itemsIds));
  }

  private List<ProductsSalesEntity> map(List<String> itemsIds) {
    List<ProductsSalesEntity> productsSalesEntities = new ArrayList<>();
    int cont = 0;
    for (String s : itemsIds) {
      productsSalesEntities.add(new ProductsSalesEntity(s, 1L));
    }
    return productsSalesEntities;
  }
}
