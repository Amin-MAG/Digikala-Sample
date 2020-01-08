package com.mag.digikala.view.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mag.digikala.data.model.MenuItem;
import com.mag.digikala.R;

import java.util.List;

public class NavigationRecyclerAdapter extends RecyclerView.Adapter<NavigationRecyclerAdapter.NavigationRecyclerViewHolder> {

    public static final String SEPRATOR = "<!>SEPRATOR<!>";

    private List<MenuItem> items;
    private Activity activity;

    public NavigationRecyclerAdapter(List<MenuItem> items) {
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

        private ImageView mainLayout;
        private TextView itemString;
        private ImageView menuImage;
        private View devider;

        public NavigationRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            itemString = itemView.findViewById(R.id.navigation_layout__item_title);
            menuImage = itemView.findViewById(R.id.navigation_layout__menu_image);
            devider = itemView.findViewById(R.id.filter_fragment__devider);
            mainLayout = itemView.findViewById(R.id.navigation_layout__menu_image);

        }

        public void bind(final MenuItem item) {


            menuImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("navigationMenu", "onClick: in text bind");
                }
            });

            if (!item.getName().equals(SEPRATOR)) {
                itemString.setText(item.getName());
                menuImage.setImageDrawable(activity.getResources().getDrawable(item.getDrawbleId()));
//                mainLayout.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Log.i("tagtaapsldplasd", "onClick: in bind");
//                    }
//                Log.i("tagtaapsldplasd", "got here ");
//                itemString.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Log.i("tagtaapsldplasd", "onClick: in text bind");
//                    }
//                });
//                });
            } else {
                itemString.setVisibility(View.GONE);
                menuImage.setVisibility(View.GONE);
                devider.setVisibility(View.VISIBLE);
            }

        }

    }

}
