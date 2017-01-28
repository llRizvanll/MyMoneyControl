package com.mymoneycontrol.android;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jrizvan on 1/28/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Configure Realm for the application
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.deleteRealm(realmConfiguration); // Clean slate
        Realm.setDefaultConfiguration(realmConfiguration); // Make this Realm the default
    }
}
