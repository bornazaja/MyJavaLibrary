/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bzaja.myjavalibrary.util;

import java.util.Arrays;

/**
 *
 * @author Borna
 */
public final class PathableUtils {

    private PathableUtils() {

    }

    public static String[] pathablesToPaths(Pathable[] pathables) {
        return Arrays.stream(pathables).map(x -> x.getPath()).toArray(String[]::new);
    }
}
