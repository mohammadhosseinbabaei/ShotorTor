package com.example.moham.shotortor.Post;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.moham.shotortor.HomePage.R;
import com.example.moham.shotortor.Models.Rest;
import com.example.moham.shotortor.WebServiece.APIClient;
import com.example.moham.shotortor.WebServiece.APIInterface;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostFragment extends Fragment {

    private static final String SEARCH_METHOD = "flickr.photos.search";
    private static final String API_KEY = "5911664908fa6777aed20a4868c68e8b";
    private static final String EXTRAS = "url_s";
    private static final String FORMAT = "json";
    private static final int NO_JSON_CALLBACK = 1;

    static PostFragment newInstance() {

        Bundle args = new Bundle();

        PostFragment fragment = new PostFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        Toast.makeText(getActivity(), "this is test", Toast.LENGTH_SHORT).show();

        APIClient.getClient().create(APIInterface.class)
                .getRest(
                        SEARCH_METHOD,
                        API_KEY,
                        EXTRAS,
                        FORMAT,
                        NO_JSON_CALLBACK,
                        "camel"
                ).enqueue(new Callback<Rest>() {

            @Override
            public void onResponse(@NonNull Call<Rest> call, @NonNull Response<Rest> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), Objects.requireNonNull(response.body()).getPhotoes().getPhotoList().size() + "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Rest> call, @NonNull Throwable t) {

            }
        });
        return view;
    }

}
