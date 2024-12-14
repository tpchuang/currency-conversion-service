package com.example.tpchuang.currencyconversion.currencyexchangeservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CircuitBreakerController {

  @GetMapping("/sample-api")
  public String sampleApi() {
    log.info("Sample api call received");
    return "sample-api";
  }

  public String hardcodedResponse(Exception e) {
    log.warn("exception occurred", e);
    return "fallback-response";
  }

}
