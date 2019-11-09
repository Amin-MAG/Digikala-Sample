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

import com.mag.digikala.Controller.Fragments.ToolbarFragment;
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
