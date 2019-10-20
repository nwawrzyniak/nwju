package nwawsoft.util;

/**
 * Provides functions to work with char arrays.
 */
public class CharArrayFunctions {
    /**
     * Prints an array of characters, one line each.
     * Wrapper for print(char[], boolean).
     *
     * @param chars an array of characters.
     */
    public static void print(final char[] chars) {
        print(chars, false);
    }

    /**
     * Prints an array of characters, one line each. If enumerated is true it will also show it's index values.
     *
     * @param chars an array of characters.
     * @param enumerated whether the print shall contain index numbers.
     */
    public static void print(final char[] chars, final boolean enumerated) {
        for (int i = 0; i < chars.length; i++) {
            if (enumerated) {
                System.out.println(i + ": " + chars[i]);
            } else {
                System.out.println(chars[i]);
            }
        }
    }
}
