package com.galya.business.productioncapacity.utils;

import java.util.Collection;

public class CommonUtils {

    public static boolean isEmpty(String text) {
        return text == null || text.equals("");
    }
    
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.size() < 1;
    }
}
