package com.mag.digikala.Model.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Model.Product;
import com.mag.digikala.R;

import java.util.List;

public class FilterListAdapter extends RecyclerView.Adapter<FilterListAdapter.FilterListViewHolder> {


    private Activity activity;
    private List<Product> products;

    public FilterListAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public FilterListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activity = (Activity) parent.getContext();
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_filter_list_item, parent, false);
        return new FilterListAdapter.FilterListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterListViewHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class FilterListViewHolder extends RecyclerView.ViewHolder {

        public FilterListViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Product product){

        }

    }

}
