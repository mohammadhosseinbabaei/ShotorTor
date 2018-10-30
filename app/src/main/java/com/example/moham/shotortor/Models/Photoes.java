package com.example.moham.shotortor.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photoes {

    @SerializedName("photo")
    private List<Photo> photoList;

    public List<Photo> getPhotoList() {
        return photoList;
    }
}
