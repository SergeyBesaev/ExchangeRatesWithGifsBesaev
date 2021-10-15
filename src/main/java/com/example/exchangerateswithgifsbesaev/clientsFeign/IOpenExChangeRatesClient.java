package com.example.exchangerateswithgifsbesaev.clientsFeign;

import com.example.exchangerateswithgifsbesaev.dto.rates.OpenExchangeRatesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Rates", url = "${api.rates.url}")
public interface IOpenExChangeRatesClient {

    @GetMapping("latest.json?app_id=${api.rates.key}&base={code}")
    OpenExchangeRatesDTO getActualRates(
            @RequestParam(value="code") String code
    );

    @GetMapping("historical/{date}.json?app_id=${api.rates.key}&base={code}")
    OpenExchangeRatesDTO getRatesByDate(
            @RequestParam(value="date") String date,
            @RequestParam(value="code") String code
    );
}
