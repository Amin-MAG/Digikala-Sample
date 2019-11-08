package com.mag.digikala.Util;

import android.graphics.Color;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.snackbar.Snackbar;

public class UiUtil {

    public static void changeFragment(FragmentManager fragmentManager, Fragment fragment, int containerId, boolean isreplaced, String tagName) {

        if (isreplaced)
            fragmentManager
                    .beginTransaction()
                    .replace(containerId, fragment)
                    .commit();
        else
            fragmentManager
                    .beginTransaction()
                    .add(containerId, fragment, tagName)
                    .commit();


    }


    public static void showSnackbar(View mainLayout, String message, String color) {
        Snackbar snackbar = Snackbar.make(mainLayout, message, Snackbar.LENGTH_SHORT);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(Color.parseColor(color));
        snackbar.show();
    }

}
