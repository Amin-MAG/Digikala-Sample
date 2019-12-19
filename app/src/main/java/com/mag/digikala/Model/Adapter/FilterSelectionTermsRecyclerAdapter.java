package com.mag.digikala.Model.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Model.ProductAttributesRepository;
import com.mag.digikala.R;

import java.util.List;

public class FilterSelectionTermsRecyclerAdapter extends RecyclerView.Adapter<FilterSelectionTermsRecyclerAdapter.FilterSelectionOptionsRecyclerViewHolder> {

    private Activity activity;
    private List<ProductAttributesRepository.Term> terms;

    public FilterSelectionTermsRecyclerAdapter(List<ProductAttributesRepository.Term> terms) {
        this.terms = terms;
    }

    @NonNull
    @Override
    public FilterSelectionOptionsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activity = (Activity) parent.getContext();
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_filter_selection_options_list_item, parent, false);
        return new FilterSelectionOptionsRecyclerViewHolder(view);
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

        private TextView optionText;

        public FilterSelectionOptionsRecyclerViewHolder(@NonNull View itemView) {

            super(itemView);

            optionText = itemView.findViewById(R.id.layout_filter_selection_options_list_item__option);

        }

        private void bind(ProductAttributesRepository.Term term) {
            optionText.setText(term.getName());
        }

    }


}
