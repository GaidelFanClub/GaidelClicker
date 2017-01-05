package com.example.gfc.gaidelclicker.achievment;


import android.support.annotation.ColorInt;

import com.example.gfc.gaidelclicker.GlobalPrefs;
import com.example.gfc.gaidelclicker.R;

public class ClickGaidelAchievement extends Achievement {

    private final int count;

    public ClickGaidelAchievement(String name, String description, int count, @ColorInt int color) {
        super(R.mipmap.click, name, description, color);
        this.count = count;
    }

    @Override
    public boolean isUnlocked() {
        return GlobalPrefs.getInstance().getCountOfClicks() >= count;
    }

    @Override
    public String getKey() {
        return getName();
    }
}
