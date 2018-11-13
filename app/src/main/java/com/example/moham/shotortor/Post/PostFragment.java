package com.example.moham.shotortor.Post;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.moham.shotortor.HomePage.R;
import com.example.moham.shotortor.Models.Photo;
import com.example.moham.shotortor.Models.Rest;
import com.example.moham.shotortor.WebServiece.APIClient;
import com.example.moham.shotortor.WebServiece.APIInterface;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFragment extends Fragment {

    private static final String SEARCH_METHOD = "flickr.photos.search";
    private static final String API_KEY = "5911664908fa6777aed20a4868c68e8b";
    private static final String EXTRAS = "url_s";
    private static final String FORMAT = "json";
    private static final int NO_JSON_CALLBACK = 1;

    private RecyclerView mRecyclerView;
    private PhotoAdapter mPhotoAdapter;

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
        mRecyclerView = view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
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
                    updateUI(response.body().getPhotoes().getPhotoList());
                    Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Rest> call, @NonNull Throwable t) {

            }
        });
        return view;
    }

    private void updateUI(List<Photo> photoList) {

        if (mPhotoAdapter == null) {
            mPhotoAdapter = new PhotoAdapter(photoList);
            mRecyclerView.setAdapter(mPhotoAdapter);
        } else {
            mPhotoAdapter.setPhotoList(photoList);
            mPhotoAdapter.notifyDataSetChanged();
        }
    }

    private class PhotoAdapter extends RecyclerView.Adapter<PhotoHolder> {

        private List<Photo> photoList;

        public PhotoAdapter(List<Photo> photoList) {

            this.photoList = photoList;
            Log.d("TAG" , photoList.size()+"");
        }

        public void setPhotoList(List<Photo> photoList) {
            this.photoList = photoList;
        }

        @NonNull
        @Override
        public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.recycler_frame, parent, false);
            Log.d("TAG" , "1");
            return new PhotoHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
            Photo photo = photoList.get(position);
            Log.d("TAG" , "2");
            holder.bindPhoto(photo);
        }

        @Override
        public int getItemCount() {
            return photoList.size();
        }
    }

    private class PhotoHolder extends RecyclerView.ViewHolder {

        ImageButton mImageButton;

        public PhotoHolder(@NonNull View itemView) {
            super(itemView);

            mImageButton = itemView.findViewById(R.id.image_btn_recycler_frame);
        }

        public void bindPhoto(Photo photo) {
            Log.d("TAG" , "ok");
            Picasso.get().load(photo.getUrl()).into(mImageButton);
        }
    }
}
