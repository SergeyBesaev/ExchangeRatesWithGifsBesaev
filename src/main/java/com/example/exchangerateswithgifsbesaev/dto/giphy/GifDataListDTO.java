package com.example.exchangerateswithgifsbesaev.dto.giphy;

import java.util.List;

public class GifDataListDTO {

    private List<Datum> data;

    public GifDataListDTO() {
    }

    public GifDataListDTO(List<Datum> data) {
        this.data = data;
    }

    public List<Datum> getData() {
        return data;
    }

}
