package com.example.gfc.gaidelclicker;


import com.google.android.gms.analytics.HitBuilders;

import java.util.Map;

public class Analytics {
    private Analytics() {
    }

    private static Analytics instance = new Analytics();

    public static Analytics getInstance() {
        return instance;
    }

    public void sendEvent(String action) {
        Map<String, String> eventParams = new HitBuilders.EventBuilder()
                .setAction(action)
                .build();
        GaidelClickerApplication.getInstance().getDefaultTracker().send(eventParams);
    }

    public void sendEvent(String action, String key, String value) {
        Map<String, String> eventParams = new HitBuilders.EventBuilder()
                .setAction(action)
                .set(key, value)
                .build();
        GaidelClickerApplication.getInstance().getDefaultTracker().send(eventParams);
    }

    public void sendEvent(String action, String key, String value, long count) {
        Map<String, String> eventParams = new HitBuilders.EventBuilder()
                .setAction(action)
                .set(key, value)
                .setValue(count)
                .build();
        GaidelClickerApplication.getInstance().getDefaultTracker().send(eventParams);
    }
}
