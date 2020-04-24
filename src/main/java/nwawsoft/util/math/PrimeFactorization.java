package nwawsoft.util.math;

import nwawsoft.util.datastructures.IntList;

/**
 * WIP, not done yet!
 */
public class PrimeFactorization {
    /**
     * Tests whether a number has a given divisor.
     *
     * @param number  the number to test
     * @param divisor the number to divide by
     * @return true if number % divisor = 0
     */
    private static boolean dividesWithoutRemainder(int number, int divisor) {
        return number % divisor == 0;
    }

    /**
     * Uses Direct Search Factorization with trial divisions to find all prime factors of a given
     * number.
     *
     * @param number a positive number to factorize
     * @return an int array containing all prime factors of the number in ascending order
     */
    public static int[] primeFactorize(int number) {
        if (number >= 0) {
            int[] primes;
            if (number == 0 || number == 1 || PrimeNumberFunctions.isPrime(number)) { // case:
                // input already output
                primes = new int[1];
                primes[0] = number;
                return primes;
            }
            IntList divisors = new IntList(); // case: input no prime. Factorize.
            divisors.toFirst();
            int upperBound = number / 2;
            for (int i = 2; i <= upperBound; i++) {
                if (PrimeNumberFunctions.isPrime(i)) {
                    if (dividesWithoutRemainder(number, i)) {
                        divisors.append(i);
                    }
                }
            } // end of trial divisions
            int productOfValues = IntList.multiplyValues(divisors);
            if (number == productOfValues) { // case: primes are complete
                // (and every single one is unique!)
                primes = new int[divisors.getLength()];
                int i = 0;
                divisors.toFirst();
                while (divisors.hasAccess()) {
                    primes[i] = (Integer) divisors.getObject();
                    i++;
                    divisors.next();
                }
                return primes;
            } else { // case: some prime appears multiple times in the factorization
                // ToDo
            }
            return null;
        } else {
            throw new IllegalArgumentException("number must be positive.");
        }
    }
}
