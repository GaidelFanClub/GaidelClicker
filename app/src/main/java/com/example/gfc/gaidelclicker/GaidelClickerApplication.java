package com.example.gfc.gaidelclicker;

import android.app.Application;

import com.example.gfc.gaidelclicker.achievment.AchievementsCenter;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.tumblr.remember.Remember;

public class GaidelClickerApplication extends Application {

    private static GaidelClickerApplication instance;
    private Tracker mTracker;

    public GaidelClickerApplication() {
        instance = this;
    }

    public static GaidelClickerApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Remember.init(this, getPackageName());
        AchievementsCenter.getInstance();
    }

    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }
}
