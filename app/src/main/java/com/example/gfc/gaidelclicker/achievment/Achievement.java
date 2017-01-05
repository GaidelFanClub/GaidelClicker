package com.example.gfc.gaidelclicker.achievment;

import android.support.annotation.Nullable;

public abstract class Achievement {
    private int imageResourceId;
    private String name;
    private String description;

    public Achievement(int imageResourceId, String name, String description) {
        this.imageResourceId = imageResourceId;
        this.name = name;
        this.description = description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getName() {
        return name;
    }

    public abstract boolean isUnlocked();
    public abstract String getKey();

    @Nullable
    public String getDescription() {
        return description;
    }
}
