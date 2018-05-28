package com.purko.url.shortener.util;

/**
 * Util to encode base10 to base62 representation and visa versa
 */
public class EncodeUtil {

    /**
     * all unique characters used in base62 encoding
     */
    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE62_LENGTH = BASE62.length();


    /**
     * Encode base10 number to base62 string representation
     * @param base10number long value, which is an 'id' of particular UrlData object from DB
     * @return String base62 representation of argument
     */
    public static String fromBase10toBase62Encode(long base10number){

        StringBuilder base62Key = new StringBuilder();
        while (base10number != 0) {
            long remainder = base10number % BASE62_LENGTH;
            base10number  /=  BASE62_LENGTH;
            base62Key.insert(0, BASE62.charAt((int) remainder));
        }
        return base62Key.toString();
    }

    /**
     * Encode base62 string to base10 long number
     * @param base62Key string base62 key
     * @return long base10 number
     */
    public static long fromBase62toBase10Encode(String base62Key){
        long base10number = 0L;
        long exponent = base62Key.length()-1;

        for (int i = 0; i < base62Key.length() ; i++, exponent--) {
            int indexFromBase62ForValue = BASE62.indexOf(base62Key.charAt(i));
            base10number += indexFromBase62ForValue * Math.pow(BASE62_LENGTH, exponent);
        }

        return base10number;
    }
}
