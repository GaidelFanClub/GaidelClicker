package com.example.gfc.gaidelclicker.upgrade.conditions;

import com.example.gfc.gaidelclicker.GlobalPrefs;

import java.math.BigDecimal;

/**
 * Created by user on 03.01.2017.
 */

public class HandMadeGaidelsCondition implements Condition {

    private BigDecimal value;

    public HandMadeGaidelsCondition(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean isFulfilled() {
        return GlobalPrefs.getInstance().getProfitFromClicks().compareTo(value) >= 0;
    }
}
