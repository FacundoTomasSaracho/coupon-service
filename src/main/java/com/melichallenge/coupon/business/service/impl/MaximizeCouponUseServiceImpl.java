package com.melichallenge.coupon.business.service.impl;

import com.melichallenge.coupon.business.service.MaximizeCouponUseService;
import com.melichallenge.coupon.client.mercadolibre.FindItemsByIdsService;
import com.melichallenge.coupon.client.mercadolibre.model.Item;
import com.melichallenge.coupon.exception.BusinessException;
import com.melichallenge.coupon.model.MaximizedTotalToSpend;
import com.melichallenge.coupon.persistence.repository.ProductsSalesRepository;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MaximizeCouponUseServiceImpl implements MaximizeCouponUseService {

  private final FindItemsByIdsService findItemsByIdsService;
  private final ProductsSalesRepository productsSalesRepository;

  @Value("${siteId}")
  private String siteId;

  public MaximizedTotalToSpend maximize(List<String> ids, Integer amount) throws BusinessException {
    List<Item> items =
        findItemsByIdsService.findItems(
            ids.stream()
                .filter(id -> id.startsWith(siteId)) // This could be managed using a header
                .toList() //  that indicates the geographic zone.
            );

    MaximizedTotalToSpend maximizedTotalToSpend = maximizePurchase(items, amount);
    productsSalesRepository.saveSellings(maximizedTotalToSpend.getItemsIds());
    return maximizedTotalToSpend;
  }

  private MaximizedTotalToSpend maximizePurchase(List<Item> items, Integer amount) {
    double spentAmount = 0.0;

    List<String> filteredItems = new ArrayList<>();

    // cheapest to most expensive.
    items.sort(Comparator.comparingDouble(item -> item.getItemBody().getPrice()));

    for (Item item : items) {
      if (spentAmount + item.getItemBody().getPrice() <= amount) { // if client can buy it or not.
        filteredItems.add(item.getItemBody().getItemId());
        spentAmount += item.getItemBody().getPrice();
      } else {
        break; // if client cant buy anything else breaks.
      }
    }
    return new MaximizedTotalToSpend(filteredItems, spentAmount);
  }
}
