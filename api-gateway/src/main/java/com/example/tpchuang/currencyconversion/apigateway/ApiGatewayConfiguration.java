package com.example.tpchuang.currencyconversion.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

  private static final String LB_CURRENCY_EXCHANGE = "lb://currency-conversion";

  @Bean
  public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
    return builder.routes()
        .route(p -> p.path("/currency-exchange/**").uri("lb://currency-exchange"))
        .route(p -> p.path("/currency-conversion/**").uri(LB_CURRENCY_EXCHANGE))
        .route(p -> p.path("/currency-conversion-feign/**").uri(LB_CURRENCY_EXCHANGE))
        .route(p -> p.path("/currency-conversion-new/**").filters(
            f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)",
                "/currency-conversion-feign/${segment}")).uri(LB_CURRENCY_EXCHANGE))
        .build();
  }

}
