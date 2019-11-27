package com.mag.digikala.Util;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


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


}
