/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
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
