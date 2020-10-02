package com.nwawsoft.util.math;

import com.nwawsoft.util.exceptions.NoMorePrimesFoundException;

import java.util.Random;

/**
 * Supplies functions to generate or check prime numbers.
 */
public class PrimeNumberFunctions {
    private static final Random rand = new Random();

    /**
     * Returns the next prime on the number line after any given number.
     *
     * @param number any number.
     * @return the prime after number on the number line.
     */
    public static int nextPrime(final int number) {
        if (number >= 0) {
            int checkNumber = number + 1;
            while (!isPrime(checkNumber)) {
                checkNumber++;
            }
            return checkNumber;
        } else {
            return 2;
        }
    }

    /**
     * Returns whether a number is prime or not.
     *
     * @param number any number.
     * @return true if number is prime. Else false.
     */
    public static boolean isPrime(final int number) {
        if (number < 2) {
            return false;
        }
        int checkDivisionBy = 2;
        while (checkDivisionBy < number) {
            if (number % checkDivisionBy == 0) {
                return false;
            } else {
                checkDivisionBy++;
            }
        }
        return true;
    }

    /**
     * Returns whether a number is prime or not.
     *
     * @param number any number.
     * @return true if number is prime. Else false.
     */
    public static boolean isPrime(final long number) {
        if (number < 2) {
            return false;
        }
        long checkDivisionBy = 2;
        while (checkDivisionBy < number) {
            if (number % checkDivisionBy == 0) {
                return false;
            } else {
                checkDivisionBy++;
            }
        }
        return true;
    }

    /**
     * Returns whether the specified number is a super prime number.
     * Super prime numbers are numbers which are prime and whose position in the list of primes got a prime index
     * aswell (starting from 1). Super primes are also known as higher-order primes or prime-indexed primes.
     *
     * @param number any number.
     * @return true if the number is super prime. Else false.
     */
    public static boolean isSuperPrime(final int number) {
        if (number < 2) {
            return false;
        }
        int checkDivisionBy = 2;
        while (checkDivisionBy < number) {
            if (number % checkDivisionBy == 0) {
                return false;
            } else {
                checkDivisionBy++;
            }
        }
        int[] primeOrder = allPrimesTo(number);
        int lastPosition = primeOrder.length;
        return isPrime(lastPosition);
    }

    /**
     * Wrapper for allPrimesFromTo(0, upperBound).
     *
     * @param upperBound the upper bound to search for primes up to.
     * @return all primes between 0 and upperBound.
     */
    public static int[] allPrimesTo(final int upperBound) {
        return allPrimesFromTo(0, upperBound);
    }

    /**
     * Returns all prime numbers between (and including) lowerBound and upperBound.
     *
     * @param lowerBound the lower bound to search for primes from.
     * @param upperBound the upper bound to search for primes up to.
     * @return all primes between (and including) lowerBound and upperBound.
     */
    public static int[] allPrimesFromTo(final int lowerBound, final int upperBound) {
        int[] primes = new int[upperBound + 1];
        int foundPrimes = 0;
        for (int i = lowerBound; i < upperBound + 1; i++) {
            if (isPrime(i)) {
                primes[foundPrimes] = i;
                foundPrimes++;
            }
        }
        int[] outputPrimeArray = new int[foundPrimes];
        if (foundPrimes >= 0) {
            System.arraycopy(primes, 0, outputPrimeArray, 0, foundPrimes);
        }
        return outputPrimeArray;
    }

    /**
     * Returns a list containing the first X primes.
     *
     * @param amount the desired amount of primes
     * @return an int array containing the first X primes, starting with 2.
     */
    public static int[] firstPrimes(final int amount) {
        if (amount > 0) {
            int[] primes = new int[amount];
            int primeCount = 0;
            for (int i = 2; primeCount < amount; i++) {
                if (isPrime(i)) {
                    primes[primeCount] = i;
                    primeCount++;
                }
            }
            return primes;
        } else {
            throw new IllegalArgumentException("amount must be greater than 0.");
        }
    }

    /**
     * Generates a random prime number between lowerBound and upperBound.
     *
     * @param lowerBound the lower bound to search for primes from.
     * @param upperBound the upper bound to search for primes up to.
     * @return a random prime number between lowerBound and upperBound.
     */
    public static int generatePrime(final int lowerBound, final int upperBound) {
        int randomNumber = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
        if (isPrime(randomNumber)) {
            return randomNumber;
        } else {
            return nextPrime(randomNumber);
        }
    }

    /**
     * Returns a prime number different from lastPrime between lowerBound and upperBound.
     *
     * @param lowerBound the lower bound to search for primes from.
     * @param upperBound the upper bound to search for primes up to.
     * @param lastPrime any prime within lowerBound and upperBound.
     * @param maxTries the maximum amount of tries to generate a different prime number.
     * @return a prime between lowerBound and upperBound which is different from lastPrime.
     * @throws NoMorePrimesFoundException if no different prime than lastPrime is found between lowerBound and
     * upperBound within maxTries tries.
     */
    public static int generateAnotherPrime(final int lowerBound, final int upperBound, final int lastPrime,
                                           final int maxTries) throws NoMorePrimesFoundException {
        int anotherPrimeNumber = generatePrime(lowerBound, upperBound);
        int tryCount = 0;
        while (anotherPrimeNumber == lastPrime && tryCount <= maxTries) {
            anotherPrimeNumber = generatePrime(lowerBound, upperBound);
            tryCount++;
            if (tryCount == maxTries && anotherPrimeNumber == lastPrime) {
                throw new NoMorePrimesFoundException();
            }
        }
        return anotherPrimeNumber;
    }

    /**
     * Returns an array containing all prime numbers from 2 to upperBound.
     *
     * @param upperBound any upper Bound. Must be greater than 1.
     * @return an array containing all prime numbers from 2 to upperBound.
     */
    public static int[] sieveOfEratosthenes(final int upperBound) {
        int[] values = new int[upperBound - 1];
        for (int i = 2; i <= upperBound - 1; i++) {
            values[i - 2] = i;
        }
        values[upperBound - 2] = upperBound;
        int counter = 2;
        while (counter < upperBound / 2 + 1) {
            values[2] = 0;
            for (int j = counter + 1; j < upperBound - 1; j++) {
                if (values[j] % counter == 0) {
                    values[j] = 0;
                }
            }
            counter++;
            while (values[counter] == 0) {
                counter++;
            }
        }
        int numberOfPrimes = 0;
        for (int i = 0; i < upperBound - 1; i++) {
            if (values[i] != 0) {
                numberOfPrimes++;
            }
        }
        int[] output = new int[numberOfPrimes];
        int posMarker = 0;
        for (int i = 0; i < upperBound - 1; i++) {
            if (values[i] != 0) {
                output[posMarker] = values[i];
                posMarker++;
            }
        }
        return output;
    }
}