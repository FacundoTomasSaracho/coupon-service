package com.melichallenge.coupon.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateConfiguration {

  public RestTemplateConfiguration() {}

  // Singleton because @Bean
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
