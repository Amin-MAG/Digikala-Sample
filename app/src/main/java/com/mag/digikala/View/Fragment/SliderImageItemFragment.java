package com.mag.digikala.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.mag.digikala.R;
import com.mag.digikala.databinding.LayoutSliderImageItemBinding;
import com.squareup.picasso.Picasso;


public class SliderImageItemFragment extends Fragment {

    private LayoutSliderImageItemBinding binding;

    public static final String ARG_URL_IMAGE_STRING = "args_url_image_string";

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
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.layout_slider_image_item, container, false);
        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Picasso.get().load(getArguments().getString(ARG_URL_IMAGE_STRING)).placeholder(getResources().getDrawable(R.drawable.place_holder)).into(binding.sliderImageItem);
    }

}
