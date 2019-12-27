package com.mag.digikala.Model.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mag.digikala.Controller.Fragments.ProductDetailFragment;
import com.mag.digikala.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.lang.invoke.CallSite;
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
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_slider_image_item, null);
        return new MainSliderAdapterViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MainSliderAdapterViewHolder viewHolder, int position) {
        Picasso.get().load(imageSrc.get(position)).into(viewHolder.imageViewBackground);
    }

    @Override
    public int getCount() {
        return imageSrc.size();
    }

    public void setImages(List<String> images) {
        imageSrc = images;
    }

    class MainSliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {

        private ImageView imageViewBackground;

        public MainSliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.slider_image_item);

            imageViewBackground.setOnClickListener(view -> {
//                activity.startActivity(Prod);
            });
        }

    }

}
