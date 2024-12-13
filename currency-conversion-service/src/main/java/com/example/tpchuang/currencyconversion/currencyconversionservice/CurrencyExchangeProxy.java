package com.example.tpchuang.currencyconversion.currencyconversionservice;

import com.example.tpchuang.currencyconversion.currencyexchangeservice.CurrencyExchange;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

  @GetMapping("/currency-exchange/from/{from}/to/{to}")
  CurrencyExchange retrieveExchangeValue(@PathVariable String from,
      @PathVariable String to);

}
