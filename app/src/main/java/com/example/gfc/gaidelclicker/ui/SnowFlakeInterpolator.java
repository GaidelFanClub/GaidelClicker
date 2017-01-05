package com.example.gfc.gaidelclicker.ui;

import android.animation.TimeInterpolator;

/**
 * Created by user on 05.01.2017.
 * Interpolates time along parabola (x-1)^2 -1 in [0,2.414] diapason
 */
public class SnowFlakeInterpolator implements TimeInterpolator {

    private float range = 2.414f;

    @Override
    public float getInterpolation(float input) {
        input *= range;
        return (input - 1) * (input - 1) - 1;
    }
}
