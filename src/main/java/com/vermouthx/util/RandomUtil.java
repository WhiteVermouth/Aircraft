package com.vermouthx.util;

import java.util.concurrent.ThreadLocalRandom;

public class RandomUtil {
    private RandomUtil() {

    }

    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
