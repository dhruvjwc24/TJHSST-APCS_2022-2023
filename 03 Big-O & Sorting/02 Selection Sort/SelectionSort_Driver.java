// Name:
// Date:

import java.util.*;
import java.io.*;

public class SelectionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
     //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      
      Selection.sort(array);   //students must write
      print(array);
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
      System.out.println();
      
      //Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      Selection.sort(arrayStr);  //students must write
      print(arrayStr);
      System.out.println();
      
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }
  
   public static void print(double[] a)
   {
      // for(int k = 0; k < a.length; k++)
         // System.out.println(a[k]);
      for(double temp: a)         //for-each
         System.out.print(temp+" ");
      System.out.println();
   }
  
   public static void print(Object[] papaya)
   {
      for(Object temp : papaya)     //for-each
         System.out.print(temp+" ");
   }
  
   public static boolean isAscending(double[] a)
   {
      boolean isGreater = true;
      int aLen = a.length;
      for(int i = 0; i < aLen-1; i++) {
         if(a[i+1] < a[i]) {
            isGreater = false;
         }
      }
      
      return isGreater;
   }
   
   @SuppressWarnings("unchecked")
   public static boolean isAscending(Comparable[] a)
   {
      boolean isGreater = true;
      int aLen = a.length;
      for(int i = 0; i < aLen-1; i++) {
         if(a[i+1].compareTo(a[i]) < 0) {
            isGreater = false;
         }
      }
      
      return isGreater;
   }
}
/////////////////////////////////////////////////////

class Selection
{
   public static void sort(double[] array)
   {
   
      int arrLen = array.length;
      int maxInd = 0;
      for(int i = 0; i < arrLen; i++) {
        maxInd = findMax(array, arrLen-i);
        swap(array, maxInd, arrLen-(i+1)); 
      }
   
   }
   
   // upper controls where the inner loop of the Selection Sort ends
   private static int findMax(double[] array, int upper)
   {
      int maxInd = 0;
      for(int i = 0; i < upper; i++) {
         if(array[i] > array[maxInd])
            maxInd = i;
      }
      return maxInd;
   
   }
   private static void swap(double[] array, int a, int b)
   {
   double temp = array[a];
   array[a] = array[b];
   array[b] = temp;
   
   
   }   	
   
	/*******  for Comparables ********************/
   @SuppressWarnings("unchecked")
   public static void sort(Comparable[] array)
   {
   
      int arrLen = array.length;
      int maxInd = 0;
      for(int i = 0; i < arrLen; i++) {
        maxInd = findMax(array, arrLen-i);
        swap(array, maxInd, arrLen-(i+1)); 
      }
   
   }
   
   @SuppressWarnings("unchecked")
   public static int findMax(Comparable[] array, int upper)
   {
      int maxInd = 0;
      for(int i = 0; i < upper; i++) {
         if(array[i].compareTo(array[maxInd]) > 0)
            maxInd = i;
      }
      return maxInd;
   
   }
   public static void swap(Object[] array, int a, int b)
   {
      Object temp = array[a];
      array[a] = array[b];
      array[b] = temp;
   }
}

