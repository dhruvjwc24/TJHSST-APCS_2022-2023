// Name: B5-07-22
// Date: 10/9/22

import java.util.*;
import java.io.*;

public class AreaFill {
   public static void main(String[] args) {
      char[][] grid = null;
      String filename = null;
      Scanner sc = new Scanner(System.in);
      while (true) // what does this do?
      {
         System.out.print("Fill the Area of (-1 to exit): ");
         filename = sc.next();
         if (filename.equals("-1")) {
            sc.close();
            System.out.println("Good-bye");
            System.exit(0);
            return;
         }
         grid = read(filename);

         String theGrid = display(grid);
         System.out.println(theGrid);

         System.out.print("1-Fill or 2-Fill-and-Count: ");
         int choice = sc.nextInt();
         switch (choice) {
            case 1: {
               System.out.print("\nEnter ROW COL to fill from: ");
               int row = sc.nextInt();
               int col = sc.nextInt();
               fill(grid, row, col, grid[row][col]);
               System.out.println(display(grid));
               break;
            }

            case 2: {
               System.out.print("\nEnter ROW COL to fill from: ");
               int row = sc.nextInt();
               int col = sc.nextInt();
               int count = fillAndCount(grid, row, col, grid[row][col]);
               System.out.println(display(grid));
               System.out.println("count = " + count);
               System.out.println();
               break;
            }

            default:
               System.out.print("\nTry again! ");
         }

      }
   }

   /**
    * Reads the contents of the file into a matrix.
    * Uses try-catch.
    * 
    * @param filename The string representing the filename.
    * @returns A 2D array of chars.
    */
   public static char[][] read(String filename) {
      Scanner infile = null;
      try {
         infile = new Scanner(new File(filename));
      } catch (Exception e) {
         System.out.println("File not found");
         return null;
      }
      /* enter your code here */

      // Count rows and cols in text file to be able to initialize 2D array
      int numR = 0;
      int numC = 0;

      String line1 = infile.nextLine();
      String[] line1Arr = line1.split(" ");
      numR = Integer.parseInt(line1Arr[0]);
      numC = Integer.parseInt(line1Arr[1]);

      // Read text from file and add it to 2D array
      char[][] charArr = new char[numR][numC];
      int r = 0;

      while (infile.hasNextLine()) {
         int c = 0;
         String line = infile.nextLine();
         char[] lineArr = line.toCharArray();
         for (char myChar : lineArr) {
            // System.out.println(myChar);
            charArr[r][c] = myChar;
            c++;
         }
         r++;

      }
      return charArr;

   }

   /**
    * @param g A 2-D array of chars.
    * @returns A string representing the 2D array.
    */   
   public static String display(char[][] g) {
      String text = "";
      int row = g.length;
      int col = g[0].length;
      for (int r = 0; r < row; r++) {
         for (int c = 0; c < col; c++) {
            text += g[r][c];
         }
         text += "\n";
      }
      return text;
   }

   /**
    * Fills part of the matrix with a different character.
    * 
    * @param g  A 2D char array.
    * @param r  An int representing the row of the cell to be filled.
    * @param c  An int representing the column of the cell to be filled.
    * @param ch The char which is being replaced.
    */
   public static void fill(char[][] g, int r, int c, char ch) {
      int numRows = g.length;
      int numCols = g[0].length;
      
      if(r < 0 || c < 0 || r >= numRows || c >= numCols) return;
      
      if(g[r][c] == ch) {
         g[r][c] = '*';
         
         fill(g, r-1, c, ch);
         fill(g, r, c+1, ch);
         fill(g, r+1, c, ch);
         fill(g, r, c-1, ch);
      }
   }

   /**
    * Fills part of the matrix with a different character.
    * Counts as you go. Does not use a static variable.
    * 
    * @param g  A 2D char array.
    * @param r  An int representing the row of the cell to be filled.
    * @param c  An int representing the column of the cell to be filled.
    * @param ch The char which is being replaced.
    * @return An int representing the number of characters that were replaced.
    */
   public static int fillAndCount(char[][] g, int r, int c, char ch) {
      int numRows = g.length;
      int numCols = g[0].length;
      int count = 0;
      
      if(r < 0 || c < 0 || r >= numRows || c >= numCols) return count;
      
      if(g[r][c] == ch) {
         g[r][c] = '*';
         count+=1;
         
         return count + fillAndCount(g, r-1, c, ch) + fillAndCount(g, r, c+1, ch) + fillAndCount(g, r+1, c, ch) + fillAndCount(g, r, c-1, ch);
      }
      return 0;
   }
}