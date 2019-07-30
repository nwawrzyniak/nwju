package nwawsoft.util;

/**
 * Provides functions to work with arrays of type int.
 */
public class IntArrayFunctions {
    public static final int MODE_LINE_BREAK = 0;
    public static final int MODE_COMMA = 1;

    /**
     * Prints an array of Integers, one value per line.
     *
     * @param ints any int array
     * @param mode 0 if values should be printed one line per number, 1 if you want them comma separated.
     */
    public static void print(int[] ints, int mode) {
        boolean firstRun = true;
        if (ints != null) {
            for (int currentInt : ints) {
                if (mode == MODE_LINE_BREAK) {
                    System.out.println(currentInt);
                } else if (mode == MODE_COMMA) {
                    if (firstRun) {
                        System.out.print(currentInt);
                        firstRun = false;
                    } else {
                        System.out.print(", " + currentInt);
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("int[] ints must not be null.");
        }
    }

    /**
     * Returns whether two int arrays have no shared value.
     *
     * @param a an int array.
     * @param b another int array.
     * @return true if no entry was found in both arrays, else false.
     */
    public static boolean sharesNoEntry(int[] a, int[] b) {
        for (int entryA : a) {
            for (int entryB : b) {
                if (entryA == entryB) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns whether two int arrays have exactly the same values in the same order.
     *
     * @param a an int array.
     * @param b another int array.
     * @return true if a and b have the same contents in the same order, else false.
     */
    public static boolean isEqual(int[] a, int[] b) {
        if (a.length == b.length) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i]) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
