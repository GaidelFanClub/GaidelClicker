package com.example.gfc.gaidelclicker.upgrade.conditions;

import com.example.gfc.gaidelclicker.GlobalPrefs;

/**
 * Created by user on 03.01.2017.
 */

public class GoldenCountCondition implements Condition {

    private int count;

    public GoldenCountCondition(int count) {
        this.count = count;
    }

    @Override
    public boolean isFulfilled() {
        return GlobalPrefs.getInstance().getGoldenCookies() >= count;
    }
}
