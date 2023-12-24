// Name: B5-07-22
// Date: 9/26/22
// INCLUDING BOTH EXTENSIONS

import java.util.*;
import java.lang.Math;

public class Permutations {
   public static int numSuperPrimes = 0;

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      System.out.print("\nHow many digits? ");
      int n = sc.nextInt();
      // leftRight("", n);
      // oddDigits("", n);

      superprime(n);
      System.out.println(String.format("Number of Super Primes Generated --> %d", numSuperPrimes));

      // if(count==0)
      // System.out.println("no superprimes");
      // else
      // System.out.println("Count is "+count);
   }

   /**
    * Builds all the permutations of a string of length n containing Ls and Rs
    * 
    * @param s A string
    * @param n An postive int representing the length of the string
    */
   public static void leftRight(String s, int n) {
      if (s.length() == n) {
         System.out.println(s);
      } else {
         leftRight(s + "L", n);
         leftRight(s + "R", n);
      }
   }

   /**
    * Builds all the permutations of a string of length n containing odd digits
    * 
    * @param s A string
    * @param n A postive int representing the length of the string
    */
   public static void oddDigits(String s, int n) {
      if (s.length() == n) {
         System.out.println(s);
      } else {
         oddDigits(s + "1", n);
         oddDigits(s + "3", n);
         oddDigits(s + "5", n);
         oddDigits(s + "7", n);
         oddDigits(s + "9", n);
      }
   }

   /**
    * Builds all combinations of a n-digit number whose value is a superprime
    * 
    * @param n A positive int representing the desired length of superprimes
    */
   public static void superprime(int n) {
      recur(2, n); // try leading 2, 3, 5, 7, i.e. all the single-digit primes
      recur(3, n);
      recur(5, n);
      recur(7, n);
   }

   /**
    * Recursive helper method for superprime
    * 
    * @param k The possible superprime
    * @param n A positive int representing the desired length of superprimes
    */
   private static void recur(int k, int n) {
      String kStr = Integer.toString(k);
      if (kStr.length() == n && isPrime(k)) {
         System.out.println(k);
         numSuperPrimes++;

      }

      if (isPrime(k)) {
         for (int i = 1; i < 10; i++) {
            if (!(i == 5 || i % 2 == 0)) {
               recur(Integer.parseInt(kStr + Integer.toString(i)), n);
            }
         }
      }
   }

   /**
    * Determines if the parameter is a prime number.
    * 
    * @param n An int.
    * @return true if prime, false otherwise.
    */
   public static boolean isPrime(int n) {
      boolean isItPrime = true;
      for (int i = 3; i <= Math.sqrt(n); i += 2) {

         // condition for nonprime number
         if (n % i == 0) {
            isItPrime = false;
         }
      }
      return isItPrime;
   }
}
