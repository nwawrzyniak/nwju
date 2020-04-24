package nwawsoft.util.math;

import java.util.Random;

public class PrimeNumberFunctions {
    private static Random rand = new Random();

    public static int nextPrime(int pNumber) {
        int checkNumber = pNumber + 1;
        while (!isPrime(checkNumber)) {
            checkNumber++;
        }
        return checkNumber;
    }

    public static boolean isPrime(int pNumber) {
        if (pNumber < 2) {
            return false;
        }
        int checkDivisionBy = 2;
        while (checkDivisionBy < pNumber) {
            if (pNumber % checkDivisionBy == 0) {
                return false;
            } else {
                checkDivisionBy++;
            }
        }
        return true;
    }

    public static boolean isPrime(long pNumber) {
        if (pNumber < 2) {
            return false;
        }
        long checkDivisionBy = 2;
        while (checkDivisionBy < pNumber) {
            if (pNumber % checkDivisionBy == 0) {
                return false;
            } else {
                checkDivisionBy++;
            }
        }
        return true;
    }

    public static boolean isSuperPrime(int pNumber) {
        if (pNumber < 2) {
            return false;
        }
        int checkDivisionBy = 2;
        while (checkDivisionBy < pNumber) {
            if (pNumber % checkDivisionBy == 0) {
                return false;
            } else {
                checkDivisionBy++;
            }
        }
        // STELLE AUCH PRIME?
        int[] primeOrder = allPrimesTo(pNumber);
        int lastPosition = primeOrder.length;
        if (isPrime(lastPosition)) {
            return true;
        }
        return false;
    }

    public static int[] allPrimesTo(int pHigherBound) {
        int[] primes = new int[pHigherBound + 1]; // ToDo: Remove this ugly big array. List?
        int foundPrimes = 0;
        for (int i = 0; i < pHigherBound + 1; i++) {
            if (isPrime(i)) {
                primes[foundPrimes] = i;
                foundPrimes++;
            }
        }
        int[] outputPrimeArray = new int[foundPrimes];
        for (int i = 0; i < foundPrimes; i++) {
            outputPrimeArray[i] = primes[i];
        }
        return outputPrimeArray;
    }

    // ToDo: overloaded method / merge with other method and parametrize
    public static int[] allPrimesFromTo(int pLowerBound, int pHigherBound) {
        int[] primes = new int[pHigherBound + 1];
        int foundPrimes = 0;
        for (int i = pLowerBound; i < pHigherBound + 1; i++) {
            if (isPrime(i)) {
                primes[foundPrimes] = i;
                foundPrimes++;
            }
        }
        int[] outputPrimeArray = new int[foundPrimes];
        for (int i = 0; i < foundPrimes; i++) {
            outputPrimeArray[i] = primes[i];
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

    public static int generatePrime(int pLowerBound, int pHigherBound) {
        int randomNumber = rand.nextInt(pHigherBound - pLowerBound + 1) + pLowerBound;
        if (isPrime(randomNumber)) {
            return randomNumber;
        } else {
            return nextPrime(randomNumber);
        }
    }

    public static int generateAnotherPrime(int pLowerBound, int pHigherBound, int lastPrime) {
        int anotherPrimeNumber = generatePrime(pLowerBound, pHigherBound);
        while (anotherPrimeNumber == lastPrime) { // ToDo: Error handling against infinite loops
            // (if there only is one prime in the range)
            anotherPrimeNumber = generatePrime(pLowerBound, pHigherBound);
        }
        return anotherPrimeNumber;
    }

    public static int[] sieveOfEratosthenes(int pUpperBound) {
        int upperBound = pUpperBound;
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