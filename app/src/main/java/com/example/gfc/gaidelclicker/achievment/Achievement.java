package com.example.gfc.gaidelclicker.achievment;

/**
 * Created by Artem on 27.12.2016.
 */

public abstract class Achievement {
    private int imageResourceId;
    private String message;

    public Achievement(int imageResourceId, String message) {
        this.imageResourceId = imageResourceId;
        this.message = message;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getMessage() {
        return message;
    }

    public abstract boolean isUnlocked();
    public abstract String getKey();

}
