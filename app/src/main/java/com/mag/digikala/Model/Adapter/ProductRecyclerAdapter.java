package com.mag.digikala.Model.Adapter;

import android.app.Activity;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Model.Merchandise;
import com.mag.digikala.R;
import com.squareup.picasso.Picasso;

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

        private int id;
        private TextView title;
        private TextView price;
        private TextView priceInvalid;
        private ImageView cover;

        public ProductRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            cover = itemView.findViewById(R.id.product_layout__cover);
            price = itemView.findViewById(R.id.product_layout_price);
            priceInvalid = itemView.findViewById(R.id.product_layout_price_without_sales);
            title = itemView.findViewById(R.id.product_layout__title);

        }

        public void bind(Merchandise merchandise) {

            String imageUrl = merchandise.getImages()[0].getSrc();
            Picasso.get().load(imageUrl).placeholder(R.drawable.place_holder).into(cover);
            Log.d("price_string", "setPrices: " + merchandise.getRegular_price() + " Sales " +   merchandise.getSale_price());
            setPrices(merchandise.getRegular_price(), merchandise.getSale_price());
            title.setText(merchandise.getName());

        }

        private void setPrices(String regular_price, String sale_price) {

            String MONEY_STRING = " " + activity.getResources().getString(R.string.tomans);
            String priceString;
            String priceInvalidString = "";

            if (sale_price.equals("")) {
                priceString = regular_price + MONEY_STRING;
            } else {
                priceString = sale_price + MONEY_STRING;
                priceInvalidString = regular_price + MONEY_STRING;
                Log.d("price_string", "setPrices: " + priceInvalidString + " | ");
            }

            priceInvalid.setText(priceInvalidString);
            priceInvalid.setPaintFlags(priceInvalid.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            price.setText(priceString);

        }

    }


    public void setProductItems(List<Merchandise> productItems) {
        this.productItems = productItems;
    }

}
