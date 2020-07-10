package com.nwawsoft.util.math;

import static com.nwawsoft.util.math.Math.getDivisors;

/**
 * Supplies functions to generate an int[] of highly composite numbers and/or print them.
 */
public class HighlyCompositeNumbers {
    /**
     * Prints all numbers between 0 and upperBound that have a higher amount of divisors than the numbers before.
     *
     * @param upperBound the last number to check
     */
    public static void printHCN(final int upperBound) {
        printHCN(0, upperBound);
    }

    /**
     * Prints all numbers that have a higher amount of divisors than the numbers before.
     * Only checks numbers between lowerBound and upperBound.
     * This function will only print HCNs after reaching the first real HCN in its range.
     * Keep in mind that the first few numbers could have a smaller amount of divisors than a number lower than
     * lowerBound and are therefore not guaranteed to be HCNs.
     *
     * @param lowerBound the number from where to start looking for HCNs
     * @param upperBound the last number to check
     */
    public static void printHCN(final int lowerBound, final int upperBound) {
        int maxDivisors = 0;
        int counter = 0;
        for (int i = lowerBound; i < upperBound; i++) {
            int currentDivisors = getDivisors(i);
            if (currentDivisors > maxDivisors) {
                counter++;
                maxDivisors = currentDivisors;
                System.out.println("HCN number " + counter + ": " + i + " (with " + maxDivisors + " divisors)");
            }
        }
    }

    /**
     * Endless HCN print.
     * Has to be manually interrupted.
     */
    public static void printHCN() {
        int maxDivisors = 0;
        int counter = 0;
        int i = 0;
        while (true) {
            int currentDivisors = getDivisors(i);
            if (currentDivisors > maxDivisors) {
                counter++;
                maxDivisors = currentDivisors;
                System.out.println("HCN number " + counter + ": " + i + " (with " + maxDivisors + " divisors)");
            }
            i++;
        }
    }

    /**
     * Returns an int[] with all highly composite numbers from 1 to upperBound.
     *
     * @param upperBound the upper bound to check to.
     * @return an int[] with size HCNAmount that contains all HCNs from 1 to upperBound.
     */
    public static int[] getHCNs(final int upperBound) {
        int[] collectorArray = new int[upperBound];
        int maxDivisors = 0;
        int counter = 0;
        for (int i = 1; i < upperBound; i++) {
            int currentDivisors = getDivisors(i);
            if (currentDivisors > maxDivisors) {
                collectorArray[counter] = i;
                counter++;
                maxDivisors = currentDivisors;
            }
        }
        int[] hcnArray = new int[counter];
        System.arraycopy(collectorArray, 0, hcnArray, 0, counter);
        return hcnArray;
    }
}
