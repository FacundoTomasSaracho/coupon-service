package com.melichallenge.coupon.client.mercadolibre;

import com.melichallenge.coupon.client.mercadolibre.model.ClientFavouriteProducts;
import com.melichallenge.coupon.exception.BusinessException;

import java.util.List;

public interface FindItemsByIdsService {

    List<ClientFavouriteProducts> findItems(List<String> ids) throws BusinessException;
}
