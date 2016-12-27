package com.example.gfc.gaidelclicker;

import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Artem on 26.12.2016.
 */

public class Building {
    private int imageResourceId;
    private String name;
    private int count = 0;
    private double price = 0;
    private double delta = 0;


    public Building(int imageResourceId, String name, double price, double delta) {
        this.imageResourceId = imageResourceId;
        this.name = name;
        this.count = 0;
        this.price = price;
        this.delta = delta;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getCount(){
        return count;
    }

    public double getPrice(){
        return price;
    }

    public double getDelta(){
        return delta;
    }

    public void buy() {
        price *= 1.1;
        count++;
    }

}
