package com.mag.digikala.Controller.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mag.digikala.R;
import com.squareup.picasso.Picasso;


public class SliderImageItemFragment extends Fragment {

    public static final String ARG_URL_IMAGE_STRING = "args_url_image_string";
    private String urlString;

    private ImageView imageView;

    public static SliderImageItemFragment newInstance(String imageUrl) {
        SliderImageItemFragment fragment = new SliderImageItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL_IMAGE_STRING, imageUrl);
        fragment.setArguments(args);

        return fragment;
    }

    public SliderImageItemFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_slider_image_item, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = view.findViewById(R.id.slider_image_item);
        urlString = getArguments().getString(ARG_URL_IMAGE_STRING);

        Picasso.get().load(urlString).placeholder(getResources().getDrawable(R.drawable.place_holder)).into(imageView);

    }

}
