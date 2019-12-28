package com.mag.digikala.Model.Adapter;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Controller.Activities.ProductDetailActivity;
import com.mag.digikala.Model.Product;
import com.mag.digikala.R;
import com.mag.digikala.Var.Constants;
import com.mag.digikala.databinding.LayoutProductSpecialBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductRecyclerViewHolder> {

    private LayoutProductSpecialBinding  binding;

    private List<Product> productItems;
    private Activity activity;

    public ProductRecyclerAdapter() {
        this.productItems = new ArrayList<>();
    }

    public ProductRecyclerAdapter(List<Product> products) {
        this.productItems = products;
    }


    @NonNull
    @Override
    public ProductRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activity = (Activity) parent.getContext();
        binding =  DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.layout_product_special, parent, false);
        return new ProductRecyclerViewHolder(binding.getRoot());
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

        private Product product;

        public ProductRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
        }


        public void bind(final Product product) {

            this.product = product;

            setImage(product);
            setPrices();
            binding.productLayoutTitle.setText(product.getName());
            binding.productLayoutCardview.setOnClickListener(view -> activity.startActivity(ProductDetailActivity.newIntent(activity, product.getId())));

        }

        private void setImage(Product product) {
            if (product.getImages().length != 0) {
                String imageUrl = product.getImages()[0].getSrc();
                Picasso.get().load(imageUrl).placeholder(R.drawable.place_holder).into(binding.productLayoutCover);
            }
        }

        private void setPrices() {

            String MONEY_STRING = Constants.SPACE_CHAR + activity.getResources().getString(R.string.tomans);
            String priceString;
            String priceInvalidString = Constants.EMPTY_CHAR;

            if (!product.isOnSale())
                priceString = product.getRegularPrice() + MONEY_STRING;
            else {
                priceString = product.getSalePrice() + MONEY_STRING;
                priceInvalidString = product.getRegularPrice() + MONEY_STRING;
            }

            TextView priceInvalid =  binding.productLayoutPriceWithoutSales;
            priceInvalid.setText(priceInvalidString);
            priceInvalid.setPaintFlags(priceInvalid.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            binding.productLayoutPrice.setText(priceString);

        }

    }


    public void setProductItems(List<Product> productItems) {
        this.productItems = productItems;
    }

}
