package com.mag.digikala.view.adapters;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.view.activities.FilterActivity;
import com.mag.digikala.data.model.Category;
import com.mag.digikala.R;
import com.mag.digikala.databinding.LayoutCategoryListItemBinding;
import com.mag.digikala.viewmodel.CategoryViewModel;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder> {

    private Activity activity;
    private List<Category> categories;

    public CategoryListAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activity = (Activity) parent.getContext();
        LayoutCategoryListItemBinding binding = DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.layout_category_list_item, parent, false);
        return new CategoryListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListViewHolder holder, int position) {
        holder.bind(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryListViewHolder extends RecyclerView.ViewHolder {

        private LayoutCategoryListItemBinding binding;
        private CategoryViewModel viewModel;


        public CategoryListViewHolder(@NonNull LayoutCategoryListItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
            this.viewModel = ViewModelProviders.of((FragmentActivity) activity).get(CategoryViewModel.class);

            binding.setCategoryViewmodel(viewModel);
        }

        public void bind(Category category) {

            viewModel.setCategory(category);
            binding.setCategoryViewmodel(viewModel);
            binding.executePendingBindings();

            binding.categoryListItemLayoutMainCardView.setOnClickListener(view -> activity.startActivity(FilterActivity.newIntent(activity, null, category.getId())));

        }

    }

}
