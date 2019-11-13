package com.mag.digikala.Model.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.R;

import java.util.List;

public class NavigationRecyclerAdapter extends RecyclerView.Adapter<NavigationRecyclerAdapter.NavigationRecyclerViewHolder> {

    private List<String> items;
    private Activity activity;

    public NavigationRecyclerAdapter(List<String> items) {
        this.items = items;
    }


    @NonNull
    @Override
    public NavigationRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        activity = (Activity) parent.getContext();
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_navigation_items, parent, false);
        return new NavigationRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationRecyclerViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class NavigationRecyclerViewHolder extends RecyclerView.ViewHolder {

        private TextView itemString;

        public NavigationRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            itemString = itemView.findViewById(R.id.navigation_layout__item_title);

        }

        public void bind(String item) {

            itemString.setText("\u200F" + item + "");

        }

    }

}
