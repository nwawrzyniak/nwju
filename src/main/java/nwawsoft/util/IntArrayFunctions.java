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
    public static void print(final int[] ints, final int mode) {
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
    public static boolean sharesNoEntry(final int[] a, final int[] b) {
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
    public static boolean isEqual(final int[] a, final int[] b) {
        if (a != null && b != null) {
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
        } else {
            throw new IllegalArgumentException("Both integer array a and b must be non-null.");
        }
    }

    /**
     * Multiplies all the values of the specified int array.
     *
     * @param ints the int array to multiply the values of.
     * @return the product of all entries of ints.
     */
    public static int multiplyValues(final int[] ints) {
        int multipliedValue = 1;
        if (ints != null) {
            if (ints.length != 0) {
                for (int anInt : ints) {
                    multipliedValue *= anInt;
                    if (multipliedValue == 0) {
                        return 0;
                    }
                }
            } else {
                throw new IllegalArgumentException("int[] ints must contain at least one element.");
            }
        } else {
            throw new IllegalArgumentException("int[] ints must not be null.");
        }
        return multipliedValue;
    }
}
