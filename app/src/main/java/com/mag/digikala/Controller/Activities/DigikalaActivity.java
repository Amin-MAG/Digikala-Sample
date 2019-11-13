package com.mag.digikala.Controller.Activities;

import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.mag.digikala.Controller.Fragments.ToolbarFragment;
import com.mag.digikala.Model.Adapter.NavigationRecyclerAdapter;
import com.mag.digikala.Model.Adapter.ProductRecyclerAdapter;
import com.mag.digikala.Model.Merchandise;
import com.mag.digikala.R;
import com.mag.digikala.Util.UiUtil;

import java.util.ArrayList;

public class DigikalaActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ToolbarFragment toolbarFragment;
    private RecyclerView navigationRecycler;
    private RecyclerView newstProductRecycler;
    private RecyclerView bestProductRecycler;
    private RecyclerView mostViewedProductRecycler;
    private NavigationRecyclerAdapter navigationRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digikala);

        drawerLayout = findViewById(R.id.digikala_activity__drawer_layout);
        toolbarFragment = ToolbarFragment.newInstance();

        UiUtil.changeFragment(getSupportFragmentManager(), toolbarFragment, R.id.digikala_activity__toolbar_frame, true, "fragment_toolbar");


        navigationRecycler = findViewById(R.id.digikala_activity__navigation_recycler);
        navigationRecyclerAdapter = new NavigationRecyclerAdapter(new ArrayList<String>() {{
            add(getString(R.string.home_page));
            add(getString(R.string.home_page));
            add(getString(R.string.home_page));
            add(getString(R.string.home_page));
            add(getString(R.string.home_page));
            add(getString(R.string.home_page));
            add(getString(R.string.home_page));
        }});
        navigationRecycler.setAdapter(navigationRecyclerAdapter);
        bestProductRecycler = findViewById(R.id.digikala_activity__best);
        bestProductRecycler.setAdapter(new ProductRecyclerAdapter(new ArrayList<Merchandise>() {{
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
        }}));
        newstProductRecycler = findViewById(R.id.digikala_activity__newest);
        newstProductRecycler.setAdapter(new ProductRecyclerAdapter(new ArrayList<Merchandise>() {{
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
        }}));
        mostViewedProductRecycler = findViewById(R.id.digikala_activity__most_view);
        mostViewedProductRecycler.setAdapter(new ProductRecyclerAdapter(new ArrayList<Merchandise>() {{
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
            add(new Merchandise("Something"));
        }}));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);

        for (int i = 0; i < menu.size(); i++) {

            SpannableString s = new SpannableString("Something");
            s.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_OPPOSITE), 0, s.length(), 0);
            menu.getItem(i).setTitle(s);

        }


        return true;
    }

    public void openNavigationView() {
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

}
