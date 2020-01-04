package com.mag.digikala.Model.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Controller.Activities.FilterActivity;
import com.mag.digikala.Repository.FilterRepository;
import com.mag.digikala.R;
import com.mag.digikala.databinding.LayoutFilterSelectionAttributesListItemBinding;

import java.util.ArrayList;
import java.util.List;

public class FilterSelectionAttributesRecyclerAdapter extends RecyclerView.Adapter<FilterSelectionAttributesRecyclerAdapter.FilterSelectionAttributesRecyclerViewHolder> {

    private Activity activity;
    private List<FilterRepository.Attribute> attributes;
    private FilterRepository.Attribute selected;
    private AdapterCallback callback;

    public FilterSelectionAttributesRecyclerAdapter(List<FilterRepository.Attribute> attributes, AdapterCallback callback) {
        this.attributes = attributes;
        this.callback = callback;
    }

    public FilterSelectionAttributesRecyclerAdapter() {
        this.attributes = new ArrayList<>();
    }

    @NonNull
    @Override
    public FilterSelectionAttributesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activity = (Activity) parent.getContext();
        LayoutFilterSelectionAttributesListItemBinding binding = DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.layout_filter_selection_attributes_list_item, parent, false);
        ((FilterActivity) activity).getFilterSelectionViewModel().getAttributes().observe((LifecycleOwner) activity, attributes -> {
            this.attributes = attributes;
        });
        ((FilterActivity) activity).getFilterSelectionViewModel().getSelectedAttribute().observe((LifecycleOwner) activity, selected -> {
            this.selected = selected;
        });
        return new FilterSelectionAttributesRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterSelectionAttributesRecyclerViewHolder holder, int position) {
        holder.bind(attributes.get(position));
    }

    @Override
    public int getItemCount() {
        return attributes.size();
    }

    public class FilterSelectionAttributesRecyclerViewHolder extends RecyclerView.ViewHolder {

        private FilterRepository.Attribute productAttribute;
        private TextView attrText;

        public FilterSelectionAttributesRecyclerViewHolder(@NonNull LayoutFilterSelectionAttributesListItemBinding binding) {

            super(binding.getRoot());

            attrText = binding.layoutFilterSelectionAttributesListItemTitle;

            attrText.setOnClickListener(view -> {
                selected = productAttribute;
                callback.update();
                notifyDataSetChanged();
            });

        }

        private void selectedAttributeUi() {

            if (selected != null && selected.getId().equals(productAttribute.getId())) {
                attrText.setTextColor(activity.getResources().getColor(R.color.cardview_dark_background));
                attrText.setBackgroundColor(activity.getResources().getColor(R.color.digikala_dark_white));
            } else {
                attrText.setTextColor(activity.getResources().getColor(R.color.digikala_raw_white));
                attrText.setBackgroundColor(activity.getResources().getColor(R.color.cardview_dark_background));
            }

        }

        private void bind(FilterRepository.Attribute productAttribute) {
            this.productAttribute = productAttribute;

            selectedAttributeUi();

            attrText.setText(productAttribute.getName());
        }

    }

    public FilterRepository.Attribute getSelected() {
        return selected;
    }

    public interface AdapterCallback {
        void update();
    }

}
