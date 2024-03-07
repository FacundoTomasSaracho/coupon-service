package com.melichallenge.coupon.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

  private Info info() {
    return new Info()
        .title("Coupons Service")
        .description(
            "Allows to calculate best way to exchange coupons and verify most redeemed products.")
        .version("1.0.0")
        .contact(contact());
  }

  private Contact contact() {
    return new Contact().name("Mercado Libre").url("https://www.mercadolibre.com.ar");
  }

  @Bean
  public OpenAPI springShopOpenAPI() {
    return new OpenAPI().info(info());
  }
}
