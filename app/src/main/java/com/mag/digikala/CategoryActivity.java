package com.mag.digikala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class CategoryActivity extends AppCompatActivity {


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
    }

}
