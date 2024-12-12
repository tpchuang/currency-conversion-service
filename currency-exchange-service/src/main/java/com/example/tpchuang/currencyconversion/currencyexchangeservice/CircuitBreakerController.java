package com.example.tpchuang.currencyconversion.currencyexchangeservice;

import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CircuitBreakerController {

  @GetMapping("/sample-api")
  @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
  public String sampleApi() {
    log.info("Sample api call received");
    return "sample-api";
  }

  public String hardcodedResponse(Exception e) {
    log.warn("exception occurred", e);
    return "fallback-response";
  }

}
