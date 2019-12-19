package com.mag.digikala.Controller.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.mag.digikala.Controller.Activities.FilterActivity;
import com.mag.digikala.R;
import com.mag.digikala.Var.Constants;

public class SearchToolbarFragment extends Fragment {

    private MaterialButton backBtn, voiceRecognitionBtn, closeSearchBtn;
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
        return inflater.inflate(R.layout.fragment_search_toolbar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findComponents(view);

        backBtn.setIconTint(getResources().getColorStateList(R.color.digikala_gray));
        voiceRecognitionBtn.setIconTint(getResources().getColorStateList(R.color.digikala_gray));
        closeSearchBtn.setIconTint(getResources().getColorStateList(R.color.digikala_gray));

        setEvents();

    }

    private void findComponents(@NonNull View view) {
        voiceRecognitionBtn = view.findViewById(R.id.fragement_search_toolbar__voice_button);
        backBtn = view.findViewById(R.id.fragement_search_toolbar__back_button);
        closeSearchBtn = view.findViewById(R.id.fragement_search_toolbar__close_button);
        searchEditTxt = view.findViewById(R.id.fragement_search_toolbar__search_bar);
    }

    private void setEvents() {
        searchEditTxt.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        closeSearchBtn.setOnClickListener(closeSearchBtnView -> {
            searchEditTxt.setText(Constants.EMPTY_CHAR);
            closeSearchBtn.setVisibility(View.GONE);
        });

        backBtn.setOnClickListener(backBtnView -> getActivity().finish());

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


    @Override
    public void onPause() {
        super.onPause();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }

}
