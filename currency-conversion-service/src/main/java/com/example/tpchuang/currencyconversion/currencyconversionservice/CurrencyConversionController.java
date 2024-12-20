package com.example.tpchuang.currencyconversion.currencyconversionservice;

import com.example.tpchuang.currencyconversion.currencyexchangeservice.CurrencyExchange;
import java.math.BigDecimal;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class CurrencyConversionController {

  private final CurrencyExchangeProxy proxy;

  private final RestTemplate restTemplate;

  @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateCurrencyConversion(@PathVariable String from,
      @PathVariable String to, @PathVariable BigDecimal quantity) {

    HashMap<String, String> uriVariables = new HashMap<>();
    uriVariables.put("from", from);
    uriVariables.put("to", to);

    ResponseEntity<CurrencyExchange> responseEntity = restTemplate.getForEntity(
        "http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyExchange.class,
        uriVariables);

    CurrencyExchange currencyExchange = responseEntity.getBody();

    return new CurrencyConversion(currencyExchange.getId(), from, to, quantity,
        currencyExchange.getConversionMultiple(),
        quantity.multiply(currencyExchange.getConversionMultiple()),
        currencyExchange.getEnvironment() + " " + "rest template");

  }

  @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversion calculateCurrencyConversionFeign(@PathVariable String from,
      @PathVariable String to, @PathVariable BigDecimal quantity) {

    CurrencyExchange currencyExchange = proxy.retrieveExchangeValue(from, to);

    return new CurrencyConversion(currencyExchange.getId(), from, to, quantity,
        currencyExchange.getConversionMultiple(),
        quantity.multiply(currencyExchange.getConversionMultiple()),
        currencyExchange.getEnvironment() + " " + "feign");
  }

}
