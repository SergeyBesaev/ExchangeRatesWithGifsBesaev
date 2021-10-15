package com.example.exchangerateswithgifsbesaev.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.exchangerateswithgifsbesaev.clientsFeign.IGiphyClient;
import com.example.exchangerateswithgifsbesaev.clientsFeign.IGiphyMediaClient;
import com.example.exchangerateswithgifsbesaev.dto.giphy.Datum;
import com.example.exchangerateswithgifsbesaev.dto.giphy.GifDataListDTO;
import com.example.exchangerateswithgifsbesaev.enums.Mood;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalMatchers.*;

@SpringBootTest
class GifServiceTest {

    @MockBean
    IGiphyMediaClient giphyMediaClient;

    @MockBean
    IGiphyClient giphyClient;

    @Autowired
    IGifService gifService;

    @Test
    void downloadRandomGifSuccess() throws IOException {
        Mood mood = Mood.BROKE;
        List<Datum> dataList = Arrays.asList(
                new Datum("id1"),
                new Datum("id2")
        );
        GifDataListDTO dto = new GifDataListDTO(dataList);
        byte[] gifContent = new byte[20];
        new Random().nextBytes(gifContent);

        when(giphyClient.getGifList(mood)).thenReturn(dto);
        when(giphyMediaClient.getGifById(or(eq("id1"), eq("id2")))).thenReturn(gifContent);

        assertEquals(gifContent, gifService.downloadRandomGif(mood));
    }

    @Test
    void failsWhenNoGifFound() {
        Mood mood = Mood.RICH;

        when(giphyClient.getGifList(mood)).thenReturn(new GifDataListDTO(Collections.emptyList()));

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> gifService.downloadRandomGif(mood)
        );

        assertEquals("Got empty gif list", exception.getMessage());
    }

}
