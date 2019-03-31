package nwawsoft.util;

public class IntArrayFunctions {
    public static final int MODE_LINE_BREAK = 0;
    public static final int MODE_COMMA = 1;

    /**
     * Prints an array of Integers, one value per line.
     *
     * @param ints any int array
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
            throw new IllegalArgumentException("ints shall not be null");
        }
    }

    public static boolean sharesNoEntry(int[] a, int[] b) {
        boolean foundMatch = false;
        for (int someA : a) {
            for (int someB : b) {
                if (someA == someB) {
                    foundMatch = true;
                }
            }
        }
        return (!foundMatch);
    }

    public static boolean isEqual(int[] a, int[] b) {
        boolean noError = true;
        if (a.length == b.length) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i]) {
                    noError = false;
                }
            }
        } else {
            noError = false;
        }
        return noError;
    }
}
