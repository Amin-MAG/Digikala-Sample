package com.mag.digikala.Model.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Repository.FilterRepository;
import com.mag.digikala.R;
import com.mag.digikala.databinding.LayoutFilterSelectionOptionsListItemBinding;

import java.util.List;

public class FilterSelectionTermsRecyclerAdapter extends RecyclerView.Adapter<FilterSelectionTermsRecyclerAdapter.FilterSelectionOptionsRecyclerViewHolder> {

    private Activity activity;
    private FilterRepository.Attribute attribute;
    private List<FilterRepository.Term> terms;

    public FilterSelectionTermsRecyclerAdapter(FilterRepository.Attribute attribute) {
        this.attribute = attribute;
        this.terms = attribute.getTerms();
    }

    @NonNull
    @Override
    public FilterSelectionOptionsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activity = (Activity) parent.getContext();
        LayoutFilterSelectionOptionsListItemBinding binding = DataBindingUtil.inflate(activity.getLayoutInflater(),R.layout.layout_filter_selection_options_list_item, parent, false);
        return new FilterSelectionOptionsRecyclerViewHolder(binding);
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

        FilterRepository.Term term;
        private CheckBox termCheckedBox;

        public FilterSelectionOptionsRecyclerViewHolder(@NonNull LayoutFilterSelectionOptionsListItemBinding binding) {

            super(binding.getRoot());

            termCheckedBox = binding.layoutFilterSelectionOptionsListItemOption;

            termCheckedBox.setOnClickListener(view -> {
                if (termCheckedBox.isChecked())
                    FilterRepository.getInstance().getAttributeById(attribute.getId()).addToSelected(term);
                else
                    FilterRepository.getInstance().getAttributeById(attribute.getId()).removeFromSelected(term);

                notifyDataSetChanged();
            });

        }

        private void bind(FilterRepository.Term term) {
            this.term = term;
            termCheckedBox.setText(term.getName());

            if (FilterRepository.getInstance().getAttributeById(attribute.getId()).getSelectedTerm().contains(term))
                termCheckedBox.setChecked(true);
            else
                termCheckedBox.setChecked(false);

        }

    }


}
