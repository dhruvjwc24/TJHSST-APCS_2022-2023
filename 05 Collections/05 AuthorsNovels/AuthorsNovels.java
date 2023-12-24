//  Name: Dhruv Chandna     Date: 01/13/22

import java.io.*;
import java.util.*;

public class AuthorsNovels {
   public static void main(String[] args) throws IOException {
      /* test the AuthorEntry object */
      AuthorEntry a = new AuthorEntry("Aaaa");
      System.out.println("name: " + a.getName());
      System.out.println("AuthorEntry.toString(): " + a);
      AuthorEntry b = new AuthorEntry("Dd", "y");
      System.out.println("name: " + b.getName());
      b.addNovel("z");
      b.addNovel("y");
      b.addNovel("x");
      System.out.println("AuthorEntry.toString(): " + b);
      System.out.println(b.compareTo(a)); // 3
      System.out.println(a.compareTo(b)); // -3

      // /* start the lab */
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim() + ".txt";
      Scanner inputFile = new Scanner(new File(inFileName));
      System.out.print("\nEnter output file name: ");
      String outFileName = keyboard.nextLine().trim();
      AuthorList authors = readAndMakeTheList(inputFile);
      outFileName = "authorsNovelsOut.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
      outputFile.println(authors.toString());
      inputFile.close();
      outputFile.close();
      System.out.println("Done.");
   }

   public static AuthorList readAndMakeTheList(Scanner inputFile) {
      AuthorList theList = new AuthorList();
      while (inputFile.hasNextLine()) {
         theList.readOneLine(inputFile.nextLine());
      }
      return theList;
   }
}

class AuthorList extends ArrayList<AuthorEntry> {
   /** you get an ArrayList for free **/
   public AuthorList() {
      super();
   }

   /**
    * extracts the author and book from oneLine.
    * calls addAuthorEntry
    */
   public void readOneLine(String oneLine) {
      String[] line = oneLine.split(", ");
      addAuthorEntry(line[0], line[1]);
   }

   /**
    * use a listIterator. Needs to call .previous()
    * either inserts a new AuthorEntry object, or
    * adds a novel to a previous AuthorEntry object,
    * in alphabetic order
    */
   public void addAuthorEntry(String name, String book) {
      ListIterator<AuthorEntry> it = this.listIterator();
      AuthorEntry a = new AuthorEntry(name, book);
      
      if(this.size() == 0) {
         this.add(a);
         return;
      }
      
      for(int i = 0; i < this.size(); i++) {
         if(this.get(i).getName().compareTo(a.getName()) == 0) {
            this.get(i).addNovel(book);
            return;
         }
         
         else if(a.getName().compareTo(this.get(i).getName()) < 0) {
            this.add(i, a);
            return;
         }
      }
      this.add(a);
      
   }

   public String toString() {
      String outString = "";
      for(AuthorEntry a : this) {
         outString += a.toString() + "\n";
      }
      return outString;
   }
}

class AuthorEntry implements Comparable<AuthorEntry> {
   // fields
   private String name;
   private ArrayList<String> novels;

   // two constructors. argument n may be in lowercase.
   public AuthorEntry(String n) {
      name = n.toUpperCase();
      novels = new ArrayList<>();
   }

   public AuthorEntry(String n, String book) {
      name = n.toUpperCase();
      novels = new ArrayList<>();
      novels.add(book);
   }

   /**
    * appends book to novels, but only if it is not already in that list.
    */
   public void addNovel(String book) {
      boolean contains = false;
      for (String nov : novels) {
         if (nov == book)
            contains = true;
      }
      if (contains == false)
         novels.add(book);
   }

   /** two standard accessor methods */
   public String getName() {
      return name;
   }

   public ArrayList<String> getNovels() {
      return novels;
   }

   /**
    * pre: name is not an empty string. novels might be an empty ArrayList.
    * uses: either a for-each loop or an iterator
    * post: returns a string representation of this AuthorEntry in the format as
    * shown on each line of the output file.
    */
   public String toString() {
      if (novels == null)
         return name;

      String output = name + ": ";

      for (String nov : novels) {
         output = output + nov + ", ";
      }
      return output.substring(0, output.length() - 2);

   }

   public int compareTo(AuthorEntry aut) {
      return name.compareTo(aut.getName());
   }

}

/********************
 * Extension
 * // class Author extends ArrayList<String> implements Comparable<Author>
 * // {
 * // }
 * 
 * 
 * /********************** SAMPLE RUN ********************************
 * name: AAAA
 * novels: []
 * toString(): AAAA
 * name: DD
 * novels: [y, z, x]
 * toString(): DD: y, z, x
 * 3
 * -3
 * 
 * Enter input file name: infile2
 * Done.
 * 
 **********************************************************/
/********
 * Output file for infile2:
 * 
 * DOSTOEVSKI: Crime and Punishment, The Possessed, The Brothers Karamazov, The
 * Grand Inquisitor
 * FLAUBERT: Madame Bovary, A Simple Heart, Memoirs of a Madman, Sentimental
 * Education
 * STENDHAL: The Red and the Black
 * TOLSTOY: Anna Karenina, War and Peace, The Death of Ivan Illyich, The
 * Kreutzer Sonata
 * 
 */