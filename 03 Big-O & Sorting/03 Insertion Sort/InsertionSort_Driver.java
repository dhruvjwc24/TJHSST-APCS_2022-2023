 //Name:     
 //Date:

import java.util.*;
import java.io.*;

public class InsertionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
      //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      
      Insertion.sort(array);  //students write
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
   
      Insertion.sort(arrayStr);   //students write
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
      for(Object temp : papaya)    
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
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
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

//**********************************************************

class Insertion
{
   public static void sort(double[] array)
   { 
      int arrLen = array.length;
      double val = 0.0;
      for(int i = 1; i < arrLen; i++) {
         val = array[i];
         shift(array, i, val);
      }
   }
 
   private static int shift(double[] array, int index, double value)
   {
      int i = index-1;
      double temp = 0.0;
      while(i+1 > 0) {
         if(value < array[i]) {
            temp = array[i];
            array[i] = value;
            array[i+1] = temp;
         }
         value = array[i];
         i-=1;
      }
      
      return i;
      
   }
 
   @SuppressWarnings("unchecked")
   public static void sort(Comparable[] array)
   { 
      int arrLen = array.length;
      Comparable val = null;
      for(int i = 1; i < arrLen; i++) {
         val = array[i];
         shift(array, i, val);
      }
   }
   
   @SuppressWarnings("unchecked")
   private static int shift(Comparable[] array, int index, Comparable value)
   {
      int i = index-1;
      Comparable temp = null;
      while(i+1 > 0) {
         if(value.compareTo(array[i]) < 0) {
            temp = array[i];
            array[i] = value;
            array[i+1] = temp;
         }
         value = array[i];
         i-=1;
      }
      
      return i;
   }
}
