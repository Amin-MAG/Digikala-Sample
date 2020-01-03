package com.mag.digikala.Model.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Repository.FilterRepository;
import com.mag.digikala.R;

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

    @NonNull
    @Override
    public FilterSelectionAttributesRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activity = (Activity) parent.getContext();
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_filter_selection_attributes_list_item, parent, false);
        return new FilterSelectionAttributesRecyclerViewHolder(view);
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

        public FilterSelectionAttributesRecyclerViewHolder(@NonNull View itemView) {

            super(itemView);

            attrText = itemView.findViewById(R.id.layout_filter_selection_attributes_list_item__title);


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
