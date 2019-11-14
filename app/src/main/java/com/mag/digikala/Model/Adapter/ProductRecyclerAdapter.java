package com.mag.digikala.Model.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Model.Merchandise;
import com.mag.digikala.R;

import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductRecyclerViewHolder> {

    private List<Merchandise> productItems;
    private Activity activity;

    public ProductRecyclerAdapter(List<Merchandise> merchandises) {
        this.productItems = merchandises;
    }


    @NonNull
    @Override
    public ProductRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activity = (Activity) parent.getContext();
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_product, parent, false);
        return new ProductRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRecyclerViewHolder holder, int position) {
        holder.bind(productItems.get(position));
    }

    @Override
    public int getItemCount() {
        return productItems.size();
    }

    public class ProductRecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView title;

        public ProductRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.product_layout__title);

        }

        public void bind(Merchandise merchandise) {

            title.setText(merchandise.getTitle());

        }

    }


    public void setProductItems(List<Merchandise> productItems) {
        this.productItems = productItems;
    }
    
}
