package com.example.weather;

import android.app.Application;
import android.content.ComponentCallbacks;

public class AppApplication extends Application {


    private static AppApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        setFontFamilyRegular();
    }

    public static synchronized AppApplication getInstance() {
        return app;
    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        super.registerComponentCallbacks(callback);
    }


    public void setFontFamilyBold() {
        //FontsManager.initFormAssets(getInstance(), "fonts/Assistant_Bold.ttf");
    }

    public void setFontFamilyRegular() {
        // FontsManager.initFormAssets(getInstance(), "fonts/Assistant_Regular.ttf");
    }



}

