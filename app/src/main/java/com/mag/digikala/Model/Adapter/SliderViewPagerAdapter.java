package com.mag.digikala.Model.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mag.digikala.Controller.Fragments.SliderImageItemFragment;

import java.util.ArrayList;
import java.util.List;

public class SliderViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<SliderImageItemFragment> imagesFragment;

    public SliderViewPagerAdapter(FragmentManager fm, List<String> imagesUrl) {
        super(fm);
        imagesFragment = new ArrayList<>();
        for (String url : imagesUrl) imagesFragment.add(SliderImageItemFragment.newInstance(url));
    }

    @Override
    public Fragment getItem(int position) {
        return imagesFragment.get(position);
    }

    @Override
    public int getCount() {
        return imagesFragment.size();
    }


}
