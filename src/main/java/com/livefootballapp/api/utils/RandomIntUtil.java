package com.livefootballapp.api.utils;

public class RandomIntUtil {
    /**
     * Method to get a random integer
     *
     * @param range
     * @return
     */
    public static int getRandomInt(int range) {
        return (int) (Math.random() * range);
    }
}
