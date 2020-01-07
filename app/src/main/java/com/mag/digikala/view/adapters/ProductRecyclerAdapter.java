package com.mag.digikala.view.adapters;

import android.app.Activity;
import android.graphics.Paint;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.view.activities.ProductDetailActivity;
import com.mag.digikala.data.model.Product;
import com.mag.digikala.R;
import com.mag.digikala.databinding.LayoutProductSpecialBinding;
import com.mag.digikala.viewmodel.ProductViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ProductRecyclerViewHolder> {

    private Activity activity;
    private List<Product> productItems;

    public ProductRecyclerAdapter() {
        this.productItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public ProductRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activity = (Activity) parent.getContext();
        LayoutProductSpecialBinding binding = DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.layout_product_special, parent, false);
        return new ProductRecyclerViewHolder(binding);
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

        private ProductViewModel viewModel;
        private LayoutProductSpecialBinding binding;

        public ProductRecyclerViewHolder(@NonNull LayoutProductSpecialBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
            this.viewModel = ViewModelProviders.of((FragmentActivity) activity).get(ProductViewModel.class);

            binding.setProductViewModel(viewModel);
        }


        public void bind(final Product product) {

            viewModel.getProduct().setValue(product);
            binding.setProductViewModel(viewModel);
            binding.executePendingBindings();


            // Image
            Picasso.get().load(viewModel.getFirstImageSrc()).placeholder(R.drawable.place_holder).into(binding.productLayoutCover);

            // Invalid Price Style
            binding.productLayoutPriceWithoutSales.setPaintFlags(new TextView(activity).getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            // Card Event
            binding.productLayoutCardview.setOnClickListener(view -> activity.startActivity(ProductDetailActivity.newIntent(activity, product.getId())));

        }

    }

    public void setProductItems(List<Product> productItems) {
        this.productItems = productItems;
        notifyDataSetChanged();
    }

}
