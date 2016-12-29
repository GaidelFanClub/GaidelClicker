package com.example.gfc.gaidelclicker.achievment;

import com.example.gfc.gaidelclicker.Building;
import com.example.gfc.gaidelclicker.bonus.BuildingsRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 29.12.2016.
 */

public class AchievementsRepository {

    private static AchievementsRepository instance = new AchievementsRepository();

    public static AchievementsRepository getInstance() {
        return instance;
    }

    private Achievement[] achievements;

    private AchievementsRepository() {
        List<Achievement> achievementList = new ArrayList<>();
        Building[] buildings = BuildingsRepository.getInstance().getBuildings();
        int[] counts = {1, 10, 100, 250};
        for (Building building : buildings) {
            for (int count : counts) {
                achievementList.add(new BuildingCountAchievement(building, count, building.getImageResourceId(), building.getName() + " x" + count));
            }
        }
        achievements = achievementList.toArray(new Achievement[achievementList.size()]);
    }

    public Achievement[] getAchievements() {
        return achievements;
    }

}
