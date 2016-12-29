package com.example.gfc.gaidelclicker.bonus;

import java.math.BigDecimal;

/**
 * Created by Artem on 29.12.2016.
 */

public class Bonus {
    private int id;
    private int imageResourceId;
    private String message;
    private boolean isImmediate;
    private int durationMillis;
    private BigDecimal coefficient;
    private int buf;

    public Bonus(int id, int imageResourceId, String message, boolean isImmediate, int durationMillis, BigDecimal coefficient, int buf) {
        this.id = id;
        this.imageResourceId = imageResourceId;
        this.message = message;
        this.isImmediate = isImmediate;
        this.durationMillis = durationMillis;
        this.coefficient = coefficient;
        this.buf = buf;
    }

    public void performImmediateAction(BigDecimal balance, BigDecimal wholeProfit) {

    }

    public int getId() {
        return id;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public String getMessage() {
        return message;
    }

    public boolean isImmediate() {
        return isImmediate;
    }

    public int getDurationMillis() {
        return durationMillis;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public int getBuf() {
        return buf;
    }
}
