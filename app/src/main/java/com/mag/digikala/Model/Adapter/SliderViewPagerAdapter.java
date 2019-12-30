package com.mag.digikala.Model.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mag.digikala.View.Fragment.SliderImageItemFragment;

import java.util.ArrayList;
import java.util.List;

public class SliderViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<SliderImageItemFragment> imagesFragment;

    public SliderViewPagerAdapter(FragmentManager fm) {
        super(fm);
        imagesFragment = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return imagesFragment.get(position);
    }

    @Override
    public int getCount() {
        return imagesFragment.size();
    }

    public void setImagesFragment(List<String> imagesUrl) {
        imagesFragment = new ArrayList<>();
        for (String url : imagesUrl) imagesFragment.add(SliderImageItemFragment.newInstance(url));
        notifyDataSetChanged();
    }

}
