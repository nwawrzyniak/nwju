package nwawsoft.util.natives;

import nwawsoft.util.tools.DebugPrinter;

/**
 * Supplies functions that manipulate or work with information from String arrays.
 */
public class StringArrayFunctions {
    /**
     * Checks whether a String array contains an exact String as an element.
     *
     * Probably deprecated? There should be such a thing in the main Java library nowadays.
     *
     * @param strings the array to search through.
     * @param pattern the exact String to look for.
     * @return true if any element of []strings is equal to pattern. Else false.
     */
    public static boolean contains(final String[] strings, final String pattern) {
        if (strings != null && pattern != null) {
            for (String string : strings) {
                if (string.equals(pattern)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Prints an array of Strings, one line each.
     *
     * @param strings the String array to print.
     */
    public static void printStringArray(final String[] strings) {
        if (strings != null) {
            for (String string : strings) {
                System.out.println(string);
            }
        }
        else {
            DebugPrinter.dp("trying to print an uninitialized array.");
        }
    }
}
