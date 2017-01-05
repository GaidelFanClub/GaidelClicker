package com.example.gfc.gaidelclicker.upgrade.conditions;

import com.example.gfc.gaidelclicker.upgrade.Upgrade;

import java.util.List;

/**
 * Created by user on 03.01.2017.
 */

public class UpgradesIsBoughtCondition implements Condition {

    private Upgrade[] dependencies;

    public UpgradesIsBoughtCondition(List<Upgrade> upgrades, int... ids) {
        this.dependencies = new Upgrade[ids.length];
        for (int i = 0; i < ids.length; i++) {
            for (Upgrade upgrade : upgrades) {
                if (upgrade.getId() == ids[i]) {
                    dependencies[i] = upgrade;
                    break;
                }
            }
        }
    }

    @Override
    public boolean isFulfilled() {
        for (Upgrade upgrade : dependencies) {
            if (!upgrade.isBought()) return false;
        }
        return true;
    }
}
