package com.example.gfc.gaidelclicker.upgrade;

import com.example.gfc.gaidelclicker.building.BuildingsRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by user on 02.01.2017.
 */

public class SimpleAddUpgrade extends Upgrade {

    private int[] ids;
    private BigDecimal addValue;
    private String key;

    public SimpleAddUpgrade(int resourceId, String name, String effect, BigInteger cost, BigDecimal addValue, int... ids) {
        super(resourceId, name, effect, cost);
        this.addValue = addValue;
        this.ids = ids;
        this.key = "simple_add" + addValue + " " + Arrays.toString(ids);
    }

    @Override
    public void activateBonus() {
        for (int id : ids) {
            BuildingsRepository.getInstance().changeAddBonus(id, addValue);
        }
    }

    @Override
    public String getKey() {
        return key;
    }
}
