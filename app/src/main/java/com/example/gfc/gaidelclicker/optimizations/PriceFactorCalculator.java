package com.example.gfc.gaidelclicker.optimizations;

import android.util.SparseArray;

import com.example.gfc.gaidelclicker.utils.BigDecimalUtils;

import java.math.BigDecimal;


public class PriceFactorCalculator {

    private static final SparseArray<BigDecimal> memoryCache = new SparseArray<>();
    private static final BigDecimal PRICE_SCALE_FACTOR = BigDecimal.valueOf(1.15d);

    public static BigDecimal getPriceScaleFactor(int value) {
        if (value < 0) throw new IllegalArgumentException("Value must be positive");
        BigDecimal result = memoryCache.get(value);
        if (result == null) {
            result = BigDecimalUtils.positivePow(PRICE_SCALE_FACTOR, value);
            memoryCache.put(value, result);
        }
        return result;
    }
}
