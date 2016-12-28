package com.example.gfc.gaidelclicker.utils;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

/**
 * Created by user on 29.12.2016.
 */

public class UIUtils {

    public static int getWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static int getHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }
}
