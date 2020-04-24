package nwawsoft.util.math;

import nwawsoft.util.tools.DebugPrinter;

/**
 * Provides additional math functions and functions to work with native numerical data types such as int or double.
 */
public class Math {

    /**
     * Rounds the specified double down (2.001 - 2.499 to 2) or up (2.5 - 2.999 to 3) and returns the result as an int.
     *
     * @param pDouble the double to round.
     * @return the corresponding int.
     */
    public static int roundDoubleToInt(final double pDouble) {
        if (pDouble > 0) {
            if (pDouble % 1 < 0.5) {
                return (int) pDouble;
            } else {
                return (int) pDouble + 1;
            }
        } else if (pDouble < 0) {
            if (pDouble % 1 > -0.5) {
                return (int) pDouble;
            } else {
                return (int) pDouble - 1;
            }
        } else {
            return 0;
        }
    }

    /**
     * Wrapper for roundDoubleToInt(double).
     * Rounds the specified double down (2.001 - 2.499 to 2) or up (2.5 - 2.999 to 3) and returns the result as an int.
     *
     * @param pDouble the double to round.
     * @return the corresponding int.
     */
    public static int round(final double pDouble) {
        return roundDoubleToInt(pDouble);
    }

    /**
     * Returns the sum of all integer values from 1 to the specified upper bound, including 1 and the upper bound.
     * If x = 1 this will return 1. If an error occurs this function returns the value -1.
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
     * bounds. If x = y this will return x. If an error occurs this function returns the value -1.
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
