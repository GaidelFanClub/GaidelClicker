package com.example.gfc.gaidelclicker;

import android.app.Application;
import android.os.AsyncTask;

import com.tumblr.remember.Remember;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Remember.init(this, getPackageName());
    }
}
