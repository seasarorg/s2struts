package org.seasar.struts.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

/**
 * @author Satoshi Kimura
 */
public abstract class RandomUtil {
    private static Random random = new Random();

    private static ThreadLocal primary = new ThreadLocal();

    protected RandomUtil() {
    }

    public static long randomLong() {
        init();
        long value = Math.abs(random.nextLong());
        if (isUsedValue(value)) {
            return randomLong();
        }
        return value;
    }

    public static String randomString() {
        return Long.toString(randomLong());
    }

    private static void init() {
        Collection values = (Collection)primary.get();
        if (values == null) {
            primary.set(new HashSet());
        }
    }

    private static boolean isUsedValue(long value) {
        Long val = new Long(value);
        Collection values = (Collection)primary.get();
        if (values.contains(val)) {
            return true;
        } else {
            values.add(val);
            return false;
        }
    }

}
