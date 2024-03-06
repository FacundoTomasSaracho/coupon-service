package com.melichallenge.coupon.client.mercadolibre.impl;

import com.melichallenge.coupon.client.mercadolibre.FindItemsByIdsService;
import com.melichallenge.coupon.client.mercadolibre.model.Item;
import com.melichallenge.coupon.exception.BusinessException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Log4j2
@Component
public class FindItemsByIdsServiceImpl implements FindItemsByIdsService {
  private final RestTemplate restTemplate;

  @Value("${meliEndpoint}")
  private String meliEndpoint;

  @Value("${attributes}")
  private String attributes;

  public List<Item> findItems(List<String> ids) throws BusinessException {

    //// TODO Modify. If an error occurs, it will be handled directly in the RestExceptionHandler.
    try {
      return restTemplate
          .exchange(
              formEndpoint(ids),
              HttpMethod.GET,
              null,
              new ParameterizedTypeReference<List<Item>>() {})
          .getBody();

    } catch (Exception e) {
      log.error("Exception: {}", e.getMessage());
      throw new BusinessException(400, "Bad Request");
    }
  }

  private String formEndpoint(List<String> ids) {
    String idsSplitByCommas = String.join(",", ids);
    return this.meliEndpoint + idsSplitByCommas + this.attributes;
  }
}
