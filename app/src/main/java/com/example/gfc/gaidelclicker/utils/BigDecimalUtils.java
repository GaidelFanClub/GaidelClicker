package com.example.gfc.gaidelclicker.utils;

import java.math.BigDecimal;

public class BigDecimalUtils {

    public static BigDecimal positivePow(BigDecimal base, int power) {
        if (power == 0) return BigDecimal.ONE;
        if (power % 2 == 0) {
            BigDecimal half = positivePow(base, power / 2);
            return half.multiply(half);
        } else {
            return positivePow(base, power - 1).multiply(base);
        }
    }
}
