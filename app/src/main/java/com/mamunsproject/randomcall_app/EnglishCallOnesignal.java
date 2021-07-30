package com.mamunsproject.randomcall_app;

import android.app.Application;

import com.onesignal.OneSignal;


public class EnglishCallOnesignal extends Application {

    private static final String ONESIGNAL_APP_ID = "d469351e-758a-4d29-92fd-e3c56aa94f7c";

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId(ONESIGNAL_APP_ID);


    }
}