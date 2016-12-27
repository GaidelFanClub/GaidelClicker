package com.example.gfc.gaidelclicker;

import com.tumblr.remember.Remember;

/**
 * Created by user on 27.12.2016.
 */

public class GlobalPrefs {

    private static final String LAST_UPDATE_TIMESTAMP = "LAST_UPDATE_TIMESTAMP";
    private static final String BALANCE = "LAST_BALANCE";

    private static GlobalPrefs instance = new GlobalPrefs();

    public static GlobalPrefs getInstance() {
        return instance;
    }

    public double getBalance() {
        return Remember.getFloat(BALANCE, 0f);
    }

    public void putBalance(double balance) {
        Remember.putFloat(BALANCE, (float) balance);//TODO migrate to bigdecimal
    }

    public void increaseBalance(double balance) {
        putBalance(getBalance() + balance);
    }

    public long getLastUpdateTs() {
        return Remember.getLong(LAST_UPDATE_TIMESTAMP, System.currentTimeMillis());
    }

    public void putLastUpdateTs(long ts) {
        Remember.putLong(LAST_UPDATE_TIMESTAMP, ts);
    }

}
