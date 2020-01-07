package com.mag.digikala.View.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mag.digikala.View.Fragments.SliderImageItemFragment;

import java.util.ArrayList;
import java.util.List;

public class SliderViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<SliderImageItemFragment> imagesFragment = new ArrayList<>();

    public SliderViewPagerAdapter(FragmentManager fm) {
        super(fm);
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
