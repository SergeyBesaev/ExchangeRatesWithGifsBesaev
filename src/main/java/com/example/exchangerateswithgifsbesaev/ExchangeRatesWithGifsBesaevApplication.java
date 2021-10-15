package com.example.exchangerateswithgifsbesaev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ExchangeRatesWithGifsBesaevApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExchangeRatesWithGifsBesaevApplication.class, args);
    }

}
