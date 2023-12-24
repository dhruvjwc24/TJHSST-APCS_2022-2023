// Name: Dhruv Chandna
// Date: B5-07-22

import java.util.*;
import java.io.*;

public class MergeSort_Driver2
{
   public static void main(String[] args) throws Exception
   {
      //Part 1, for doubles
      double[] array1  ;   // small example array from the MergeSort handout
      double[] array2 ;
      double[] copyBuffer ;
            //int n = (int)(Math.random()*50+10);
      // double[] array = new double[n];
      // for(int k = 0; k < array.length; k++)
         // array[k] = Math.random();
     
        int low, medium, high;
        low = 2;
        medium = 4;
        high = 7; 
      double[] array =   {1,4,6,2,7,2,8,9, 11, 15}; 	
      array1 = Arrays.copyOfRange(array,low,medium+1);      
      array2 = Arrays.copyOfRange(array,medium+1,high);      
      copyBuffer = new double[array1.length+array2.length];


      print(array);   

      MergeSort.merge(array,array1, array2, copyBuffer, low);
  
      print(array1);   
      print(array2);     
      print(copyBuffer);
      print(array);   
   }

   
   public static void print(double[] a)   
   {                             
      for(double number : a)    
         System.out.print(number+" ");
      System.out.println();
   }
}
/////////////////////////////////////////////
// 15 Oct 07
// MergeSort Code Handout
// from Lambert & Osborne, p. 482 - 485

class MergeSort
{
   
   /* array				array that is being sorted
      copyBuffer		temp space needed during the merge process
      low				beginning of first sorted subarray
      middle			end of first sorted subarray
      middle + 1		beginning of second sorted subarray
      high				end of second sorted subarray   
   */


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
}
