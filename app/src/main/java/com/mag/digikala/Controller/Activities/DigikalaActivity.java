package com.mag.digikala.Controller.Activities;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toolbar;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.mag.digikala.Controller.Fragments.*;
import com.mag.digikala.R;
import com.mag.digikala.Util.UiUtil;

public class DigikalaActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private ToolbarFragment toolbarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digikala);

        drawerLayout = findViewById(R.id.digikala_activity__drawer_layout);
        toolbarFragment = ToolbarFragment.newInstance();

        UiUtil.changeFragment(getSupportFragmentManager(), toolbarFragment, R.id.digikala_activity__toolbar_frame, true, "fragment_toolbar");

    }


    public void openNavigationView() {
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

}
