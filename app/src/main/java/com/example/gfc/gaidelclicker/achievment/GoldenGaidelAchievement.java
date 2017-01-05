package com.example.gfc.gaidelclicker.achievment;


import com.example.gfc.gaidelclicker.GlobalPrefs;

public class GoldenGaidelAchievement extends Achievement {

    private final int count;

    public GoldenGaidelAchievement(int imageResourceId, String name, String description, int count) {
        super(imageResourceId, name, description);
        this.count = count;
    }

    @Override
    public boolean isUnlocked() {
        return GlobalPrefs.getInstance().getGoldenCookies() >= count;
    }

    @Override
    public String getKey() {
        return getName();
    }
}
