package com.mag.digikala.Controller.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mag.digikala.Controller.Fragments.SearchToolbarFragment;
import com.mag.digikala.R;
import com.mag.digikala.Util.UiUtil;

public class SearchActivity extends AppCompatActivity {

    public static final String FRAGMENT_SEARCH_TOOLBAR = "fragment_search_toolbar";
    private SearchToolbarFragment searchToolbarFragment;

    public static Intent newIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (searchToolbarFragment == null) {
            searchToolbarFragment = SearchToolbarFragment.newInstance();
            UiUtil.changeFragment(getSupportFragmentManager(), searchToolbarFragment, R.id.search_activity__toolbar, true, FRAGMENT_SEARCH_TOOLBAR);
        }

    }

}
