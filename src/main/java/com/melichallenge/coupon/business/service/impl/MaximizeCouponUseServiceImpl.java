package com.melichallenge.coupon.business.service.impl;

import com.melichallenge.coupon.business.service.MaximizeCouponUseService;
import com.melichallenge.coupon.client.mercadolibre.FindItemsByIdsService;
import com.melichallenge.coupon.client.mercadolibre.model.ClientFavouriteProducts;
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
    List<ClientFavouriteProducts> clientFavouriteItems =
        findItemsByIdsService.findItems(
            noRepeatedIds.stream()
                .filter(id -> id.startsWith(siteId)) // This could be managed using a header
                .toList() //  that indicates the geographic zone.
            );

    // Maximizing coupon
    MaximizedTotalToSpend maximizedTotalToSpend = maximizeCouponUsage(clientFavouriteItems, amount);

    // Saving in DB.
    productsSalesRepository.saveProductIdAndQuantity(maximizedTotalToSpend.getItemsIds());

    // return
    return maximizedTotalToSpend;
  }

  private MaximizedTotalToSpend maximizeCouponUsage(List<ClientFavouriteProducts> clientFavouriteItems, Integer amount) {

    double totalSpentAmount = 0.0;

    List<String> filteredClientFavouriteItems = new ArrayList<>();

    // Order asc.
    sortItemsByPrice(clientFavouriteItems);

    for (ClientFavouriteProducts items : clientFavouriteItems) {
      if (canAddItem(totalSpentAmount, items, amount)) { // if client can buy it.
        addItem(filteredClientFavouriteItems, items);
        totalSpentAmount += items.getItemBody().getPrice();
      }
    }
    return new MaximizedTotalToSpend(
        new ArrayList<>(filteredClientFavouriteItems), totalSpentAmount);
  }

  private boolean canAddItem(
      double totalSpentAmount, ClientFavouriteProducts item, double maxAmount) {
    return totalSpentAmount + item.getItemBody().getPrice() <= maxAmount;
  }

  private void addItem(List<String> filteredClientFavouriteItems, ClientFavouriteProducts item) {
    filteredClientFavouriteItems.add(item.getItemBody().getItemId());
  }

  private void sortItemsByPrice(List<ClientFavouriteProducts> items) {
    items.sort(Comparator.comparingDouble(item -> item.getItemBody().getPrice()));
  }
}
