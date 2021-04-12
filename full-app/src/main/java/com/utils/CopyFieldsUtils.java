package com.utils;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author avinash.a.mishra
 */
public class CopyFieldsUtils {

    public static void copyAllProperties(Object dest, Object orig) {
        try {
            PropertyUtils.copyProperties(dest, orig);

        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
