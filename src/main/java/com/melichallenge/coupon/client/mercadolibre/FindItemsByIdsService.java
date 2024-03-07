package com.melichallenge.coupon.client.mercadolibre;

import com.melichallenge.coupon.client.mercadolibre.model.ClientFavouriteItems;
import com.melichallenge.coupon.exception.BusinessException;

import java.util.List;

public interface FindItemsByIdsService {

    List<ClientFavouriteItems> findItems(List<String> ids) throws BusinessException;
}
