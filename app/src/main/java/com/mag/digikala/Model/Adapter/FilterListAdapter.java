package com.mag.digikala.Model.Adapter;

import android.app.Activity;
import android.graphics.Paint;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Controller.Activities.ProductDetailActivity;
import com.mag.digikala.Model.Product;
import com.mag.digikala.R;
import com.mag.digikala.databinding.LayoutFilterListItemBinding;
import com.mag.digikala.viewmodel.ProductViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FilterListAdapter extends RecyclerView.Adapter<FilterListAdapter.FilterListViewHolder> {


    private Activity activity;
    private List<Product> products;

    public FilterListAdapter(List<Product> products) {
        this.products = products;
    }

    public FilterListAdapter() {
        this.products = new ArrayList<>();
    }

    @NonNull
    @Override
    public FilterListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activity = (Activity) parent.getContext();
        LayoutFilterListItemBinding binding = DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.layout_filter_list_item, parent, false);
        return new FilterListAdapter.FilterListViewHolder(binding);
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

        private LayoutFilterListItemBinding binding;
        private ProductViewModel viewModel;

        public FilterListViewHolder(@NonNull LayoutFilterListItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
            this.viewModel = new ProductViewModel();

            binding.setProductViewModel(viewModel);

        }

        public void bind(Product product) {

            binding.getProductViewModel().setProduct(product);

//            binding.filterListItemLayoutProductDescription.setText(shortDescriptionString.length() > 80 ? shortDescriptionString.substring(0, 80) + "..." : shortDescriptionString);

            // Image
            Picasso.get().load(viewModel.getFirstImageSrc()).placeholder(activity.getResources().getDrawable(R.drawable.place_holder)).into(binding.filterListItemLayoutProductImage);

            // Invalid Price Style
            binding.filterListItemLayoutProductRegularPrice.setPaintFlags(new TextView(activity).getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

            // Card Event
            binding.filterListItemLayoutCardView.setOnClickListener(view -> activity.startActivity(ProductDetailActivity.newIntent(activity, viewModel.getId())));

        }

    }


    public void setData(List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }
    
}
