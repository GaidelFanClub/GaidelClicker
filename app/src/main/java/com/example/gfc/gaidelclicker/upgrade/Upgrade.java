package com.example.gfc.gaidelclicker.upgrade;

import com.example.gfc.gaidelclicker.Prefs;
import com.example.gfc.gaidelclicker.upgrade.conditions.Condition;
import com.example.gfc.gaidelclicker.upgrade.effects.Effect;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by user on 02.01.2017.
 */

public class Upgrade implements Comparable<Upgrade> {

    private int resourceId;
    private String name;
    private String effect;
    private BigInteger cost;

    private List<Effect> effects = new ArrayList<>();
    private List<Condition> conditions = new ArrayList<>();
    private int id;
    private String key;

    public Upgrade(int id, int resourceId, String name, String effect, BigInteger cost) {
        this.resourceId = resourceId;
        this.name = name;
        this.effect = effect;
        this.cost = cost;
        this.id = id;
        this.key = "upgrade" + id;
    }

    public int getId() {
        return id;
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

    public Upgrade addCondition(Condition condition) {
        conditions.add(condition);
        return this;
    }

    public Upgrade addEffect(Effect effect) {
        effects.add(effect);
        return this;
    }

    public void activateBonus() {
        for (Effect effect : effects) {
            effect.perform();
        }
    }

    public String getKey() {
        return key;
    }

    public boolean isPreconditionsFullfilled() {
        for (Condition condition : conditions) {
            if (!condition.isFulfilled()) return false;
        }
        return true;
    }

    @Override
    public int compareTo(Upgrade o) {
        return cost.compareTo(o.cost);
    }
}
