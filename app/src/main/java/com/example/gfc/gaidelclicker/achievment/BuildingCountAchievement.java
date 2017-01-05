package com.example.gfc.gaidelclicker.achievment;

import com.example.gfc.gaidelclicker.building.Building;

/**
 * Created by user on 29.12.2016.
 */

public class BuildingCountAchievement extends Achievement {

    private Building building;
    private int count;
    private String key;

    public BuildingCountAchievement(Building building, int count, int resourceId, String name) {
        super(resourceId, name, null);
        this.building = building;
        this.count = count;
        key = "building_" + building.getId() + " " + count;
    }

    @Override
    public boolean isUnlocked() {
        return building.getCount() >= count;
    }

    @Override
    public String getKey() {
        return key;
    }
}
