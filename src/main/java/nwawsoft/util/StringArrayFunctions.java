package nwawsoft.util;

/**
 * Created by Ernst on 22.06.2017.
 */
public class StringArrayFunctions {
    /**
     * Checks whether a String array contains an exact String as an element.
     *
     * @param strings the array to look for the pattern in
     * @param pattern the pattern to look for
     * @return true if any element of strings is equal to pattern. Else false.
     */
    public static boolean contains(String[] strings, String pattern) {
        if (strings != null && pattern != null) {
            for (int i = 0; i < strings.length; i++) {
                if (strings[i].equals(pattern)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void printStringArray(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
    }
}
