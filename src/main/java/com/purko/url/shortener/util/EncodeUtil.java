package com.purko.url.shortener.util;

public class EncodeUtil {

    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE62_LENGTH = BASE62.length();

    public static String fromBase10toBase62Encode(long base10number){

        StringBuilder base62Key = new StringBuilder();
        while (base10number != 0) {
            long remainder = base10number % BASE62_LENGTH;
            base10number  /=  BASE62_LENGTH;
            base62Key.insert(0, BASE62.charAt((int) remainder));
        }
        return base62Key.toString();
    }


    public static long fromBase62toBase10Encode(String base62hashKey){
        long base10number = 0L;
        long exponent = base62hashKey.length()-1;

        for (int i = 0; i < base62hashKey.length() ; i++, exponent--) {
            int indexFromBase62ForValue = BASE62.indexOf(base62hashKey.charAt(i));
            base10number += indexFromBase62ForValue * Math.pow(BASE62_LENGTH, exponent);
        }

        return base10number;
    }
}
