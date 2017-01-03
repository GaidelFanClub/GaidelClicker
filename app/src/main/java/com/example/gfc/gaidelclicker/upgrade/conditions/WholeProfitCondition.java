package com.example.gfc.gaidelclicker.upgrade.conditions;

import com.example.gfc.gaidelclicker.GlobalPrefs;

import java.math.BigDecimal;

/**
 * Created by user on 03.01.2017.
 */

public class WholeProfitCondition implements Condition {

    private BigDecimal value;

    public WholeProfitCondition(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean isFulfilled() {
        return GlobalPrefs.getInstance().getWholeProfit().compareTo(value) >= 0;
    }
}
