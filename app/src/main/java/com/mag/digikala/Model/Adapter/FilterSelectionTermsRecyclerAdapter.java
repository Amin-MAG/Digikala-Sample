package com.mag.digikala.Model.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Repository.FilterRepository;
import com.mag.digikala.R;
import com.mag.digikala.databinding.LayoutFilterSelectionOptionsListItemBinding;
import com.mag.digikala.viewmodel.FilterSelectionViewModel;

import java.util.ArrayList;
import java.util.List;

public class FilterSelectionTermsRecyclerAdapter extends RecyclerView.Adapter<FilterSelectionTermsRecyclerAdapter.FilterSelectionOptionsRecyclerViewHolder> {

    private FilterSelectionViewModel viewModel;

    private Activity activity;
    private FilterRepository.Attribute attribute;
    private List<FilterRepository.Term> terms;
    private RecyclerView recyclerView;

    public FilterSelectionTermsRecyclerAdapter(FilterSelectionViewModel viewModel, RecyclerView recyclerView) {
        this.viewModel = viewModel;
        this.attribute = viewModel.getSelectedAttribute().getValue();
        this.terms = attribute.getTerms();
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public FilterSelectionOptionsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activity = (Activity) parent.getContext();
        LayoutFilterSelectionOptionsListItemBinding binding = DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.layout_filter_selection_options_list_item, parent, false);
        setObserve();
        return new FilterSelectionOptionsRecyclerViewHolder(binding);
    }

    private void setObserve() {
        viewModel.getSelectedAttribute().observe((LifecycleOwner) activity, attribute -> {
            this.attribute = attribute;
            this.terms = attribute.getTerms();
            recyclerView.post(this::notifyDataSetChanged);
        });
    }

    @Override
    public void onBindViewHolder(@NonNull FilterSelectionOptionsRecyclerViewHolder holder, int position) {
        holder.bind(terms.get(position));
    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    public class FilterSelectionOptionsRecyclerViewHolder extends RecyclerView.ViewHolder {

        private FilterRepository.Term term;
        private CheckBox termCheckedBox;

        public FilterSelectionOptionsRecyclerViewHolder(@NonNull LayoutFilterSelectionOptionsListItemBinding binding) {

            super(binding.getRoot());

            termCheckedBox = binding.layoutFilterSelectionOptionsListItemOption;

//            termCheckedBox.setOnClickListener(view -> {
//                viewModel.onTermClicked(term, termCheckedBox.isChecked());
//                notifyDataSetChanged();
//            });

        }

        private void bind(FilterRepository.Term term) {
            this.term = term;

            termCheckedBox.setText(term.getName());

//            if (FilterRepository.getInstance().getAttributeById(attribute.getId()).getSelectedTerm().contains(term))
//                termCheckedBox.setChecked(true);
//            else
//                termCheckedBox.setChecked(false);

        }

    }


}
