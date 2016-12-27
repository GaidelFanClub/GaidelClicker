package com.example.gfc.gaidelclicker.utils;


import java.text.DecimalFormat;

public class FormatUtils {

    private static DecimalFormat df = new DecimalFormat("#.###");

    private FormatUtils() {
    }

    public static String formatClicksSpeed(double speed) {
        return df.format(speed);
    }

}
