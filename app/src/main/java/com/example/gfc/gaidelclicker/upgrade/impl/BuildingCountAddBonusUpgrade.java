package com.example.gfc.gaidelclicker.upgrade.impl;

import com.example.gfc.gaidelclicker.building.Building;
import com.example.gfc.gaidelclicker.building.BuildingsRepository;
import com.example.gfc.gaidelclicker.upgrade.Upgrade;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by user on 02.01.2017.
 */

public class BuildingCountAddBonusUpgrade extends Upgrade {

    private BigDecimal factor;
    private int id;
    private String key;

    public BuildingCountAddBonusUpgrade(int resourceId, String name, String effect, BigInteger cost, BigDecimal factor, int id) {
        super(resourceId, name, effect, cost);
        key = "build_count_add" + cost + " " + factor + " " + id;
        this.factor = factor;
        this.id = id;
    }

    @Override
    public void activateBonus() {
        int count = 0;
        for (Building building : BuildingsRepository.getInstance().getBuildings()) {
            count += building.getCount();
        }
        BigDecimal resultFactor = factor.multiply(BigDecimal.valueOf(count));
        BuildingsRepository.getInstance().changeAddBonus(id, resultFactor);
    }

    @Override
    public String getKey() {
        return key;
    }
}
