package com.example.gfc.gaidelclicker.upgrade.effects;

import com.example.gfc.gaidelclicker.building.BuildingsRepository;

import java.math.BigDecimal;

/**
 * Created by user on 02.01.2017.
 */

public class SimpleMulEffect implements Effect {

    private BigDecimal mulValue;
    private int[] ids;

    public SimpleMulEffect(BigDecimal mulValue, int... ids) {
        this.mulValue = mulValue;
        this.ids = ids;
    }

    @Override
    public void perform() {
        for (int id : ids) {
            BuildingsRepository.getInstance().changeMulBonus(id, mulValue);
        }

    }
}
