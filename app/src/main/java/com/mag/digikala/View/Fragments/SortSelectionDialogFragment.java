package com.mag.digikala.View.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;

import com.mag.digikala.R;
import com.mag.digikala.databinding.FragmentSortSelectionDialogBinding;


public class SortSelectionDialogFragment extends DialogFragment {

    private FragmentSortSelectionDialogBinding binding;

    public static final String EXTRA_SORT_ID = "extra_sort_id";


    public enum SORT_MODE {
        SORT_BY_VIEW(0), SORT_BY_SELL(1), SORT_BY_PRICE_ASCENDING(2), SORT_BY_PRICE_DESCENDING(3), SORT_BY_NEWEST(4);

        private int code;

        SORT_MODE(int code) {
            this.code = code;
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


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_sort_selection_dialog, null, false);

        setEvents();

        // Dialog Box

        Dialog dialog = new AlertDialog.Builder(getActivity())
                .setView(binding.getRoot())
                .create();

        return dialog;
    }

    private void setEvents() {
        binding.sortSelectionDialogFragmentNewest.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_SORT_ID, SORT_MODE.SORT_BY_NEWEST.code);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
            dismiss();
        });

        binding.sortSelectionDialogFragmentMostView.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_SORT_ID, SORT_MODE.SORT_BY_VIEW.code);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
            dismiss();
        });

        binding.sortSelectionDialogFragmentBestSeller.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_SORT_ID, SORT_MODE.SORT_BY_SELL.code);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
            dismiss();
        });

        binding.sortSelectionDialogFragmentPriceAscending.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_SORT_ID, SORT_MODE.SORT_BY_PRICE_ASCENDING.code);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
            dismiss();
        });

        binding.sortSelectionDialogFragmentPriceDescending.setOnClickListener(view1 -> {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_SORT_ID, SORT_MODE.SORT_BY_PRICE_DESCENDING.code);
            getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
            dismiss();
        });
    }

}



