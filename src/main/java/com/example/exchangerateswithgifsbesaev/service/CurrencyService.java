package com.example.exchangerateswithgifsbesaev.service;

import com.example.exchangerateswithgifsbesaev.clientsFeign.IOpenExChangeRatesClient;
import com.example.exchangerateswithgifsbesaev.dto.rates.OpenExchangeRatesDTO;
import com.example.exchangerateswithgifsbesaev.enums.Currency;
import com.example.exchangerateswithgifsbesaev.enums.Mood;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class CurrencyService implements ICurrencyService {

    @Value("${base.currency}")
    private Currency baseCurrency;

    private final IOpenExChangeRatesClient ratesClient;

    public CurrencyService(IOpenExChangeRatesClient ratesClient) {
        this.ratesClient = ratesClient;
    }

    // метод возвращает вчерашнее число
    private Date getDateMinusDays(Integer minusDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -minusDays);
        return calendar.getTime();
    }

    public Mood getMood(String currencyCode) {
        OpenExchangeRatesDTO actualRatesDTO = ratesClient
                .getActualRates(currencyCode);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String yesterday = dateFormat.format(getDateMinusDays(1));

        OpenExchangeRatesDTO yesterdayRatesDTO = ratesClient
                .getRatesByDate(yesterday, currencyCode);

        double actualRate = actualRatesDTO
                .getRate(baseCurrency);

        double yesterdayRate = yesterdayRatesDTO
                .getRate(baseCurrency);

        if (actualRate < yesterdayRate) {
            return Mood.BROKE;
        } else {
            return Mood.RICH;
        }
    }

}
