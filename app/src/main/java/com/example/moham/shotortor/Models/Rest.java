package com.example.moham.shotortor.Models;

import com.google.gson.annotations.SerializedName;

public class Rest {

    @SerializedName("photos")
    private Photoes photoes;

    public Photoes getPhotoes() {
        return photoes;
    }
}
