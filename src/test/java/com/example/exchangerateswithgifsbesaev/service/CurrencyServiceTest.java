package com.example.exchangerateswithgifsbesaev.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.exchangerateswithgifsbesaev.clientsFeign.IOpenExChangeRatesClient;
import com.example.exchangerateswithgifsbesaev.dto.rates.OpenExchangeRatesDTO;
import com.example.exchangerateswithgifsbesaev.enums.Mood;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class CurrencyServiceTest {

    @MockBean
    IOpenExChangeRatesClient ratesClient;

    @Autowired
    ICurrencyService currencyService;

    @Test
    void getRichMoodTest() {
        String currency = "USD";

        OpenExchangeRatesDTO actualRates = new OpenExchangeRatesDTO(Map.of("AUD", 1.0, "RUB", 2.0));
        OpenExchangeRatesDTO yesterdayRates = new OpenExchangeRatesDTO(Map.of("AUD", 1.1, "RUB", 1.9));

        when(ratesClient.getActualRates(currency)).thenReturn(actualRates);
        when(ratesClient.getRatesByDate(any(String.class), eq(currency))).thenReturn(yesterdayRates);

        assertEquals(Mood.RICH, currencyService.getMood(currency));
    }

    @Test
    void getBrokeMoodTest() {
        String currency = "USD";

        OpenExchangeRatesDTO actualRates = new OpenExchangeRatesDTO(Map.of("AUD", 1.1, "RUB", 1.9));
        OpenExchangeRatesDTO yesterdayRates = new OpenExchangeRatesDTO(Map.of("AUD", 1.0, "RUB", 2.0));

        when(ratesClient.getActualRates(currency)).thenReturn(actualRates);
        when(ratesClient.getRatesByDate(any(String.class), eq(currency))).thenReturn(yesterdayRates);

        assertEquals(Mood.BROKE, currencyService.getMood(currency));
    }

}
