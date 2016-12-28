package com.example.gfc.gaidelclicker.bonus;

import com.example.gfc.gaidelclicker.Building;
import com.example.gfc.gaidelclicker.R;
import com.tumblr.remember.Remember;

/**
 * Created by user on 27.12.2016.
 */

public class BuildingsRepository {

    private static BuildingsRepository instance = new BuildingsRepository();

    public static BuildingsRepository getInstance() {
        return instance;
    }

    private Building[] buildings;
    private double deltaPerSecond;

    private BuildingsRepository() {
        buildings = new Building[7];
        buildings[0] = new Building(1, R.mipmap.click, "Кликер", 20, 0.1);
        buildings[1] = new Building(2, R.mipmap.lector, "Сходить на лекции", 150, 1);
        buildings[2] = new Building(3, R.mipmap.practice, "Сделать идз", 560, 8);
        buildings[3] = new Building(4, R.mipmap.circle, "Сходить на кружок", 12000, 47);
        buildings[4] = new Building(5, R.mipmap.task, "Порешать тимус", 130000, 260);
        buildings[5] = new Building(6, R.drawable.building_rocket, "Two", 1400000, 1400);
        buildings[6] = new Building(7, R.drawable.building_wizard, "One", 20000000, 7800);
        recalculateDelta();
    }

    public Building[] getBuildings() {
        return buildings;
    }

    public void buy(Building building) {
        Remember.putInt(building.getStringId(), getCount(building) + 1);
        recalculateDelta();
    }

    public int getCount(Building building) {
        return Remember.getInt(building.getStringId(), 0);
    }

    public double getDeltaPerSecond() {
        return deltaPerSecond;
    }

    private void recalculateDelta() {
        double delta = 0;
        for (Building building : buildings) {
            delta += building.getDelta() * getCount(building);
        }
        deltaPerSecond = delta;
    }

}
