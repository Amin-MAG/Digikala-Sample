package com.mag.digikala.View.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.mag.digikala.R;
import com.mag.digikala.databinding.LayoutSliderImageItemBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainSliderAdapter extends SliderViewAdapter<MainSliderAdapter.MainSliderAdapterViewHolder> {

    private Activity activity;
    private List<String> imageSrc;

    public MainSliderAdapter(List<String> imageSrc) {
        this.imageSrc = imageSrc;
    }

    @Override
    public MainSliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        activity = (Activity) parent.getContext();
        LayoutSliderImageItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.layout_slider_image_item, null, false);
        return new MainSliderAdapterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MainSliderAdapterViewHolder viewHolder, int position) {
        viewHolder.bind(imageSrc.get(position));
    }

    @Override
    public int getCount() {
        return imageSrc.size();
    }

    public void setImages(List<String> images) {
        imageSrc = images;
    }

    class MainSliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {

        private LayoutSliderImageItemBinding binding;

        public MainSliderAdapterViewHolder(@NonNull LayoutSliderImageItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;

            binding.sliderImageItem.setOnClickListener(view -> {

            });

        }

        public void bind(String src) {
            Picasso.get().load(src).into(binding.sliderImageItem);
        }

    }

}
