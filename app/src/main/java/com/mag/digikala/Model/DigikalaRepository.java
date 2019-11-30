package com.mag.digikala.Model;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.mag.digikala.CategoryActivity;
import com.mag.digikala.Model.Adapter.NavigationRecyclerAdapter;
import com.mag.digikala.R;
import com.mag.digikala.Var.Constants;

import java.util.ArrayList;
import java.util.List;

public class DigikalaRepository {

    // Singleton

    private static DigikalaRepository instance;

    public DigikalaRepository() {

    }

    public static DigikalaRepository getInstance() {
        if (instance == null)
            instance = new DigikalaRepository();
        return instance;
    }

    // Products

    private List<Merchandise> allProducts;

    public void setAllProducts(List<Merchandise> allProducts) {
        this.allProducts = allProducts;
    }

    public List<Merchandise> getAllProducts() {
        return allProducts;
    }

    public Merchandise getProductById(String id) {
        for (Merchandise merchandise : allProducts) {
            if (merchandise.getId().equals(id)) return merchandise;
        }
        return null;
    }


    // Navigation items

    private List<DigikalaMenuItem> navigationItems;

    public List<DigikalaMenuItem> getNavigationItems() {
        return navigationItems;
    }

    public void setNavigationItems(List<DigikalaMenuItem> navigationItems) {
        this.navigationItems = navigationItems;
    }

    public void setNavigationItems(Context context) {
        this.navigationItems = getDefaultList(context);
    }

    public List<Merchandise> getOfferedProduct() {

        ArrayList<Merchandise> offered = new ArrayList<>();

        for (Merchandise merchandise : allProducts)
            if (merchandise.getSale_price().length() > 0)
                offered.add(merchandise);

        return offered;

    }

    private List<DigikalaMenuItem> getDefaultList(final Context context) {

        return new ArrayList<DigikalaMenuItem>() {{
            add(new DigikalaMenuItem(context.getString(R.string.home_page), R.drawable.ic_home, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }));
            add(new DigikalaMenuItem(context.getString(R.string.category), R.drawable.ic_category, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("tagtaapsldplasd", "onClick: ");
                    context.startActivity(CategoryActivity.newIntent(context, ""));
                }
            }));
            add(new DigikalaMenuItem(NavigationRecyclerAdapter.SEPRATOR, Constants.NOT_FOUNDED, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }));
            add(new DigikalaMenuItem(context.getString(R.string.cart), R.drawable.ic_shopping_cart_dark, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }));
            add(new DigikalaMenuItem(NavigationRecyclerAdapter.SEPRATOR, Constants.NOT_FOUNDED, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }));
            add(new DigikalaMenuItem(context.getString(R.string.offers), R.drawable.ic_star, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }));
            add(new DigikalaMenuItem(context.getString(R.string.best_sellers), R.drawable.ic_star, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }));
            add(new DigikalaMenuItem(context.getString(R.string.most_views), R.drawable.ic_star, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }));
            add(new DigikalaMenuItem(context.getString(R.string.newests), R.drawable.ic_star, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }));
            add(new DigikalaMenuItem(NavigationRecyclerAdapter.SEPRATOR, Constants.NOT_FOUNDED, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }));
            add(new DigikalaMenuItem(context.getString(R.string.setting), R.drawable.ic_setting, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }));
            add(new DigikalaMenuItem(context.getString(R.string.faq), R.drawable.ic_help, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }));
            add(new DigikalaMenuItem(context.getString(R.string.about_us), R.drawable.ic_phone, new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }));
        }};


    }


}
