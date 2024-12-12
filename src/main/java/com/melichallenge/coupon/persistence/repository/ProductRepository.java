package com.melichallenge.coupon.persistence.repository;

import com.melichallenge.coupon.persistence.entities.ProductsSalesEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductsSalesEntity, Long> {


  @Query(
      "SELECT NEW com.melichallenge.coupon.persistence.entities.ProductsSalesEntity(p.product.productName, SUM(p.)) "
          + "FROM ProductsSalesEntity p "
          + "GROUP BY p.productName "
          + "ORDER BY SUM(p.quantity) DESC "
          + "LIMIT 5")
  List<ProductsSalesEntity> findTopFiveRedeemedProducts();
}
