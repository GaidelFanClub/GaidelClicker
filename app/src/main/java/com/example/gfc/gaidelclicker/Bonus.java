package com.example.gfc.gaidelclicker;

import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Artem on 26.12.2016.
 */

public class Bonus {
    private int count = 0;
    private double price = 0;
    private double delta = 0;


    public Bonus(double price, double delta) {
        this.count = 0;
        this.price = price;
        this.delta = delta;

    }

    public void UpdateItem() {
        if (MainActivity.count >= this.price) {
            MainActivity.count -= this.price;
            this.price *= 1.1;
            this.count++;
            MainActivity.delta += this.delta;
            MainActivity.countOfClick.setText(Integer.toString((int)MainActivity.count));
        }
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
}
