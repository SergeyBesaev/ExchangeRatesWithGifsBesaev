package com.example.exchangerateswithgifsbesaev.dto.rates;

import com.example.exchangerateswithgifsbesaev.enums.Currency;

import java.util.Map;

public class OpenExchangeRatesDTO {

    private Map<String, Double> rates;

    public OpenExchangeRatesDTO() {
    }

    public OpenExchangeRatesDTO(Map<String, Double> rates) {
        this.rates = rates;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public Double getRate(Currency currency) {
        return rates.get(currency.toString());
    }

}
