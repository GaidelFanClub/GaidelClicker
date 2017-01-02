package com.example.gfc.gaidelclicker.upgrade;

import com.example.gfc.gaidelclicker.Prefs;

import java.math.BigInteger;

/**
 * Created by user on 02.01.2017.
 */

public abstract class Upgrade {

    private int resourceId;
    private String name;
    private String effect;
    private BigInteger cost;

    public Upgrade(int resourceId, String name, String effect, BigInteger cost) {
        this.resourceId = resourceId;
        this.name = name;
        this.effect = effect;
        this.cost = cost;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }

    public BigInteger getCost() {
        return cost;
    }

    public boolean isBought() {
        return Prefs.getBoolean(getKey(), false);
    }

    public void buy() {
        Prefs.putBoolean(getKey(), true);
    }

    public abstract void activateBonus();
    public abstract String getKey();

}
