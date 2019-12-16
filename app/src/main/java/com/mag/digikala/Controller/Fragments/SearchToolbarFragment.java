package com.mag.digikala.Controller.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.mag.digikala.R;

import org.w3c.dom.Text;

public class SearchToolbarFragment extends Fragment {

    private MaterialButton backBtn,voiceRecognitionBtn,closeSearchBtn;
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

        voiceRecognitionBtn = view.findViewById(R.id.materialButton2);
        backBtn = view.findViewById(R.id.materialButton);
        closeSearchBtn= view.findViewById(R.id.materialButton3);

        backBtn.setIconTint(getResources().getColorStateList(R.color.digikala_gray));
        voiceRecognitionBtn.setIconTint(getResources().getColorStateList(R.color.digikala_gray));
        closeSearchBtn.setIconTint(getResources().getColorStateList(R.color.digikala_gray));


    }


}
