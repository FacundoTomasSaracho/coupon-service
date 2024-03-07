package com.melichallenge.coupon.business.service.impl;

import com.melichallenge.coupon.business.service.MaximizeCouponUseService;
import com.melichallenge.coupon.client.mercadolibre.FindItemsByIdsService;
import com.melichallenge.coupon.client.mercadolibre.model.Item;
import com.melichallenge.coupon.exception.BusinessException;
import com.melichallenge.coupon.model.MaximizedTotalToSpend;
import com.melichallenge.coupon.persistence.repository.ProductsSalesRepository;
import java.util.*;
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

  private MaximizedTotalToSpend maximizePurchase(List<Item> items, double amount) {
    Set<String> purchasedItemIds = new HashSet<>();
    double totalSpentAmount = 0.0;

    sortItemsByPrice(items);

    for (Item item : items) {
      if (canPurchaseItem(totalSpentAmount, item, amount)) {
        purchaseItem(purchasedItemIds, item);
        totalSpentAmount += item.getItemBody().getPrice();
      }
    }
    return new MaximizedTotalToSpend(new ArrayList<>(purchasedItemIds), totalSpentAmount);
  }

  private void sortItemsByPrice(List<Item> items) {
    items.sort(Comparator.comparingDouble(item -> item.getItemBody().getPrice()));
  }

  private boolean canPurchaseItem(double totalSpentAmount, Item item, double maxAmount) {
    return totalSpentAmount + item.getItemBody().getPrice() <= maxAmount;
  }

  private void purchaseItem(Set<String> purchasedItemIds, Item item) {
    purchasedItemIds.add(item.getItemBody().getItemId());
  }

  //  private boolean canAffordIt(double spentAmount, List<Item> items) {
  //    Set<String> filteredItems = new HashSet<>();
  //    for (Item item : items) {
  //      if (spentAmount + item.getItemBody().getPrice() <= amount) { // if client can buy it or
  // not.
  //        filteredItems.add(item.getItemBody().getItemId());
  //        spentAmount += item.getItemBody().getPrice();
  //      }
  //    }
  //  }
}
