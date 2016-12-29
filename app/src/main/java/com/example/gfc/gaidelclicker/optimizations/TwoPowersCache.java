package com.example.gfc.gaidelclicker.optimizations;

import android.util.SparseArray;

import com.example.gfc.gaidelclicker.utils.BigDecimalUtils;

import java.math.BigDecimal;


public class TwoPowersCache {

    private static final SparseArray<BigDecimal> memoryCache = new SparseArray<>();

    public static final BigDecimal get(int power) {
        if (power < 0) throw new IllegalArgumentException("power must be positive");
        BigDecimal result = memoryCache.get(power);
        if (result == null) {
            result = BigDecimalUtils.positivePow(BigDecimal.valueOf(2), power);
            memoryCache.put(power, result);
        }
        return result;
    }
}
