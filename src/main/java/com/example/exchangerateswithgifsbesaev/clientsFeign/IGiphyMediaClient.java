package com.example.exchangerateswithgifsbesaev.clientsFeign;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "Giphy", url = "${api.gif.media.url}")
public interface IGiphyMediaClient {


    @GetMapping("{gifId}/giphy.gif")
    byte[] getGifById(
            @RequestParam(value="gifId") String gifId
    );

}
