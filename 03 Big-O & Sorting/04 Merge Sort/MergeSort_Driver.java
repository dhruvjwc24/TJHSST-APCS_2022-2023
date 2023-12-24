// Name: Dhruv Chandna
// Date: B5-07-22

import java.util.*;
import java.io.*;

public class MergeSort_Driver
{
   public static void main(String[] args) throws Exception
   {
      //Part 1, for doubles
      //double[] array = {3,1,4,1,5,9,2,6};    // small example array from the MergeSort handout
    // small example array from the MergeSort handout
      int n = (int)(Math.random()*50+10);
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random();

      MergeSort.sort(array);
   
      print(array);
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("oops!");
         
      //Part 2, for Comparables
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      MergeSort.sort(arrayStr);
      print(arrayStr);
      System.out.println();
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }

   
   public static void print(double[] a)   
   {                             
      for(double number : a)    
         System.out.print(number+" ");
      System.out.println();
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
  
   public static void print(Object[] peach)
   {
      for(Object obj : peach)    
         System.out.print(obj+" ");
      System.out.println();
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
/////////////////////////////////////////////
// 15 Oct 07
// MergeSort Code Handout
// from Lambert & Osborne, p. 482 - 485

class MergeSort
{
   private static final int CUTOFF = 10; // for small lists, recursion isn't worth it
  
   public static void sort(double[] array)
   { 
      double[] copyBuffer = new double[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1);
   }

   private static void mergeSortHelper(double[] array, double[] copyBuffer,
                                                      int low, int high)
   {  
      //if ( high - low  < CUTOFF )                  //switch to selection sort  when
         //SelectionLowHigh.sort(array, low, high);        //the list gets small enough 
      //else {
      if (low < high)
      {
         int middle = (low + high) / 2;
         mergeSortHelper(array, copyBuffer, low, middle);
         mergeSortHelper(array, copyBuffer, middle + 1, high);

         double[] array1 = Arrays.copyOfRange(array,low, middle+1);
         double[] array2 = Arrays.copyOfRange(array,middle+1,high+1);

         merge(array, array1, array2, copyBuffer, low);          
      }
   }
   
   public static void merge(double[] array, double[] array1, double[] array2, double[] copyBuffer, int low)
   {
      int i1 = 0;
      int i2 = 0;
      int index = 0;
      int arr1Len = array1.length;
      int arr2Len = array2.length;
      
      while(i1+i2 < (arr1Len + arr2Len)) {
         if(i1 < arr1Len && i2 < arr2Len) {
            if (array1[i1] < array2[i2]) {
               copyBuffer[index] = array1[i1];
               array[low+index] = array1[i1];
               i1+=1;
            }
            
            else {
               copyBuffer[index] = array2[i2];
               array[low+index] = array2[i2];
               i2+=1;
            }
            
            index+=1;
         }
         
         else {
            if(i1 == arr1Len) {
               copyBuffer[index] = array2[i2];
               array[low+index] = array2[i2];
               i2+=1;
            }
            else {
               copyBuffer[index] = array1[i1];
               array[low+index] = array1[i1];
               i1+=1;
            }
            index+=1;
         }
      }
   }	
/*******  for Comparables ********************/
   @SuppressWarnings("unchecked")
   public static void merge(Comparable[] array, Comparable[] array1, Comparable[] array2, Comparable[] copyBuffer, int low) 
   {   	
      int i1 = 0;
      int i2 = 0;
      int index = 0;
      int arr1Len = array1.length;
      int arr2Len = array2.length;
      
      while(i1+i2 < (arr1Len + arr2Len)) {
         if(i1 < arr1Len && i2 < arr2Len) {
            if (array1[i1].compareTo(array2[i2]) < 0) {
               copyBuffer[index] = array1[i1];
               array[low+index] = array1[i1];
               i1+=1;
            }
            
            else {
               copyBuffer[index] = array2[i2];
               array[low+index] = array2[i2];
               i2+=1;
            }
            
            index+=1;
         }
         
         else {
            if(i1 == arr1Len) {
               copyBuffer[index] = array2[i2];
               array[low+index] = array2[i2];
               i2+=1;
            }
            else {
               copyBuffer[index] = array1[i1];
               array[low+index] = array1[i1];
               i1+=1;
            }
            index+=1;
         }
      }
   }	
      
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static void sort(Comparable[] array)
   { 
      Comparable[] copyBuffer = new Comparable[array.length];
      mergeSortHelper(array, copyBuffer, 0, array.length - 1);
   }

   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low, high		bounds of subarray
      middle			midpoint of subarray  */
   @SuppressWarnings("unchecked")
   private static void mergeSortHelper(Comparable[] array, Comparable[] copyBuffer, int low, int high)
   {
     if (low < high)
      {
         int middle = (low + high) / 2;
         mergeSortHelper(array, copyBuffer, low, middle);
         mergeSortHelper(array, copyBuffer, middle + 1, high);

         Comparable[] array1 = Arrays.copyOfRange(array,low, middle+1);
         Comparable[] array2 = Arrays.copyOfRange(array,middle+1,high+1);

         merge(array, array1, array2, copyBuffer, low);          
      }
   }
}
      

/***************************************************
This is the extension.  You will have to uncomment Lines 89-90 above.
***************************************************/

/***
class SelectionLowHigh
{
   public static void sort(double[] array, int low, int high)
   {  
    
      int arrLen = array.length;
      int maxInd = 0;
      for(int i = low; i < arrLen; i++) {
        maxInd = findMax(array, arrLen-i);
        swap(array, maxInd, arrLen-(i+1)); 
      }
 
    
   }
   private static int findMax(double[] array, int low, int upper)
   {
      int maxInd = 0;
      for(int i = low; i < upper; i++) {
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
   
}
***/
