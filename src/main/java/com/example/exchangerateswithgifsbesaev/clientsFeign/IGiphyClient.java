package com.example.exchangerateswithgifsbesaev.clientsFeign;

import com.example.exchangerateswithgifsbesaev.dto.giphy.GifDataListDTO;
import com.example.exchangerateswithgifsbesaev.enums.Mood;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "GiphyMedia", url = "${api.gif.url}")
public interface IGiphyClient {

    @GetMapping("search?api_key=${api.gif.key}&q={query}")
    GifDataListDTO getGifList(
            @RequestParam(value="query") Mood mood
    );

}
