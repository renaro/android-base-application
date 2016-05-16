package com.renarosantos.mybaseapplication.base;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.raizlabs.android.dbflow.config.FlowManager;

public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);

    }

}
