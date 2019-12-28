package com.mag.digikala.View.Adapters;

import android.app.Activity;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Controller.Activities.CategoryActivity;
import com.mag.digikala.Model.CategoryGroup;
import com.mag.digikala.R;
import com.mag.digikala.databinding.LayoutCategoryGreenBtnItemBinding;
import com.mag.digikala.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryGreenButtonsRecyclerAdapter extends RecyclerView.Adapter<CategoryGreenButtonsRecyclerAdapter.CategoryRecyclerViewHolder> {


    private Activity activity;
    private List<CategoryGroup> items;

    public CategoryGreenButtonsRecyclerAdapter() {
        this.items = new ArrayList<>();
    }

    public CategoryGreenButtonsRecyclerAdapter(List<CategoryGroup> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activity = (Activity) parent.getContext();
        LayoutCategoryGreenBtnItemBinding binding = DataBindingUtil.inflate(activity.getLayoutInflater(), R.layout.layout_category_green_btn_item, parent, false);
        return new CategoryRecyclerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CategoryRecyclerViewHolder extends RecyclerView.ViewHolder {

        private LayoutCategoryGreenBtnItemBinding binding;
        private CategoryViewModel viewModel;

        public CategoryRecyclerViewHolder(@NonNull LayoutCategoryGreenBtnItemBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
            this.viewModel = new CategoryViewModel();

            binding.setCategoryViewModel(viewModel);
        }

        public void bind(final CategoryGroup categoryGroup) {

            binding.getCategoryViewModel().setCategoryGroup(categoryGroup);
            binding.layoutGreenCatItem.setOnClickListener(view -> activity.startActivity(CategoryActivity.newIntent(activity, categoryGroup.getId())));

        }

    }

    public void setCategoriesItems(List<CategoryGroup> items) {
        this.items = items;
        notifyDataSetChanged();
    }

}
