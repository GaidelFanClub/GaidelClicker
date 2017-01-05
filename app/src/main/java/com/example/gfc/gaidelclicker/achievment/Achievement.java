package com.example.gfc.gaidelclicker.achievment;

import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;

public abstract class Achievement {
    private int imageResourceId;
    private String name;
    private String description;

    @ColorInt
    private int color = -1;

    public Achievement(int imageResourceId, String name, String description) {
        this(imageResourceId, name, description, -1);
    }

    public Achievement(int imageResourceId, String name, String description, int color) {
        this.imageResourceId = imageResourceId;
        this.name = name;
        this.description = description;
        this.color = color;
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

    public int getColor() {
        return color;
    }
}
