package com.example.tpchuang.currencyconversion.currencyconversionservice;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyConversion {

  private Long id;
  private String from;
  private String to;
  private BigDecimal quantity;
  private BigDecimal conversionMultiple;
  private BigDecimal totalCalculatedAmount;
  private String environment;
}
