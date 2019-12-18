package com.mag.digikala.Controller.Fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mag.digikala.R;


public class SortSelectionDialogFragment extends DialogFragment {


    public static SortSelectionDialogFragment newInstance() {

        Bundle args = new Bundle();

        SortSelectionDialogFragment fragment = new SortSelectionDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SortSelectionDialogFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sort_selection_dialog, container, false);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_sort_selection_dialog, null, false);

        // Dialog Box

        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();

        return dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
