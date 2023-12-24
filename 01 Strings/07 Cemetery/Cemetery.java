
// Name: B5-07-22
// Date: 9/16/2022
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

public class Cemetery {
   public static void main(String[] args) throws IOException {

      File file = new File("cemetery_short.txt");
      // File file = new File("cemetery.txt");
      // System.out.println("1 " + file.getName());

      int numEntries = countEntries(file);
      Person[] cemetery = readIntoArray(file, numEntries);
      // for (Person rip : cemetery) {
      // System.out.println(rip.toString());
      // }
      // see what you have

      for (int i = 0; i < cemetery.length; i++)
         System.out.println(cemetery[i]);

      int min = locateMinAgePerson(cemetery);
      int max = locateMaxAgePerson(cemetery);
      System.out.println("\nIn the St. Mary Magdelene Old Fish Cemetery --> ");
      System.out.println("Name of youngest person: " + cemetery[min].getName());
      System.out.println("Age of youngest person: " + cemetery[min].getAge());
      System.out.println("Name of oldest person: " + cemetery[max].getName());
      System.out.println("Age of oldest person: " + cemetery[max].getAge());
      // you may create other testing cases here

   }

   /*
    * Counts and returns the number of entries in File f.
    * Returns 0 if the File f is not valid.
    * Uses a try-catch block.
    * 
    * @param f -- the file object
    */
   public static int countEntries(File f) {
      int entries = 0;
      // System.out.println("In the func...");
      try {
         Scanner sc = new Scanner(f);
         // System.out.println("in the try");
         while (sc.hasNextLine()) {
            sc.nextLine();
            entries++;
         }
         sc.close();
      } catch (FileNotFoundException e) {
         System.out.println("INVALID FILE " + e.toString());
         entries = -1;
      }

      return entries;
   }

   /*
    * Reads the data from file f (you may assume each line has same allignment).
    * Fills the array with Person objects. If File f is not valid return null.
    * 
    * @param f -- the file object
    * 
    * @param num -- the number of lines in the File f
    */
   public static Person[] readIntoArray(File f, int num) {
      Person[] pArr = new Person[num];
      Pattern patternMatch;
      try {
         Scanner sc = new Scanner(f);
         String name;
         String birthDate;
         String strAge;
         double age;
         for (int i = 0; i < num; i++) {
            String line = sc.nextLine();
            name = line.substring(0, 25).trim();
            birthDate = line.substring(25, 37).trim();
            strAge = line.substring(36, 42).trim();

            if (strAge.contains("w")) {
               int wPos = strAge.indexOf("w");
               String ageInWeeks = strAge.substring(0, wPos);
               age = Double.parseDouble(ageInWeeks) / 52.1429;
            } else if (strAge.contains("d")) {
               int dPos = strAge.indexOf("d");
               String ageInDays = strAge.substring(0, dPos);
               age = Double.parseDouble(ageInDays) / 365.0;
            } else {
               age = Double.parseDouble(strAge);
            }
            // patternMatch = Pattern.compile("-?\\d+(\\.\\d+)?");
            // if (patternMatch.matcher(age).matches()) {
            pArr[i] = new Person(name, birthDate, age);
         }

      } catch (Exception e) {
         System.out.println("I HAVE CAUGHT THE... EXCEPTIONNNN:|" + e.toString());
      }
      return pArr;
   }

   /*
    * A helper method that instantiates one Person object.
    * 
    * @param entry -- one line of the input file.
    * This method is made public for gradeit testing purposes.
    * This method should not be used in any other class!!!
    */
   public static Person makeObjects(String entry) {
      return new Person("-1", "-1", 0.0);
   }

   /*
    * Finds and returns the location (the index) of the Person
    * who is the youngest. (if the array is empty it returns -1)
    * If there is a tie the lowest index is returned.
    * 
    * @param arr -- an array of Person objects.
    */
   public static int locateMinAgePerson(Person[] arr) {
      int minPos = 0;
      int i = 0;
      for (Person peep : arr) {
         i++;
      }

      for (int j = 1; j < i; j++) {
         if (Double.parseDouble(arr[j].getAge()) < Double.parseDouble(arr[minPos].getAge())) {
            minPos = j;
         }
      }
      return minPos;
   }

   /*
    * Finds and returns the location (the index) of the Person
    * who is the oldest. (if the array is empty it returns -1)
    * If there is a tie the lowest index is returned.
    * 
    * @param arr -- an array of Person objects.
    */
   public static int locateMaxAgePerson(Person[] arr) {
      int maxPos = 0;
      int i = 0;
      for (Person peep : arr) {
         i++;
      }

      for (int j = 1; j < i; j++) {
         if (Double.parseDouble(arr[j].getAge()) > Double.parseDouble(arr[maxPos].getAge())) {
            maxPos = j;
         }
      }
      return maxPos;
   }
}

class Person {
   // constant that can be used for formatting purposes
   private static final DecimalFormat df = new DecimalFormat("0.0000");
   /* private fields */
   private String name = "";
   private String burialDate = "";
   private String age = "";

   /*
    * a three-arg constructor
    * 
    * @param name, burialDate may have leading or trailing spaces
    * It creates a valid Person object in which each field has the leading and
    * trailing
    * spaces eliminated
    */
   public Person(String n, String bd, Double a) {
      name = n;
      burialDate = bd;
      age = df.format(a);
   }

   /*
    * any necessary accessor methods (at least "double getAge()" and
    * "String getName()" )
    * make sure your get and/or set methods use the same data type as the field
    */
   public String getAge() {
      return age;
   }

   public String getName() {
      return name;
   }

   /*
    * handles the inconsistencies regarding age
    * 
    * @param a = a string containing an age from file. Ex: "12", "12w", "12d"
    * returns the age transformed into year with 4 decimals rounding
    */
   public double calculateAge(String a) {
      return 0.0;
   }

   public String toString() {
      return String.format("%s, %s, %s", name, burialDate, age);
   }
}

/******************************************
 * 
 * John William ALLARDYCE, 17 Mar 1844, 2.9
 * Frederic Alex. ALLARDYCE, 21 Apr 1844, 0.17
 * Philip AMIS, 03 Aug 1848, 1.0
 * Thomas ANDERSON, 06 Jul 1845, 27.0
 * Edward ANGEL, 20 Nov 1842, 22.0
 * Lucy Ann COLEBACK, 23 Jul 1843, 0.2685
 * Thomas William COLLEY, 08 Aug 1833, 0.011
 * Joseph COLLIER, 03 Apr 1831, 58.0
 * 
 * In the St. Mary Magdelene Old Fish Cemetery -->
 * Name of youngest person: Thomas William COLLEY
 * Age of youngest person: 0.011
 * Name of oldest person: Joseph COLLIER
 * Age of oldest person: 58.0
 * 
 **************************************/