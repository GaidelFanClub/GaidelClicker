package com.example.gfc.gaidelclicker.building;

import com.example.gfc.gaidelclicker.Building;
import com.example.gfc.gaidelclicker.R;
import com.example.gfc.gaidelclicker.achievment.AchievementsCenter;
import com.example.gfc.gaidelclicker.bonus.Bonus;
import com.example.gfc.gaidelclicker.optimizations.TwoPowersCache;
import com.tumblr.remember.Remember;

import java.math.BigDecimal;

/**
 * Created by user on 27.12.2016.
 */

public class BuildingsRepository {
    private static BuildingsRepository instance = new BuildingsRepository();

    public static BuildingsRepository getInstance() {
        return instance;
    }

    private Building[] buildings;
    private BigDecimal deltaPerSecond = BigDecimal.ZERO;

    private Bonus bonus;

    private BuildingsRepository() {
        buildings = new Building[9];
        buildings[0] = new Building(1, R.mipmap.click, "Кликер", 20, 0.1);
        buildings[1] = new Building(2, R.mipmap.lector, "Сходить на лекции", 150, 1);
        buildings[2] = new Building(3, R.mipmap.practice, "Сделать идз", 560, 8);
        buildings[3] = new Building(4, R.mipmap.circle, "Сходить на кружок", 12000, 47);
        buildings[4] = new Building(5, R.mipmap.task, "Порешать тимус", 130000, 260);
        buildings[5] = new Building(6, R.mipmap.code_forces, "Написать раунд CF", 1400000, 1400);
        buildings[6] = new Building(7, R.mipmap.beer_factory, "Построить пив.завод", 20000000, 7800);
        buildings[7] = new Building(8, R.mipmap.go_to_final, "Выйти в финал", 330000000, 44000);
        buildings[8] = new Building(9, R.mipmap.dota, "Сыграть с Гайделем", 5100000000L, 100000);

        recalculateDelta();
    }

    public Building[] getBuildings() {
        return buildings;
    }

    public void buy(Building building) {
        Remember.putInt(building.getStringId(), getCount(building) + 1);
        recalculateDelta();
        AchievementsCenter.getInstance().onBuildingWasBought(building);
    }

    public BigDecimal getCoefficient(Building building) {
        int power = getCount(building) / 50;
        return TwoPowersCache.get(power);
    }

    public int getCount(Building building) {
        return Remember.getInt(building.getStringId(), 0);
    }

    public BigDecimal getDeltaPerSecond() {
        return deltaPerSecond;
    }

    public void setActiveBonus(Bonus bonus) {
        this.bonus = bonus;
        recalculateDelta();
    }

    public BigDecimal getClickProfit() {
        return bonus != null ? bonus.getCoefficient() : BigDecimal.ONE;
    }

    private void recalculateDelta() {
        BigDecimal delta = BigDecimal.ZERO;
        for (Building building : buildings) {
            delta = delta.add((BigDecimal.valueOf(building.getDelta()).multiply(getCoefficient(building))).multiply(BigDecimal.valueOf(getCount(building))));
        }
        if (bonus != null) {
            delta = delta.multiply(bonus.getCoefficient());
        }
        deltaPerSecond = delta;
    }

}
