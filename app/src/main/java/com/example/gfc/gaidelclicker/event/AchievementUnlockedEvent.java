package com.example.gfc.gaidelclicker.event;

import com.example.gfc.gaidelclicker.achievment.Achievement;

/**
 * Created by user on 30.12.2016.
 */

public class AchievementUnlockedEvent {

    private Achievement achievement;

    public AchievementUnlockedEvent(Achievement achievement) {
        this.achievement = achievement;
    }

    public Achievement getAchievement() {
        return achievement;
    }
}
