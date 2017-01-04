package com.example.gfc.gaidelclicker.building;

import android.util.SparseArray;

import com.example.gfc.gaidelclicker.Prefs;
import com.example.gfc.gaidelclicker.R;
import com.example.gfc.gaidelclicker.achievment.AchievementsCenter;
import com.example.gfc.gaidelclicker.bonus.Bonus;
import com.example.gfc.gaidelclicker.optimizations.TwoPowersCache;
import com.example.gfc.gaidelclicker.upgrade.Upgrade;
import com.example.gfc.gaidelclicker.upgrade.UpgradesRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 27.12.2016.
 */

public class BuildingsRepository {

    public static final int ID_ALL = -2;
    public static final int ID_TAP = -1;
    public static final int ID_CLICK = 1;
    public static final int ID_TWITCH = 2;
    public static final int ID_LECTURE = 3;
    public static final int ID_LAB_WORK = 4;
    public static final int ID_PRACTICE = 5;
    public static final int ID_CIRCLE = 6;
    public static final int ID_CORMEN = 7;
    public static final int ID_TIMUS = 8;
    public static final int ID_CF = 9;
    public static final int ID_BEER = 10;
    public static final int ID_FINAL = 11;
    public static final int ID_DOTA = 12;

    private static final BigDecimal speedUp = new BigDecimal("1");

    private static BuildingsRepository instance = new BuildingsRepository();

    public static BuildingsRepository getInstance() {
        return instance;
    }

    private Building[] buildings;
    private BigDecimal baseDeltaPerSecond = BigDecimal.ZERO;
    private BigDecimal deltaPerSecond = BigDecimal.ZERO;

    private SparseArray<BigDecimal> addBonus = new SparseArray<>();
    private SparseArray<BigDecimal> mulBonus = new SparseArray<>();
    private SparseArray<BigDecimal> finalAddBonus = new SparseArray<>();
    private int percentage = 100;
    private int clickPercentCpS = 0;
    private int divideGoldenCookieSpawnFactor = 1;
    private int multipleGoldenCookiePresentFactor = 1;
    private int multipleGoldenCookieEffectFactor = 1;

    private Bonus bonus;

    private BuildingsRepository() {
        List<Building> buildingList = new ArrayList<>();
        buildingList.add(new Building(ID_CLICK, R.mipmap.click, "Кликер", 20, 0.1));
        buildingList.add(new Building(ID_TWITCH, R.mipmap.twitch, "Посмотреть стрим Гайделя", 50, 0.5));
        buildingList.add(new Building(ID_LECTURE, R.mipmap.lecture, "Сходить на лекции", 150, 1));
        buildingList.add(new Building(ID_LAB_WORK, R.mipmap.lab_work, "Сдать лабу", 560, 3));
        buildingList.add(new Building(ID_PRACTICE, R.mipmap.practice, "Сделать идз", 1100, 8));
        buildingList.add(new Building(ID_CIRCLE, R.mipmap.circle, "Сходить на кружок", 12000, 47));
        buildingList.add(new Building(ID_CORMEN, R.mipmap.cormen, "Почитать кормена", 56000, 68));
        buildingList.add(new Building(ID_TIMUS, R.mipmap.task, "Порешать тимус", 130000, 260));
        buildingList.add(new Building(ID_CF, R.mipmap.code_forces, "Написать раунд CF", 1400000, 1400));
        buildingList.add(new Building(ID_BEER, R.mipmap.beer_factory, "Построить пивзавод", 20000000, 7800));
        buildingList.add(new Building(ID_FINAL, R.mipmap.go_to_final, "Выйти в финал", 330000000, 44000));
        buildingList.add(new Building(ID_DOTA, R.mipmap.dota, "Сыграть с Гайделем в доту", 5100000000L, 100000));

        buildings = buildingList.toArray(new Building[buildingList.size()]);
    }

    public Building getBuildingById(int id) {
        for (Building building : buildings) {
            if (building.getId() == id) return building;
        }
        return null;
    }

    public void changeAddBonus(int id, BigDecimal add) {
        BigDecimal current = addBonus.get(id, BigDecimal.ZERO);
        current = current.add(add);
        addBonus.put(id, current);
    }

    public void changeFinalAddBonus(int id, BigDecimal add) {
        BigDecimal current = finalAddBonus.get(id, BigDecimal.ZERO);
        current = current.add(add);
        finalAddBonus.put(id, current);
    }

    public void addPercentage(int add) {
        percentage += add;
    }

    public void changeMulBonus(int id, BigDecimal mul) {
        BigDecimal current = mulBonus.get(id, BigDecimal.ONE);
        current = current.multiply(mul);
        mulBonus.put(id, current);
    }

    public void changeGoldenCookieFactors(int divSpawn, int mulPresent, int mulEffect) {
        divideGoldenCookieSpawnFactor *= divSpawn;
        multipleGoldenCookiePresentFactor *= mulPresent;
        multipleGoldenCookieEffectFactor *= mulEffect;
    }

    public int getDivideGoldenCookieSpawnFactor() {
        return divideGoldenCookieSpawnFactor;
    }

    public int getMultipleGoldenCookiePresentFactor() {
        return multipleGoldenCookiePresentFactor;
    }

    public int getMultipleGoldenCookieEffectFactor() {
        return multipleGoldenCookieEffectFactor;
    }

    public void increaseClickPercentCpS(int addPercent) {
        clickPercentCpS += addPercent;
    }

    public Building[] getBuildings() {
        return buildings;
    }

    public void buy(Building building) {
        Prefs.putInt(building.getStringId(), getCount(building) + 1);
        recalculateDelta();
        AchievementsCenter.getInstance().onBuildingWasBought(building);
    }

    public BigDecimal getCoefficient(Building building) {
        int power = getCount(building) / 50;
        return TwoPowersCache.get(power);
    }

    public int getCount(Building building) {
        return Prefs.getInt(building.getStringId(), 0);
    }

    public BigDecimal getDeltaPerSecond() {
        return deltaPerSecond;
    }

    public void setActiveBonus(Bonus bonus) {
        this.bonus = bonus;
        recalculateDelta();
    }

    public BigDecimal getClickProfit() {
        BigDecimal value = calculate(BigDecimal.ONE, ID_TAP);
        value = value.add(BigDecimal.valueOf(clickPercentCpS / 100d).multiply(baseDeltaPerSecond));
        if (bonus != null) {
            value = value.multiply(bonus.getCoefficient());
        }
        value = value.multiply(speedUp);
        return value;
    }

    public void recalculateDelta() {
        reactivateAllUpgrades();
        BigDecimal delta = BigDecimal.ZERO;
        for (Building building : buildings) {
            BigDecimal value = calculate(building.getDelta(), building.getId());
            value = value.multiply(getCoefficient(building));
            value = value.multiply(BigDecimal.valueOf(getCount(building)));
            delta = delta.add(value);
        }
        delta = delta.multiply(speedUp);
        BigDecimal percentageVal = BigDecimal.valueOf(percentage / 100d);
        delta = delta.multiply(percentageVal);
        baseDeltaPerSecond = delta;
        if (bonus != null) {
            delta = delta.multiply(bonus.getCoefficient());
        }
        deltaPerSecond = delta;
    }

    private void reactivateAllUpgrades() {
        addBonus.clear();
        mulBonus.clear();
        finalAddBonus.clear();
        percentage = 100;
        clickPercentCpS = 0;
        divideGoldenCookieSpawnFactor = multipleGoldenCookiePresentFactor = multipleGoldenCookieEffectFactor = 1;
        for (Upgrade upgrade : UpgradesRepository.getInstance().getBoughtUpgrades()) {
            if (upgrade.isBought()) {
                upgrade.activateBonus();
            }
        }
    }

    private BigDecimal calculate(BigDecimal base, int id) {
        BigDecimal add = addBonus.get(id, BigDecimal.ZERO);
        BigDecimal mul = mulBonus.get(id, BigDecimal.ONE);
        BigDecimal finalAdd = finalAddBonus.get(id, BigDecimal.ZERO);
        return base.add(add).multiply(mul).add(finalAdd);
    }

}
