package com.example.gfc.gaidelclicker.building;

import com.example.gfc.gaidelclicker.optimizations.PriceFactorCalculator;

import java.math.BigDecimal;

/**
 * Created by Artem on 26.12.2016.
 */

public class Building {

    private int id;
    private String stringId;
    private String name;

    private int imageResourceId;
    private BigDecimal basePrice;
    private BigDecimal baseDelta;


    public Building(int id, int imageResourceId, String name, long price, double delta) {
        this.id = id;
        this.stringId = String.valueOf(id);
        this.imageResourceId = imageResourceId;
        this.name = name;
        this.basePrice = BigDecimal.valueOf(price);
        this.baseDelta = BigDecimal.valueOf(delta);
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getCount() {
        return BuildingsRepository.getInstance().getCount(this);
    }

    public BigDecimal getPrice() {
        return PriceFactorCalculator.getPriceScaleFactor(getCount()).multiply(basePrice);
    }

    public BigDecimal getDelta() {
        return baseDelta;
    }

    public int getId() {
        return id;
    }

    public String getStringId() {
        return stringId;
    }

    public String getName() {
        return name;
    }

}
