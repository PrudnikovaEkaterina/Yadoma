package ru.yadoma_realty.utils;

public class StringHelper {
    public static String getAllNumbersFromString (String source){
        return source.replaceAll("[^0-9]", "");
    }
}
