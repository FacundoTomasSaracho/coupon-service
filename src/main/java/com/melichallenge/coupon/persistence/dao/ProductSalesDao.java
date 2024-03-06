package com.melichallenge.coupon.persistence.dao;

import com.melichallenge.coupon.persistence.entities.ProductsSalesEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSalesDao extends JpaRepository<ProductsSalesEntity, Long> {

  //    @Query("SELECT p.productName, SUM(p.quantity) AS quantity " +
  //            "FROM ProductsSalesEntity p " +
  //            "GROUP BY p.productName " +
  //            "ORDER BY quantity DESC " +
  //            "LIMIT 5")

  //  List<ProductsSalesEntity> findTop5ByOrderByQuantityDesc();

  // TODO Should be configurable, using pageable maybe.
  @Query(
      "SELECT NEW com.melichallenge.coupon.persistence.entities.ProductsSalesEntity(p.productName, SUM(p.quantity)) "
          + "FROM ProductsSalesEntity p "
          + "GROUP BY p.productName "
          + "ORDER BY SUM(p.quantity) DESC "
          + "LIMIT 5")
  List<ProductsSalesEntity> findTop5SellingProducts();
}
