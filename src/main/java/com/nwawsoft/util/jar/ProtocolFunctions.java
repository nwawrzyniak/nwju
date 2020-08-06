package com.nwawsoft.util.jar;

/**
 * Provides functions which behave differently depending on the run environment (mainly IDE/direct execution vs. from
 * inside a '.jar' file).
 */
public class ProtocolFunctions {

    /**
     * Returns whether a class has been loaded from a '.jar'.
     *
     * @param o the context. To check if a class itself runs from within a '.jar' use 'this'.
     * @return true if o was loaded from a '.jar' file. Else false.
     */
    public static boolean isInJar(final Object o) {
        return o.getClass().getResource("").getProtocol().equals("jar");
    }
}
