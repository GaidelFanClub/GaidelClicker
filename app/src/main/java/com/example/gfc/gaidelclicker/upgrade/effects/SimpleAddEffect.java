package com.example.gfc.gaidelclicker.upgrade.effects;

import com.example.gfc.gaidelclicker.building.BuildingsRepository;

import java.math.BigDecimal;

/**
 * Created by user on 02.01.2017.
 */

public class SimpleAddEffect implements Effect {

    private BigDecimal addValue;
    private int[] ids;

    public SimpleAddEffect(BigDecimal addValue, int... ids) {
        this.addValue = addValue;
        this.ids = ids;
    }

    @Override
    public void perform() {
        for (int id : ids) {
            BuildingsRepository.getInstance().changeAddBonus(id, addValue);
        }

    }
}
