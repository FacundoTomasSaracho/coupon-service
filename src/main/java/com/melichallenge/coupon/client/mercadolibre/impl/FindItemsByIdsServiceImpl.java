package com.melichallenge.coupon.client.mercadolibre.impl;

import static com.melichallenge.coupon.exception.enums.ResponseCode.BAD_REQUEST;

import com.melichallenge.coupon.client.mercadolibre.FindItemsByIdsService;
import com.melichallenge.coupon.client.mercadolibre.model.ClientFavouriteProducts;
import com.melichallenge.coupon.exception.BusinessException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
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

  public List<ClientFavouriteProducts> findItems(List<String> ids) throws BusinessException {

    // TODO Improve | If an error occurs, it will be handled directly in the RestExceptionHandler
    // I should have the MeLi error structure.
    log.info("Calling MeLi API");
    try {
      return restTemplate
          .exchange(
              formEndpoint(ids),
              HttpMethod.GET,
              null,
              new ParameterizedTypeReference<List<ClientFavouriteProducts>>() {})
          .getBody();

    } catch (HttpClientErrorException | HttpServerErrorException exception) {
      log.error("Exception: ", exception);
      throw new BusinessException(BAD_REQUEST);
    }
  }

  private String formEndpoint(List<String> ids) {
    String idsSplitByCommas = String.join(",", ids);
    return this.meliEndpoint + idsSplitByCommas + this.attributes;
  }
}
