/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bzaja.myjavalibrary.util;

/**
 *
 * @author Borna
 */
public final class CommonUtils {

    private CommonUtils() {

    }

    public static void runIfMatchesObject(Object source, Object target, Runnable runnable) {
        if (source.equals(target)) {
            runnable.run();
        }
    }
}
