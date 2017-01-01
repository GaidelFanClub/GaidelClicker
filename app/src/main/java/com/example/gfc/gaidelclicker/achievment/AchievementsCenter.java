package com.example.gfc.gaidelclicker.achievment;

import com.example.gfc.gaidelclicker.GlobalPrefs;
import com.example.gfc.gaidelclicker.Prefs;
import com.example.gfc.gaidelclicker.building.Building;
import com.example.gfc.gaidelclicker.event.AchievementUnlockedEvent;

import org.greenrobot.eventbus.EventBus;

import java.math.BigDecimal;

/**
 * Created by user on 29.12.2016.
 */

public class AchievementsCenter implements GlobalPrefs.OnBalanceChangedListener {

    private static AchievementsCenter instance = new AchievementsCenter();

    public static AchievementsCenter getInstance() {
        return instance;
    }

    private AchievementsCenter() {
        achievements = AchievementsRepository.getInstance().getAchievements();
        GlobalPrefs.getInstance().registerListener(this);
    }

    private Achievement[] achievements;

    @Override
    public void onBalanceChanged(BigDecimal currentBalance, BigDecimal wholeProfit) {
        checkNewUnlockedAchievement();
    }

    public void onBuildingWasBought(Building building) {
        checkNewUnlockedAchievement();
    }

    private void checkNewUnlockedAchievement() {
        for (Achievement achievement : achievements) {
            if (achievement.isUnlocked()) {
                if (!Prefs.getBoolean(achievement.getKey(), false)) {
                    Prefs.putBoolean(achievement.getKey(), true);
                    EventBus.getDefault().post(new AchievementUnlockedEvent(achievement));
                }
            }
        }
    }
}
