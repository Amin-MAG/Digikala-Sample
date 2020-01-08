package com.mag.digikala.view.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.mag.digikala.R;
import com.mag.digikala.databinding.FragmentCommonToolbarBinding;


public class CommonToolbarFragment extends Fragment {

    private FragmentCommonToolbarBinding binding;

    public static final String ARG_COMMON_TOOLBAR_TITLE = "arg_common_toolbar_title";

    private CommonToolbarCallback callback;


    public static CommonToolbarFragment newInstance(String title, CommonToolbarCallback callback) {

        Bundle args = new Bundle();
        args.putString(ARG_COMMON_TOOLBAR_TITLE, title);

        CommonToolbarFragment fragment = new CommonToolbarFragment(callback);
        fragment.setArguments(args);
        return fragment;
    }

    public CommonToolbarFragment(CommonToolbarCallback callback) {
        this.callback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_common_toolbar, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.commonToolbarFragmentBackBtn.setOnClickListener(backBtnView -> callback.backBtnClicked());
        binding.commonToolbarFragmentTitle.setText(getArguments().getString(ARG_COMMON_TOOLBAR_TITLE));

    }

    // Should be changed later
    public interface CommonToolbarCallback {
        void backBtnClicked();
    }

}
