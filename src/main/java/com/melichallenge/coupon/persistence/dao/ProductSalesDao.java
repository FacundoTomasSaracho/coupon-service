package com.melichallenge.coupon.persistence.dao;

import com.melichallenge.coupon.persistence.entities.ProductsSalesEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSalesDao extends JpaRepository<ProductsSalesEntity, Long> {

  // TODO Should be configurable, using pageable maybe.
  @Query(
      "SELECT NEW com.melichallenge.coupon.persistence.entities.ProductsSalesEntity(p.productName, SUM(p.quantity)) "
          + "FROM ProductsSalesEntity p "
          + "GROUP BY p.productName "
          + "ORDER BY SUM(p.quantity) DESC "
          + "LIMIT 5")
  List<ProductsSalesEntity> findTopFiveRedeemedProducts();
}
