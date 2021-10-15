package com.example.exchangerateswithgifsbesaev.service;

import com.example.exchangerateswithgifsbesaev.enums.Mood;

import java.io.IOException;

public interface IGifService {

    byte[] downloadRandomGif(Mood mood) throws IOException;

}
