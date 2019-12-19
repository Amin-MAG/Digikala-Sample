package com.mag.digikala.Model.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Model.ProductAttributesRepository;
import com.mag.digikala.R;

import java.util.List;

public class FilterSelectionTermsRecyclerAdapter extends RecyclerView.Adapter<FilterSelectionTermsRecyclerAdapter.FilterSelectionOptionsRecyclerViewHolder> {

    private Activity activity;
    private ProductAttributesRepository.Attribute attribute;
    private List<ProductAttributesRepository.Term> terms;

    public FilterSelectionTermsRecyclerAdapter(ProductAttributesRepository.Attribute attribute) {
        this.attribute = attribute;
        this.terms = attribute.getTerms();
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

        ProductAttributesRepository.Term term;
        private CheckBox termCheckedBox;

        public FilterSelectionOptionsRecyclerViewHolder(@NonNull View itemView) {

            super(itemView);

            termCheckedBox = itemView.findViewById(R.id.layout_filter_selection_options_list_item__option);

            termCheckedBox.setOnClickListener(view -> {
                if (termCheckedBox.isChecked())
                    ProductAttributesRepository.getInstance().getAttributeById(attribute.getId()).addToSelected(term);
                else
                    ProductAttributesRepository.getInstance().getAttributeById(attribute.getId()).removeFromSelected(term);

                notifyDataSetChanged();
            });

        }

        private void bind(ProductAttributesRepository.Term term) {
            this.term = term;
            termCheckedBox.setText(term.getName());

            if (ProductAttributesRepository.getInstance().getAttributeById(attribute.getId()).getSelectedTerms().contains(term))
                termCheckedBox.setChecked(true);
            else
                termCheckedBox.setChecked(false);

        }

    }


}
