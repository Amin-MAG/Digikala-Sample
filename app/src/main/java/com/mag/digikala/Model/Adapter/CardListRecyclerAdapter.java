package com.mag.digikala.Model.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mag.digikala.Controller.Activities.ProductDetailActivity;
import com.mag.digikala.Model.Product;
import com.mag.digikala.R;
import com.mag.digikala.Repository.CardRepository;
import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;

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
        private CardView cardView;
        private TextView productTitle, productDescription, productCount, productPrice;
        private ImageView productImage;
        private MaterialButton increaseBtn, decreaseBtn;
        private MaterialButton clearBtn;

        public CardListViewHolder(@NonNull View itemView) {
            super(itemView);

            findItems(itemView);

            setEvents();

        }

        private void findItems(@NonNull View itemView) {
            productTitle = itemView.findViewById(R.id.layout_card_list_item__title);
            productDescription = itemView.findViewById(R.id.layout_card_list_item__description);
            productImage = itemView.findViewById(R.id.layout_card_list_item__image);
            productCount = itemView.findViewById(R.id.layout_card_list_item__count);
            productPrice = itemView.findViewById(R.id.layout_card_list_item__price);
            increaseBtn = itemView.findViewById(R.id.layout_card_list_item__increase);
            decreaseBtn = itemView.findViewById(R.id.layout_card_list_item__decrease);
            clearBtn = itemView.findViewById(R.id.layout_card_list_item__delete);
            cardView = itemView.findViewById(R.id.layout_card_list_item__card_view);
        }

        private void setEvents() {
            increaseBtn.setOnClickListener(view -> {
                CardRepository.getInstance().increaseProductInCard(product);
            });

            decreaseBtn.setOnClickListener(view -> {
                CardRepository.getInstance().decreaseProductInCard(product);
            });

            clearBtn.setOnClickListener(view -> {
                CardRepository.getInstance().clearProductFromCard(product);
            });

            cardView.setOnClickListener(view -> {
                activity.startActivity(ProductDetailActivity.newIntent(activity, product.getId()));
            });
        }

        public void bind(Product product) {

            this.product = product;

            productTitle.setText(product.getName());
            productDescription.setText(Jsoup.parse(product.getShortDescription()).body().text());
            productCount.setText(String.valueOf(product.getCardCount()));
            productPrice.setText(product.getSalePrice().equals("") ? product.getRegularPrice() : product.getSalePrice());
            Picasso.get().load(product.getImages()[0].getSrc()).placeholder(activity.getResources().getDrawable(R.drawable.place_holder)).into(productImage);

        }

    }

}
