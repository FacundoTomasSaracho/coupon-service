package com.melichallenge.coupon.client.mercadolibre;

import com.melichallenge.coupon.client.mercadolibre.model.Item;
import com.melichallenge.coupon.exception.BusinessException;

import java.util.List;

public interface FindItemsByIdsService {

    List<Item> findItems(List<String> ids) throws BusinessException;
}
