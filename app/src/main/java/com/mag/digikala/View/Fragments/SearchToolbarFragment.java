package com.mag.digikala.View.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.mag.digikala.Controller.Activities.FilterActivity;
import com.mag.digikala.R;
import com.mag.digikala.Var.Constants;
import com.mag.digikala.databinding.FragmentSearchToolbarBinding;

public class SearchToolbarFragment extends Fragment {

    private FragmentSearchToolbarBinding binding;

    private MaterialButton closeSearchBtn;
    private EditText searchEditTxt;

    public static SearchToolbarFragment newInstance() {

        Bundle args = new Bundle();

        SearchToolbarFragment fragment = new SearchToolbarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SearchToolbarFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_search_toolbar, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findComponents();
        
        binding.fragementSearchToolbarBackButton.setIconTint(getResources().getColorStateList(R.color.digikala_gray));
        binding.fragementSearchToolbarVoiceButton.setIconTint(getResources().getColorStateList(R.color.digikala_gray));
        closeSearchBtn.setIconTint(getResources().getColorStateList(R.color.digikala_gray));

        searchEditTxt.requestFocus();

        setEvents();

    }

    private void findComponents() {
        closeSearchBtn = binding.fragementSearchToolbarCloseButton;
        searchEditTxt = binding.fragementSearchToolbarSearchBar;
    }

    private void setEvents() {

        openKeyboard();

        closeSearchBtn.setOnClickListener(closeSearchBtnView -> {
            searchEditTxt.setText(Constants.EMPTY_CHAR);
            closeSearchBtn.setVisibility(View.GONE);
        });

        binding.fragementSearchToolbarBackButton.setOnClickListener(backBtnView -> getActivity().finish());

        searchEditTxt.setOnKeyListener((view, i, keyEvent) -> {
            if (searchEditTxt.getText().length() > 0) closeSearchBtn.setVisibility(View.VISIBLE);
            else closeSearchBtn.setVisibility(View.GONE);
            return false;
        });

        searchEditTxt.setOnEditorActionListener((textView, i, keyEvent) -> {
            startActivity(FilterActivity.newIntent(getContext(), searchEditTxt.getText().toString(), null));
            InputMethodManager imm1 = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm1.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
            return false;
        });

    }

    private void openKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }


    @Override
    public void onPause() {
        super.onPause();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }

}
