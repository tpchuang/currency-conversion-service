package com.example.tpchuang.currencyconversion.currencyexchangeservice;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CurrencyExchangeController {

  private final CurrencyExchangeRepository repository;
  private final Environment environment;

  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  public CurrencyExchange retrieveExchangeValue(@PathVariable String from,
      @PathVariable String to) {

    log.info("retrieveExchangeValue called with {} to {}", from, to);

    CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);

    if (currencyExchange == null) {
      throw new RuntimeException
          ("Unable to Find data for " + from + " to " + to);
    }

    String port = environment.getProperty("local.server.port");
    currencyExchange.setEnvironment(port);

    return currencyExchange;
  }

}
