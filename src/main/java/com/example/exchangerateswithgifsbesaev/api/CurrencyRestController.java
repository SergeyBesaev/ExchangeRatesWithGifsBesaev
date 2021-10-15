package com.example.exchangerateswithgifsbesaev.api;


import com.example.exchangerateswithgifsbesaev.enums.Mood;
import com.example.exchangerateswithgifsbesaev.service.CurrencyService;
import com.example.exchangerateswithgifsbesaev.service.GifService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CurrencyRestController {

    private final CurrencyService currencyService;
    private final GifService gifService;

    public CurrencyRestController(CurrencyService currencyService, GifService gifService) {
        this.currencyService = currencyService;
        this.gifService = gifService;
    }

    @ResponseBody
    @GetMapping("/surprise-me")
    public ResponseEntity<byte[]> getCurrencyRate(@RequestParam(name = "currencyCode") String currencyCode) {
        Mood mood = currencyService.getMood(currencyCode);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_GIF)
                .body(gifService.downloadRandomGif(mood));
    }

}
