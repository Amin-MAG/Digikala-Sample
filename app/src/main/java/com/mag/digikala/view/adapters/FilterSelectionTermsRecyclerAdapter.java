package com.mag.digikala.view.adapters;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.data.repository.FilterRepository;
import com.mag.digikala.R;
import com.mag.digikala.databinding.LayoutFilterSelectionOptionsListItemBinding;
import com.mag.digikala.viewmodel.FilterSelectionViewModel;

import java.util.List;

public class FilterSelectionTermsRecyclerAdapter extends RecyclerView.Adapter<FilterSelectionTermsRecyclerAdapter.FilterSelectionOptionsRecyclerViewHolder> {

    private FilterSelectionViewModel viewModel;

    private Activity activity;
    private FilterRepository.Attribute attribute;
    private List<FilterRepository.Term> terms;

    public FilterSelectionTermsRecyclerAdapter(FilterSelectionViewModel viewModel) {
        this.viewModel = viewModel;
        this.attribute = viewModel.getSelectedAttribute().getValue();
        this.terms = attribute.getTerms();
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

            termCheckedBox.setOnClickListener(view -> {
                viewModel.onTermClicked(term, termCheckedBox.isChecked());
            });

        }

        private void bind(FilterRepository.Term term) {
            this.term = term;

            termCheckedBox.setText(term.getName());

            if (viewModel.getSelectedAttribute().getValue().getSelectedTerms().contains(term))
                termCheckedBox.setChecked(true);
            else
                termCheckedBox.setChecked(false);

        }

    }


}
