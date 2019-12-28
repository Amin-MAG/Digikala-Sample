package com.mag.digikala.Controller.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.mag.digikala.R;


public class SortSelectionDialogFragment extends DialogFragment {

    public static final String EXTRA_SORT_ID = "extra_sort_id";
    private RadioButton newest, mostView, bestSeller, priceAscending, priceDescending;


    public static enum SORT_MODE {
        SORT_BY_VIEW(0), SORT_BY_SELL(1), SORT_BY_PRICE_ASCENDING(2), SORT_BY_PRICE_DESCENDING(3), SORT_BY_NEWEST(4);

        private int code;

        SORT_MODE(int code) {
            this.code = code;
        }

        public int getNumVal() {
            return code;
        }

    }

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

        findComponents(view);

        setEvents();

        // Dialog Box

        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();

        return dialog;
    }

    private void setEvents() {
        newest.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_SORT_ID, SORT_MODE.SORT_BY_NEWEST.code);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
            dismiss();
        });

        mostView.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_SORT_ID, SORT_MODE.SORT_BY_VIEW.code);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
            dismiss();
        });

        bestSeller.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_SORT_ID, SORT_MODE.SORT_BY_SELL.code);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
            dismiss();
        });

        priceAscending.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_SORT_ID, SORT_MODE.SORT_BY_PRICE_ASCENDING.code);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
            dismiss();
        });

        priceDescending.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_SORT_ID, SORT_MODE.SORT_BY_PRICE_DESCENDING.code);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
            dismiss();
        });
    }

    private void findComponents(View view) {
        newest = view.findViewById(R.id.sort_selection_dialog_fragment__newest);
        mostView = view.findViewById(R.id.sort_selection_dialog_fragment__most_view);
        bestSeller = view.findViewById(R.id.sort_selection_dialog_fragment__best_seller);
        priceAscending = view.findViewById(R.id.sort_selection_dialog_fragment__price_ascending);
        priceDescending = view.findViewById(R.id.sort_selection_dialog_fragment__price_descending);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}



