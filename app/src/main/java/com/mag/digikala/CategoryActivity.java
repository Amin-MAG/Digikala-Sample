package com.mag.digikala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mag.digikala.Controller.Fragments.ToolbarFragment;
import com.mag.digikala.Util.UiUtil;

public class CategoryActivity extends AppCompatActivity {

    private CommonToolbarFragment commonToolbarFragment;

    public static final String EXTRA_CATEGORY_ID = "extra_category_id";

    public static Intent newIntent(Context context, String categoryId) {
        Intent intent = new Intent(context, CategoryActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        if (commonToolbarFragment == null) {
            commonToolbarFragment = CommonToolbarFragment.newInstance(getResources().getString(R.string.category));
            UiUtil.changeFragment(getSupportFragmentManager(), commonToolbarFragment, R.id.digikala_category__toolbar_frame, true, "fragment_category_common_toolbar");
        }



    }

}
