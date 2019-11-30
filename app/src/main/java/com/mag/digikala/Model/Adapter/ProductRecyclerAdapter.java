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
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Controller.Activities.ProductDetailActivity;
import com.mag.digikala.Model.Product;
import com.mag.digikala.R;
import com.mag.digikala.Var.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductRecyclerViewHolder> {

    private List<Product> productItems;
    private Activity activity;

    public ProductRecyclerAdapter(List<Product> products) {
        this.productItems = products;
    }


    @NonNull
    @Override
    public ProductRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activity = (Activity) parent.getContext();
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_product_special, parent, false);
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
        private CardView cardView;

        public ProductRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            cover = itemView.findViewById(R.id.product_layout__cover);
            price = itemView.findViewById(R.id.product_layout_price);
            priceInvalid = itemView.findViewById(R.id.product_layout_price_without_sales);
            title = itemView.findViewById(R.id.product_layout__title);
            cardView = itemView.findViewById(R.id.product_layout__cardview);

        }

        public void bind(final Product product) {

            String imageUrl = product.getImages()[0].getSrc();
            Picasso.get().load(imageUrl).placeholder(R.drawable.place_holder).into(cover);

            setPrices(product.getRegular_price(), product.getSale_price());

            title.setText(product.getName());


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.startActivity(ProductDetailActivity.newIntent(activity, product.getId()));
                }
            });

        }

        private void setPrices(String regular_price, String sale_price) {

            String MONEY_STRING = Constants.SPACE_CHAR + activity.getResources().getString(R.string.tomans);
            String priceString;
            String priceInvalidString = Constants.EMPTY_CHAR;

            if (sale_price.equals(Constants.EMPTY_CHAR))
                priceString = regular_price + MONEY_STRING;
            else {
                priceString = sale_price + MONEY_STRING;
                priceInvalidString = regular_price + MONEY_STRING;
            }

            priceInvalid.setText(priceInvalidString);
            priceInvalid.setPaintFlags(priceInvalid.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            price.setText(priceString);

        }

    }


    public void setProductItems(List<Product> productItems) {
        this.productItems = productItems;
    }

}
