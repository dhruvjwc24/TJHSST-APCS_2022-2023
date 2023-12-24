
// Name: B5-07-22
// Date: 9/5/22
import java.util.*;
import java.io.*;

public class PigLatin {
   public static void main(String[] args) {
      // part_1_using_pig();

      part_2_using_piglatenizeFile();

      /* extension only */
      // String pigLatin = pig("What!?");
      // System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin)); // Yahwta!?
      // pigLatin = pig("{(Hello!)}!?");
      // System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //
      // {(Yaholle!)}
      // pigLatin = pig("\"McDonald???\"");
      // System.out.println("\n" + pigLatin + "\t\t" + pigReverse(pigLatin));//
      // "YaDcmdlano???"
   }

   public static void part_1_using_pig() {
      Scanner sc = new Scanner(System.in); // input from the keyboard
      while (true) {
         System.out.print("\nWhat word? ");
         String s = sc.next(); // reads up to white space
         if (s.equals("-1")) {
            System.out.println("Goodbye!");
            System.exit(0);
         }
         String p = pig(s);
         System.out.println(p);
      }
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUYaeiouy";

   public static String pig(String s) {
      if (s.length() == 0)
         return "";

      // remove and store the beginning punctuation
      String[] arrS = s.split("");
      String begPunct = "";
      String endPunctWrong = "";

      int i = 0;

      while (punct.contains(arrS[i])) {
         begPunct += arrS[i];
         i++;
      }

      i = s.length() - 1;
      while (punct.contains(arrS[i])) {
         endPunctWrong += arrS[i];
         i--;
      }

      String endPunctArr[] = endPunctWrong.split("");
      String endPunct = "";
      for (i = endPunctArr.length - 1; i >= 0; i--) {
         endPunct += endPunctArr[i];
      }

      String sNoPunct = "";
      int indxEndPunct = s.lastIndexOf(endPunct);
      if (endPunct.length() == 0) {
         indxEndPunct = s.length();
      }
      sNoPunct = s.substring(begPunct.length(), indxEndPunct);

      // START HERE with the basic case:
      int firstVowelIndx = 0;
      boolean vowelFound = false;
      String[] sNoPunctArr = sNoPunct.split("");
      i = 0;
      while (i < sNoPunctArr.length) {
         if (vowels.contains(sNoPunctArr[i])) {
            if ((((sNoPunctArr[i].equals("u") || sNoPunctArr[i].equals("U")) && !(i == 0))
                  && (sNoPunctArr[i - 1].equals("q") || sNoPunctArr[i - 1].equals("Q")))
                  || (i == 0 && (sNoPunctArr[i].equals("y") || sNoPunctArr[i].equals("Y")))) {
               i++;
               continue;
            } else {
               firstVowelIndx = i;
               vowelFound = true;
               break;
            }
         }
         i++;
      }
      String pigLatinString = "";
      if (vowelFound && firstVowelIndx == 0) {
         pigLatinString = begPunct + sNoPunct + "way" + endPunct;
         return pigLatinString;
      }

      else if (vowelFound && firstVowelIndx != 0) {
         // begPunct + ... + "ay" + endPunct
         // if first letter is uppercase, pig latinized word first letter is upper, and
         // the original is lowercase
         pigLatinString = sNoPunct.substring(firstVowelIndx) + sNoPunct.substring(0, 1).toLowerCase()
               + sNoPunct.substring(1, firstVowelIndx) + "ay";
         if (sNoPunct.substring(0, 1).toUpperCase().equals(sNoPunct.substring(0, 1))) {
            pigLatinString = pigLatinString.substring(0, 1).toUpperCase() + pigLatinString.substring(1);
            pigLatinString = begPunct + pigLatinString + endPunct;
         } else {
            pigLatinString = begPunct + pigLatinString + endPunct;
         }

         return pigLatinString;
      }

      else if (!vowelFound) {
         return "**** NO VOWEL ****";
      }

      return "";
   }

   public static void part_2_using_piglatenizeFile() {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile(fileNameIn, fileNameOut);
      System.out.println("Piglatin done!");
   }

   /******************************
    * piglatinizes each word in each line of the input file
    * precondition: both fileNames include .txt
    * postcondition: output a piglatinized .txt file
    ******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) {
      Scanner infile = null;
      try {
         infile = new Scanner(new File(fileNameIn));
      } catch (IOException e) {
         System.out.println("oops");
         System.exit(0);
      }

      PrintWriter outfile = null;
      try {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      } catch (IOException e) {
         System.out.println("File not created");
         System.exit(0);
      }
      // process each word in each line
      while (infile.hasNext()) {
         String text = infile.nextLine();
         // System.out.println(text);
         String[] textArr = text.split(" ");
         // System.out.println(Arrays.toString(textArr));
         String pigLatinTextArr[] = new String[textArr.length];
         String pigLatinText = "";
         String pigLatinWord = "";
         int i = 0;
         for (String word : textArr) {
            pigLatinWord = pig(word);
            pigLatinTextArr[i] = pigLatinWord;
            i++;
         }
         pigLatinText = String.join(" ", pigLatinTextArr);
         // System.out.println(pigLatinText);
         outfile.println(pigLatinText);
         // System.out.println(Arrays.toString(pigLatinTextArr));
         // System.out.println("\n");
      }

      outfile.close();
      infile.close();
   }

   /**
    * EXTENSION: Output each PigLatin word in reverse, preserving before-and-after
    * punctuation.
    */
   public static String pigReverse(String s) {
      if (s.length() == 0)
         return "";

      // remove and store the beginning punctuation
      String[] arrS = s.split("");
      String begPunct = "";
      String endPunctWrong = "";

      int i = 0;

      while (punct.contains(arrS[i])) {
         begPunct += arrS[i];
         i++;
      }

      i = s.length() - 1;
      while (punct.contains(arrS[i])) {
         endPunctWrong += arrS[i];
         i--;
      }

      String endPunctArr[] = endPunctWrong.split("");
      String endPunct = "";
      for (i = endPunctArr.length - 1; i >= 0; i--) {
         endPunct += endPunctArr[i];
      }

      String sNoPunct = "";
      int indxEndPunct = s.lastIndexOf(endPunct);
      if (endPunct.length() == 0) {
         indxEndPunct = s.length();
      }
      sNoPunct = s.substring(begPunct.length(), indxEndPunct);

      char[] sNoPunctArr = sNoPunct.toCharArray();
      String sNoPunctRev = "";
      boolean isFirstLetterUpper = sNoPunct.substring(0, 1).toUpperCase().equals(sNoPunct.substring(0, 1));

      for (i = sNoPunctArr.length - 1; i >= 0; i--) {
         sNoPunctRev += sNoPunctArr[i];
      }
      sNoPunctRev = sNoPunctRev.substring(0, sNoPunctRev.length() - 1)
            + sNoPunctRev.substring(sNoPunctRev.length() - 1, sNoPunctRev.length()).toLowerCase();
      if (isFirstLetterUpper) {
         sNoPunctRev = sNoPunctRev.substring(0, 1).toUpperCase() + sNoPunctRev.substring(1, sNoPunctRev.length());
      }
      sNoPunctRev = begPunct + sNoPunctRev + endPunct;

      return sNoPunctRev; // just to compile
   }
}