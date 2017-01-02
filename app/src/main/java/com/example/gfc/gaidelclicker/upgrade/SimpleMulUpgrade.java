package com.example.gfc.gaidelclicker.upgrade;

import com.example.gfc.gaidelclicker.building.BuildingsRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by user on 02.01.2017.
 */

public class SimpleMulUpgrade extends Upgrade {

    private int[] ids;
    private BigDecimal mulValue;
    private String key;

    public SimpleMulUpgrade(int resourceId, String name, String effect, BigInteger cost, BigDecimal mulValue, int... ids) {
        super(resourceId, name, effect, cost);
        this.mulValue = mulValue;
        this.ids = ids;
        this.key = "simple_add" + cost + " " + mulValue + " " + Arrays.toString(ids);
    }

    @Override
    public void activateBonus() {
        for (int id : ids) {
            BuildingsRepository.getInstance().changeMulBonus(id, mulValue);
        }
    }

    @Override
    public String getKey() {
        return key;
    }
}
