package com.example.gfc.gaidelclicker;

import com.tumblr.remember.Remember;

import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 27.12.2016.
 */

public class GlobalPrefs {

    private static final String LAST_UPDATE_TIMESTAMP = "LAST_UPDATE_TIMESTAMP";
    private static final String BALANCE = "LAST_BALANCE";
    private static final String WHOLE_PROFIT = "WHOLE_PROFIT";
    private static final String GOLDEN_COOKIES = "GOLDEN_COOKIES";
    private static final String COUNT_OF_CLICK = "COUNT_OF_CLICK";

    private static GlobalPrefs instance = new GlobalPrefs();
    public interface OnBalanceChangedListener {
        void onBalanceChanged(BigDecimal currentBalance, BigDecimal wholeProfit);
    }

    private List<WeakReference<OnBalanceChangedListener>> listeners = new ArrayList<>();

    public static GlobalPrefs getInstance() {
        return instance;
    }

    public BigDecimal getBalance() {
        return new BigDecimal(Remember.getString(BALANCE, BigDecimal.ZERO.toString()));
    }

    public BigDecimal getWholeProfit() {
        return new BigDecimal(Remember.getString(WHOLE_PROFIT, BigDecimal.ZERO.toString()));
    }

    public void putWholeProfit(BigDecimal wholeProfit) {
        Remember.putString(WHOLE_PROFIT, wholeProfit.toString());
    }

    public void putBalance(BigDecimal balance) {
        Remember.putString(BALANCE, balance.toString());
    }

    public void changeBalance(BigDecimal difference) {
        putBalance(getBalance().add(difference));
        if (difference.compareTo(BigDecimal.ZERO) >= 0) {
            putWholeProfit(getWholeProfit().add(difference));
        }
        notifyListeners(getBalance(), getWholeProfit());
    }

    public long getLastUpdateTs() {
        return Remember.getLong(LAST_UPDATE_TIMESTAMP, System.currentTimeMillis());
    }

    public void putLastUpdateTs(long ts) {
        Remember.putLong(LAST_UPDATE_TIMESTAMP, ts);
    }

    public int getGoldenCookies() {
        return Remember.getInt(GOLDEN_COOKIES, 0);
    }

    public void addGoldenCookie() {
        Remember.putInt(GOLDEN_COOKIES, getGoldenCookies() + 1);
    }

    public int getCountOfClicks() {
        return Remember.getInt(COUNT_OF_CLICK, 0);
    }

    public void increaseClickCount(int delta) {
        Remember.putInt(COUNT_OF_CLICK, getCountOfClicks() + delta);
    }

    public void registerListener(OnBalanceChangedListener listener) {
        if (!alreadyListener(listener)) {
            listeners.add(new WeakReference<>(listener));
        }
        listener.onBalanceChanged(getBalance(), getWholeProfit());
    }

    private void notifyListeners(BigDecimal balance, BigDecimal wholeProfit) {
        for (WeakReference<OnBalanceChangedListener> weakListener : listeners) {
            OnBalanceChangedListener listener = weakListener.get();
            if (listener != null) {
                listener.onBalanceChanged(balance, wholeProfit);
            }
        }
    }

    public boolean alreadyListener(OnBalanceChangedListener listener) {
        for (WeakReference<OnBalanceChangedListener> weakReference : listeners) {
            if (weakReference.get() == listener) return true;
        }
        return false;
    }




}
