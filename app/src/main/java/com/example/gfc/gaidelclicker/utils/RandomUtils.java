package com.example.gfc.gaidelclicker.utils;

import java.util.Random;

/**
 * Created by user on 30.12.2016.
 */

public class RandomUtils {
    private static final Random random = new Random();

    public static final int nextInt(int from, int to) {
        int len = to - from + 1;
        if (len <= 0) {
            throw new IllegalArgumentException("from can't be greater that to");
        }
        int value = random.nextInt(len);
        return from + value;
    }

    public static double nextDouble(double from, double to) {
        if (from > to) throw new IllegalArgumentException("from can't be greater that to");
        double len = to - from;
        return from + random.nextDouble() * len;
    }

    public static int nextInt(int limit) {
        return random.nextInt(limit);
    }

    public static int nextIndex(int arrayLength) {
        return random.nextInt(arrayLength);
    }

    public static int nextSign() {
        return random.nextBoolean() ? 1 : -1;
    }
}
