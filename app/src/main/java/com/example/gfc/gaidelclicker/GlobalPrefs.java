package com.example.gfc.gaidelclicker;

import com.tumblr.remember.Remember;

import java.math.BigDecimal;

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

    public BigDecimal getBalance() {
        return new BigDecimal(Remember.getString(BALANCE, BigDecimal.ZERO.toString()));
    }

    public void putBalance(BigDecimal balance) {
        Remember.putString(BALANCE, balance.toString());
    }

    public void increaseBalance(BigDecimal balance) {
        putBalance(getBalance().add(balance));
    }

    public long getLastUpdateTs() {
        return Remember.getLong(LAST_UPDATE_TIMESTAMP, System.currentTimeMillis());
    }

    public void putLastUpdateTs(long ts) {
        Remember.putLong(LAST_UPDATE_TIMESTAMP, ts);
    }

}
