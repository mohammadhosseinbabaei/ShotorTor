package com.example.moham.shotortor.WebServiece;

import com.example.moham.shotortor.Models.Rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("rest")
    Call<Rest> getRest(@Query("method") String method,
                       @Query("api_key") String apiKey,
                       @Query("extras") String extras,
                       @Query("format") String format,
                       @Query("nojsoncallback") int noJsonCallback,
                       @Query("text") String text);
}
