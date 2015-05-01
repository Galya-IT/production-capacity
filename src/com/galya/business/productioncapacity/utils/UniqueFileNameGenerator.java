package com.galya.business.productioncapacity.utils;

public class UniqueFileNameGenerator {
    private static int currentLastId = 0;

    public static String generate() {
        String fileName = String.valueOf(currentLastId);
        currentLastId++;
        return fileName;
    }
}
