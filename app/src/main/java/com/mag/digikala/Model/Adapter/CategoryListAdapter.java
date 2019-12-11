package com.mag.digikala.Model.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.Model.Category;
import com.mag.digikala.R;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.CategoryListViewHolder> {

    private List<Category> categories;
    private Activity activity;

    public CategoryListAdapter(List<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activity = (Activity) parent.getContext();
        ;
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_category_list_item, parent, false);
        return new CategoryListViewHolder(view);
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

        private TextView title;


        public CategoryListViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.layout_category_list_item__title);

        }

        public void bind(Category category) {

            title.setText(category.getName());

        }

    }

}
