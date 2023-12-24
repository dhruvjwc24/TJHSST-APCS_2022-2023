// Name: 
// Date: 

import java.io.*;
import java.util.*;

public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      PrintWriter outputFile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         outputFile = new PrintWriter(new FileWriter("dictionaryOutput.txt"));
      }
      catch(Exception e)
      {
         System.out.println( e );
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      outputFile.println("ENGLISH TO SPANISH");
      outputFile.println(display(eng2spn));
   
      Map<String, Set<String>>spn2eng = reverse(eng2spn);
      outputFile.println("SPANISH TO ENGLISH");
      outputFile.println(display(spn2eng));
      outputFile.close();
      
      System.out.println("File created.");
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      TreeMap<String, Set<String>> map = new TreeMap<>();
      int count = 0;
      String english = "";
      String spanish = "";
      String curr = "";
      while(infile.hasNext()) {
         curr = infile.next();
         count+=1;
         if(count % 2 == 1) {
            english = curr;
            spanish = infile.next();
            count+=1;
         }
         
         add(map, english, spanish);
         // System.out.println(String.format("%s\t%s", english, spanish));
      }
      
      return map;
      
   }
   
   public static void add(Map<String, Set<String>> dictionary, String word, String translation)
   { 
      if(dictionary.containsKey(word)) {
         dictionary.get(word).add(translation);
      }
      
      else {
         Set<String> set = new TreeSet<String>();
         set.add(translation);
         dictionary.put(word, set);
      }

   }
   
   public static String display(Map<String, Set<String>> m)
   {
      Set<String> s = m.keySet();
      String s1 = "";
      Iterator<String> itr = s.iterator();
      while(itr.hasNext()) {
         String key = itr.next();
         Set<String> value = m.get(key);
         String val = value.toString();
         s1+= "\t" + key + " " + val + "\n";
         // System.out.println(s1);
         // s1 = "";
         // s1+= "\t" + key.replace(",", "") + ": " + val + "\n";
      }
      
      return s1;
   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      TreeMap<String, Set<String>> rev = new TreeMap<String, Set<String>>();
      Set<String> s = dictionary.keySet();
      Set<String> keys = new TreeSet<String>();
      Iterator<String> itr = s.iterator();
      String value = "";
      while(itr.hasNext()) {
         value = itr.next();
         keys = dictionary.get(value);
         for(String key : keys) {
            add(rev, key, value);
         }
      }
      
      
      return rev;
   }
}


   /********************
	FILE INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	FILE OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/