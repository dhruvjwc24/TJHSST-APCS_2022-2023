// Name: B5-07-22
// Date: 9/26/22

import java.util.*;

public class Fibonacci {
    public static void main(String[] args) {
        long start, end, fib; // why long?
        double time;
        int lastFibNumber = 43;
        int[] fibNumber = { 1 };
        System.out.println("\tFibonacci\tBy Iteration\tTime\tby Recursion\t Time");
        for (int n = fibNumber[0]; n <= lastFibNumber; n++) {
            start = System.nanoTime();
            fib = fibIterate(n);
            end = System.nanoTime();
            time = (end - start) / 1000.;
            System.out.print("\t" + n + "\t\t" + fib + "\t\t" + time);

            start = System.nanoTime();
            fib = fibRecur(n);
            end = System.nanoTime();
            time = (end - start) / 1000.;
            System.out.println("\t" + fib + "\t\t" + time);
        }
    }

    /**
     * Calculates the nth Fibonacci number by interation
     * 
     * @param n A variable of type int representing which Fibonacci number
     *          to retrieve
     * @returns A long data type representing the Fibonacci number
     */
    public static long fibIterate(int n) {

        long n1 = 1;
        long n2 = 1;
        long val = 1;
        if (n == 1 || n == 2) {
            return 1;
        }
        for (int i = 3; i <= n; i++) {
            val = n2;
            n2 = n1 + n2;
            n1 = val;
        }
        return n2;

    }

    /**
     * Calculates the nth Fibonacci number by recursion
     * 
     * @param n A variable of type int representing which Fibonacci number
     *          to retrieve
     * @returns A long data type representing the Fibonacci number
     */
    public static long fibRecur(int n) {
        if (n <= 2)
            return 1;
        return (fibRecur(n - 1) + fibRecur(n - 2));
    }
}