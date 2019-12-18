package com.mag.digikala.Controller.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.mag.digikala.Controller.Activities.SearchActivity;
import com.mag.digikala.R;

public class CardActivity extends AppCompatActivity {


    public static Intent newIntent(Context context) {
        return new Intent(context, CardActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
    }

}
