package com.example.gfc.gaidelclicker.utils;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class FormatUtils {

    private static final DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.US);
    private static final DecimalFormat df = new DecimalFormat("#.###", formatSymbols);
    private static final String integerFormat = "%s.%s × 10<sup>%d</sup>";
    private static final BigInteger SIMPLE_INTEGER_VALUE_THRESHOLD = BigInteger.valueOf(9999);
    private static final BigDecimal SIMPLE_DOUBLE_VALUE_THRESHOLD = BigDecimal.valueOf(999.99);

    private FormatUtils() {
    }

    public static CharSequence formatDecimal(BigDecimal value) {
        if (value.compareTo(SIMPLE_DOUBLE_VALUE_THRESHOLD) <= 0) return df.format(value.doubleValue());
        return formatIntegerNaive(value.toBigInteger());
    }

    public static CharSequence formatDecimalAsInteger(BigDecimal value) {
        return formatInteger(value.toBigInteger());
    }

    public static CharSequence formatInteger(BigInteger value) {
        if (value.compareTo(SIMPLE_INTEGER_VALUE_THRESHOLD) <= 0) return value.toString();
        return formatIntegerNaive(value);
    }

    private static CharSequence formatIntegerNaive(BigInteger value) {
        String stringRepresentation = value.toString();
        int radixCount = stringRepresentation.length() - 1;
        return nonLocalizeableFormat(integerFormat, stringRepresentation.substring(0, 1), stringRepresentation.substring(1, 3), radixCount);
    }

    private static CharSequence nonLocalizeableFormat(String formatString, Object... objects) {
        return String.format(Locale.US, formatString, objects);
    }

}
