package com.gmail.alexandrtalan;

public class MathUtils {

    /**
     * Returns the greatest common divider of given two numbers
     *
     * @param firstNumber  - positive number
     * @param secondNumber - positive number
     * @return greatest common divider of two numbers
     */
    public int getGreatestCommonDivider(int firstNumber, int secondNumber) {
        if (secondNumber == 0) {
            return firstNumber;
        } else {
            return getGreatestCommonDivider(secondNumber, firstNumber % secondNumber);
        }
    }

    /**
     * Returns sum of digits of the given number
     *
     * @param number - positive number
     * @return the sum of digits of the given number
     */
    public int getSumOfDigits(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    /**
     * Checks if the given number is prime or not
     *
     * @param number
     * @return true - if number is prime, if not return false
     */
    public boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns sum of row: 1! - 2! + 3! - 4! + 5! - ... + n!
     *
     * @param n - positive number
     */
    public int getSumOfRow(int n) {
        if (n > 0) {
            int result = 1;
            for (int i = 2; i <= n; i++) {
                if (i % 2 != 0) {
                    result += factorial(i);
                } else {
                    result -= factorial(i);
                }
            }
            return result;
        }

        return 0;
    }

    private int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; ++i) {
            result *= i;
        }
        return result;
    }

    /**
     * Returns Fibonacci series of a specified length
     *
     * @param length - the length of the Fibonacci series
     * @return array filled with Fibonacci series
     */
    public int[] getFibonacciSeries(int length) {
        int a = 1, b = 1;
        int mas[] = new int[length];
        if (length > 0) {
            mas[0] = a;
            mas[1] = b;
            for (int i = 0; i < length - 2; i++) {
                mas[i + 2] = a + b;
                a = b;
                b = mas[i + 2];
            }
        }
        return mas;
    }

    /**
     * Returns array with prime numbers
     *
     * @param length - the length of prime numbers series
     * @return array filled with prime numbers
     */
    public int[] getPrimeSeries(int length) {
        int mas[] = new int[length];
        int pos = 0;
        for (int i = 2; true; i++) {
            if (pos == length) {
                break;
            } else if (isPrime(i)) {
                mas[pos] = i;
                pos++;
            }
        }
        return mas;
    }
}
