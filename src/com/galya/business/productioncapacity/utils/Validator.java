package com.galya.business.productioncapacity.utils;

public class Validator {

    public static final int MAX_LENGTH_SHORT_TEXTS = 255;
    public static final int MIN_COMPANY_NAME_LENGTH = 2;
    
    private static final int MAX_LENGTH_BULSTAT = 15;

    private static final String BULSTAT_PATTERN = "(BG)(\\d+)";
    private static final String DOUBLE_PATTERN = "(\\d+)?(.)?(\\d+)";

    public static boolean validateBulstat(String bulstat) {
        return bulstat.matches(BULSTAT_PATTERN) && bulstat.length() < MAX_LENGTH_BULSTAT;
    }

    public static boolean validateDouble(String doubleText) {
        return doubleText.matches(DOUBLE_PATTERN);
    }
}
