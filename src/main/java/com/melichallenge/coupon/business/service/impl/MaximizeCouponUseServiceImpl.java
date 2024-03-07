package com.melichallenge.coupon.business.service.impl;

import com.melichallenge.coupon.business.service.MaximizeCouponUseService;
import com.melichallenge.coupon.client.mercadolibre.FindItemsByIdsService;
import com.melichallenge.coupon.client.mercadolibre.model.ClientFavouriteItems;
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

    // No repeated ids
    Set<String> noRepeatedIds = new HashSet<>(ids);

    // Request to MeLi API
    List<ClientFavouriteItems> clientFavouriteItems =
        findItemsByIdsService.findItems(
            noRepeatedIds.stream()
                .filter(id -> id.startsWith(siteId)) // This could be managed using a header
                .toList() //  that indicates the geographic zone.
            );

    // Maximizing coupon
    MaximizedTotalToSpend maximizedTotalToSpend = maximizeCouponUsage(clientFavouriteItems, amount);

    // Saving in DB.
    productsSalesRepository.saveSellings(maximizedTotalToSpend.getItemsIds());

    // return
    return maximizedTotalToSpend;
  }

<<<<<<< HEAD
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
=======
  private MaximizedTotalToSpend maximizeCouponUsage(
      List<ClientFavouriteItems> clientFavouriteItems, Integer amount) {

    double totalSpentAmount = 0.0;
    List<String> filteredClientFavouriteItems = new ArrayList<>();

    // Order asc.
    sortItemsByPrice(clientFavouriteItems);

    for (ClientFavouriteItems items : clientFavouriteItems) {
      if (canAddItem(totalSpentAmount, items, amount)) { // if client can buy it.
        saveItem(filteredClientFavouriteItems, items);
        totalSpentAmount += items.getItemBody().getPrice();
      }
    }
    return new MaximizedTotalToSpend(
        new ArrayList<>(filteredClientFavouriteItems), totalSpentAmount);
  }

  private void sortItemsByPrice(List<ClientFavouriteItems> items) {
    items.sort(Comparator.comparingDouble(item -> item.getItemBody().getPrice()));
  }

  private boolean canAddItem(double totalSpentAmount, ClientFavouriteItems item, double maxAmount) {
    return totalSpentAmount + item.getItemBody().getPrice() <= maxAmount;
  }

  private void saveItem(List<String> filteredClientFavouriteItems, ClientFavouriteItems item) {
    filteredClientFavouriteItems.add(item.getItemBody().getItemId());
>>>>>>> 6ee41ee4d297022b8f2180bb7533f45c627dbbad
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
