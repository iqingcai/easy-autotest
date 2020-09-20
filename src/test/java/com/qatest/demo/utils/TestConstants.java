package com.qatest.demo.utils;

import java.util.Random;


public class TestConstants {

    public static String getRandomString(int length) {

        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static int getRandomInt(int min, int max) {

        Random random = new Random();
        int rand = random.nextInt(max) % (max - min + 1) + min;
        return rand;
    }


}