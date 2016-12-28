package com.example.gfc.gaidelclicker;

import android.graphics.drawable.Drawable;

/**
 * Created by Artem on 29.12.2016.
 */

public class Bonus {
    private int id;
    private int imageResourceId;
    private int type;
    private int time;
    private int coefficient;
    private int buf;

    public Bonus(int id,int imageResourceId, int type, int time, int coefficient, int buf){
        this.id = id;
        this.imageResourceId = imageResourceId;
        this.type = type;
        this.time = time;
        this.coefficient = coefficient;
        this.buf = buf;
    }
}
