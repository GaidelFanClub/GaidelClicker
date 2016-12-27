package com.example.gfc.gaidelclicker;

import com.example.gfc.gaidelclicker.bonus.BuildingsRepository;

/**
 * Created by Artem on 26.12.2016.
 */

public class Building {

    private static final double PRICE_SCALE_FACTOR = 1.1d;

    private int id;
    private String stringId;
    private String name;
    private int imageResourceId;
    private double basePrice;
    private double baseDelta;


    public Building(int id, int imageResourceId, String name, double price, double delta) {
        this.id = id;
        this.stringId = String.valueOf(id);
        this.imageResourceId = imageResourceId;
        this.name = name;
        this.basePrice = price;
        this.baseDelta = delta;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getCount() {
        return BuildingsRepository.getInstance().getCount(this);
    }

    public double getPrice() {
        return Math.pow(PRICE_SCALE_FACTOR, getCount()) * basePrice;
    }

    public double getDelta() {
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
