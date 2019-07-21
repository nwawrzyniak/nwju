package nwawsoft.util;

/**
 * Provides additional math functions.
 * Also provides functions to work with numerical native data types such as int or double.
 */
public class Math {

    /**
     * Rounds the specified double down (2.001 - 2.499 -> 2) or up (2.5 - 2.999 -> 3) and returns the result as an int.
     *
     * @param pDouble the double to round.
     * @return the corresponding int.
     */
    public static int roundDoubleToInt(final double pDouble) {
        int y;
        double decimal = pDouble % 10; // make leading part before decimal point exactly 1 digit long
        char digitAfterDecimalPoint = Double.toString(decimal).charAt(2);
        if (digitAfterDecimalPoint <= '4') {
            y = (int) pDouble;
        } else {
            y = (int) pDouble + 1;
        }
        return y;
    }

    /**
     * Returns the sum of all integer values from 1 to the specified upper bound, including 1 and the upper bound.
     * If x = 1 this will return 1.
     *
     * @param x upper bound.
     * @return the sum of all integers from 1 to x.
     */
    public static int sumUpToX(final int x) {
        if (x >= 0) {
            int sum = 0;
            for (int i = 1; i <= x; i++) {
                sum += i;
            }
            return sum;
        } else {
            DebugPrinter.dp("Upper bound has to be positive. Returning -1.");
            return -1;
        }
    }

    /**
     * Returns the sum of all integer values from a specified lower bound to a specified upper bound, including both
     * bounds. If x = y this will return x.
     *
     * @param x lower bound.
     * @param y upper bound.
     * @return the sum of all integers from x to y.
     */
    public static int sumFromXToY(final int x, final int y) {
        if (x >= 0 && y >= 0 && y >= x) {
            int sum = 0;
            for (int i = x; i <= y; i++) {
                sum += i;
            }
            return sum;
        } else {
            DebugPrinter.dp("Both bounds have to be positive integers and upper bound has to be bigger or equal than " +
                    "lower bound. Returning -1.");
            return -1;
        }
    }
}
