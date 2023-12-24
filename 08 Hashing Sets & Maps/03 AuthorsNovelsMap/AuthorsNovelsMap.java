//  name:
// date:

import java.io.*;
import java.util.*;

public class AuthorsNovelsMap
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim()+".txt";
      Scanner inputFile = new Scanner(new File(inFileName));
      //System.out.print("\nEnter output file name: ");
      //String outFileName = keyboard.nextLine().trim();
   
      AuthorsMap authors = readAndMakeTheList(inputFile);
      PrintWriter outputFile = new PrintWriter(new FileWriter("authorsNovelsOut.txt"));
      outputFile.println( authors.toString() );
      inputFile.close(); 						
      outputFile.close();
      System.out.println("File created.");
   }
   
   public static AuthorsMap readAndMakeTheList(Scanner inputFile)
   {
      AuthorsMap theAuthors = new AuthorsMap();
      while(inputFile.hasNextLine())
      {
         theAuthors.readOneLine(inputFile.nextLine());
      }
      return theAuthors;
   }
}

class AuthorsMap extends TreeMap<String, Set<String>>
{
  /**   you get a TreeMap for free  **/
   // TreeMap<String, Set<String>> map = new HashMap<>;
   String name, book;
   
   public AuthorsMap() {
      super();
   }
    
   /** extracts the author and book from oneLine.
   
       calls addAuthorOrNovel      
      */
   public void readOneLine(String oneLine) 
   { 
      // String[] arr = oneLine.split(" ");
      name = oneLine.substring(0, oneLine.indexOf(" "));
      book = oneLine.substring(oneLine.indexOf(" ")+1, oneLine.length());
      addAuthorOrNovel(name, book);
   }
   
   /**  either inserts a new Author mapping, or updates a previous Author mapping
        */
   public void addAuthorOrNovel(String name, String book)
   {
      name = name.toUpperCase();
      if(containsKey(name)) {
         get(name).add(book);
      }
      
      else {
         Set<String> set = new TreeSet<String>();
         set.add(book);
         put(name, set);
      }
   }
   
   public String toString()
   {
      Set<String> s = this.keySet();
      String s1 = "";
      Iterator<String> itr = s.iterator();
      while(itr.hasNext()) {
         String key = itr.next();
         Set<String> value = this.get(key);
         String val = value.toString().replace("[", "").replace("]", "");
         s1+= key.toUpperCase().replace(",", "") + ": " + val + "\n";
      }
      
      return s1;
   }
}
   

/**********************  SAMPLE RUN  ********************************
   /******** Output file for infile2:
   
   DOSTOEVSKI: Crime and Punishment, The Possessed, The Brothers Karamazov, The Grand Inquisitor
   FLAUBERT: Madame Bovary, A Simple Heart, Memoirs of a Madman, Sentimental Education
   STENDHAL: The Red and the Black
   TOLSTOY: Anna Karenina, War and Peace, The Death of Ivan Illyich, The Kreutzer Sonata
   
    **********************************/