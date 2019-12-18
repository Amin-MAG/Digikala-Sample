package com.mag.digikala.Controller;

import android.app.Application;

import com.mag.digikala.Model.CardProduct;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class ShopApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        realmInitialization();

    }

    private void realmInitialization() {
        Realm.init(this);
        RealmConfiguration realmAppConfiguration = new RealmConfiguration.Builder()
                .name("shop.realm")
                .build();
        Realm.setDefaultConfiguration(realmAppConfiguration);

    }

}
