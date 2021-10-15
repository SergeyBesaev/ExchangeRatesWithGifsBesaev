package com.example.exchangerateswithgifsbesaev.service;

import com.example.exchangerateswithgifsbesaev.clientsFeign.IGiphyClient;
import com.example.exchangerateswithgifsbesaev.clientsFeign.IGiphyMediaClient;
import com.example.exchangerateswithgifsbesaev.dto.giphy.GifDataListDTO;
import com.example.exchangerateswithgifsbesaev.enums.Mood;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GifService implements IGifService {

    private final IGiphyClient giphyClient;
    private final IGiphyMediaClient giphyMediaClient;

    public GifService(IGiphyClient giphyClient, IGiphyMediaClient giphyMediaClient) {
        this.giphyClient = giphyClient;
        this.giphyMediaClient = giphyMediaClient;
    }

    private String getRandomGifId(GifDataListDTO gifDTO) {
        int gifCount = gifDTO.getData().size();
        if (gifCount == 0) {
            throw new RuntimeException("Got empty gif list");
        }
        Random rnd = new Random();
        int num = rnd.nextInt(gifCount);
        return gifDTO.getData().get(num).getId();
    }

    public byte[] downloadRandomGif(Mood mood) {
        GifDataListDTO gifDataList = giphyClient.getGifList(mood);
        String gifId = getRandomGifId(gifDataList);
        return giphyMediaClient.getGifById(gifId);
    }

}
