package com.mag.digikala.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.mag.digikala.view.fragments.CardFragment;
import com.mag.digikala.R;
import com.mag.digikala.util.UiUtil;
import com.mag.digikala.view.fragments.ToolbarFragments.CardToolbarFragment;
import com.mag.digikala.viewmodel.CardViewModel;

public class CardActivity extends AppCompatActivity {

    public static final String FRAGMENT_CARD_TOOLBAR = "fragment_card_toolbar";
    public static final String FRAGMENT_CARD_MAIN = "fragment_card_main";

    private CardViewModel viewModel;

    private CardToolbarFragment cardToolbarFragment;
    private CardFragment cardFragment;

    public static Intent newIntent(Context context) {
        return new Intent(context, CardActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);


        viewModel = ViewModelProviders.of(this).get(CardViewModel.class);

        if (cardToolbarFragment == null) {
            cardToolbarFragment = CardToolbarFragment.newInstance();
            UiUtil.changeFragment(getSupportFragmentManager(), cardToolbarFragment, R.id.card_activity__common_toolbar, true, FRAGMENT_CARD_TOOLBAR);
        }


        if (cardFragment == null) {
            cardFragment = CardFragment.newInstance();
            UiUtil.changeFragment(getSupportFragmentManager(), cardFragment, R.id.card_activity__main_frame, true, FRAGMENT_CARD_MAIN);
        }

    }

}
