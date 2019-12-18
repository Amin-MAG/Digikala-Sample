package com.mag.digikala.Model.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Model.Product;
import com.mag.digikala.R;

import java.util.List;

public class CardListRecyclerAdapter extends RecyclerView.Adapter<CardListRecyclerAdapter.CardListViewHolder> {

    private Activity activity;
    private List<Product> products;

    public CardListRecyclerAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public CardListRecyclerAdapter.CardListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activity = (Activity) parent.getContext();
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_card_list_item, parent, false);
        return new CardListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardListRecyclerAdapter.CardListViewHolder holder, int position) {
        holder.bind(products.get(position));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(List<Product> productList) {
        this.products = productList;
    }


    public class CardListViewHolder extends RecyclerView.ViewHolder {

        private Product product;
        private TextView productTitle;


        public CardListViewHolder(@NonNull View itemView) {
            super(itemView);

            productTitle = itemView.findViewById(R.id.layout_card_list_item__title);

        }

        public void bind(Product product) {

            this.product = product;

            productTitle.setText(product.getName());

        }

    }

}
