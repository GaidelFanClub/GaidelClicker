package com.example.gfc.gaidelclicker.achievment;

import android.graphics.Color;

import com.example.gfc.gaidelclicker.R;
import com.example.gfc.gaidelclicker.building.Building;
import com.example.gfc.gaidelclicker.building.BuildingsRepository;

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

        achievementList.add(new GoldenGaidelAchievement(R.drawable.gaidel_face_dark, "Пiймав Гайделя", "Поймать золотого Гайделя", 1));
        achievementList.add(new GoldenGaidelAchievement(R.mipmap.bronze_gaidel, "Охотник", "Поймать 10 золотых Гайделей", 10));
        achievementList.add(new GoldenGaidelAchievement(R.mipmap.silver_gaidel, "Вы тут ночуете?", "Поймать 100 золотых Гайделей", 100));
        achievementList.add(new GoldenGaidelAchievement(R.mipmap.gold_gaidel, "Золотая армия", "Поймать 1000 золотых Гайделей", 1000));

        achievementList.add(new ClickGaidelAchievement("Клик!", "Кликнуть по Гайделю", 1, Color.BLACK));
        achievementList.add(new ClickGaidelAchievement("По лицу", "Кликнуть по Гайделю 10 раз", 10, Color.DKGRAY));
        achievementList.add(new ClickGaidelAchievement("Со всех сил", "Кликнуть по Гайделю 100 раз", 100, Color.GREEN));
        achievementList.add(new ClickGaidelAchievement("Избиение", "Кликнуть по Гайделю 1000 раз", 1000, Color.BLUE));
        achievementList.add(new ClickGaidelAchievement("Вы получили автомат по кликам", "Кликнуть по Гайделю 10000 раз", 10000, Color.MAGENTA));
        achievementList.add(new ClickGaidelAchievement("Вмятина на экране", "Кликнуть по Гайделю 100000 раз", 100000, Color.YELLOW));
        achievementList.add(new ClickGaidelAchievement("Сейчас бы в 2027 играть в кликеры", "Кликнуть по Гайделю миллион раз", 1000000, Color.RED));

        Building[] buildings = BuildingsRepository.getInstance().getBuildings();
        int[] counts = {1, 10, 50, 100, 150, 200, 250, 300};
        for (Building building : buildings) {
            for (int count : counts) {
                achievementList.add(new BuildingCountAchievement(building, count, building.getImageResourceId(), building.getName() + " x" + count));
            }
        }

        achievementList.add(new BuildingCountAchievement(BuildingsRepository.getInstance().getBuildingById(BuildingsRepository.ID_FINAL), 3, R.mipmap.go_to_final, "Нарушитель правил"));
        achievements = achievementList.toArray(new Achievement[achievementList.size()]);
    }

    public Achievement[] getAchievements() {
        return achievements;
    }

}
