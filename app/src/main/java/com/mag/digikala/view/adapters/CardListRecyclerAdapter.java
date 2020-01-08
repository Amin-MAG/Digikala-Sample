package com.mag.digikala.view.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.view.activities.ProductDetailActivity;
import com.mag.digikala.data.model.Product;
import com.mag.digikala.R;
import com.mag.digikala.data.repository.CardRepository;
import com.mag.digikala.databinding.LayoutCardListItemBinding;
import com.mag.digikala.viewmodel.ProductViewModel;
import com.squareup.picasso.Picasso;

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
        LayoutCardListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.layout_card_list_item, parent, false);
        return new CardListViewHolder(binding);
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

        private LayoutCardListItemBinding binding;
        private ProductViewModel viewModel;

        public CardListViewHolder(@NonNull LayoutCardListItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
            this.viewModel = ViewModelProviders.of((FragmentActivity) activity).get(ProductViewModel.class);

            binding.setProductViewModel(viewModel);

        }

        public void bind(Product product) {

            viewModel.getProduct().setValue(product);
            product.getCardCount().observe((LifecycleOwner) activity, integer -> {
                viewModel.getProduct().setValue(product);
                binding.setProductViewModel(viewModel);
            });
            binding.setProductViewModel(viewModel);
            binding.executePendingBindings();

            Picasso.get().load(viewModel.getFirstImageSrc()).placeholder(activity.getResources().getDrawable(R.drawable.place_holder)).into(binding.layoutCardListItemImage);

            binding.layoutCardListItemIncrease.setOnClickListener(view -> {
                CardRepository.getInstance().increaseProductInCard(product);
            });

            binding.layoutCardListItemDecrease.setOnClickListener(view -> {
                CardRepository.getInstance().decreaseProductInCard(product);
            });

            binding.layoutCardListItemDelete.setOnClickListener(view -> {
                CardRepository.getInstance().clearProductFromCard(viewModel.getProduct().getValue());
            });

            binding.layoutCardListItemCardView.setOnClickListener(view -> {
                activity.startActivity(ProductDetailActivity.newIntent(activity, product.getId()));
            });

        }

    }

}
