// Name:
// Date:

import java.util.*;

import org.w3c.dom.Node;

import java.io.*;

public class Josephus {
   private static String WINNER = "Josephus";

   public static void main(String[] args) throws FileNotFoundException {
      /* Josephus Part I, insert() and print() */
      ListNode last = null;
      // DEBUG: set a breakpoint here and see if the circle is correctly made
      last = insert(last, "B");
      last = insert(last, "C");
      last = insert(last, "D");
      last = insert(last, "E");
      last = insert(last, "F");
      print(last);

      /* Josephus Part II */
      /* run numberCircle first with J_numbers.txt */
      Scanner sc = new Scanner(System.in);
      System.out.print("How many names (2-20)? ");
      int n = Integer.parseInt(sc.next());
      System.out.print("How many names to count off each time?");
      int countOff = Integer.parseInt(sc.next());
      ListNode winningPos = numberCircle(n, countOff, "J_numbers.txt");
      System.out.println(winningPos.getValue() + " wins!");

      /* run josephusCircle next with J_names.txt */
      System.out.println("\n **** Now start all over. **** \n");
      System.out.print("Where should " + WINNER + " stand? ");
      int winPos = Integer.parseInt(sc.next());
      ListNode theWinner = josephusCircle(n, countOff, "J_names.txt", winPos);
      System.out.println(theWinner.getValue() + " wins!");
   }

   public static ListNode numberCircle(int n, int countOff, String filename) throws FileNotFoundException {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      print(p);
      // while (p.getNext() != null) {
      // p = countingOff(p, countOff, n);
      // print(p);
      // }
      while (p.getNext() != null) {
         p = countingOff(p, countOff, n);
         print(p);
      }

      return p;
   }

   public static ListNode josephusCircle(int n, int countOff, String filename, int winPos)
         throws FileNotFoundException {
      ListNode p = null;
      p = readNLinesOfFile(n, new File(filename));
      while (p.getNext() != null) {
         replaceAt(p, WINNER, winPos);
         p = countingOff(p, countOff, n);
         print(p);
      }

      return p;
   }

   /*
    * reads the names, calls insert(), builds the linked list.
    * DEBUG: set a breakpoint in this method and see if the data is read into the
    * circle
    */
   public static ListNode readNLinesOfFile(int n, File f) throws FileNotFoundException {
      Scanner scnr = new Scanner(f);
      ListNode ln = new ListNode(scnr.nextLine(), null);
      ln.setNext(ln);
      for (int i = 1; i < n; i++) {
         ln = insert(ln, scnr.nextLine());
      }
      return ln;
   }

   /*
    * helper method to build the list. Creates the node, then
    * inserts it in the circular linked list.
    */
   public static ListNode insert(ListNode p, Object obj) {

      ListNode temp = p;
      ListNode node = new ListNode(obj, temp);

      if (p == null) {
         node = new ListNode(obj, null);
         p = node;
         p.setNext(p);
      } else {
         node = new ListNode(obj, p.getNext());
         p.setNext(node);
         p = node;
      }

      // node = p.getNext();
      // p.setNext(node);
      // Object initialValue = temp.getValue();
      // while (p.getNext().getValue() != initialValue) {
      // p = p.getNext();
      // }

      return p;

      // if (p == null) {
      // return node;
      // } else {
      // Object initialValue = p.getValue();
      // if(p.getValue() == initialValue) {
      // p.setNext(node);
      // }
      // else {
      // while (p.getNext().getValue() != initialValue)
      // p = p.getNext();
      // }
      // p.setNext(node);
      // }
      // return temp;
   }

   /*
    * Runs a Josephus game, counting off and removing each name. Prints after each
    * removal.
    * Ends with one remaining name, who is the winner.
    * DEBUG: set a breakpoint in this method and see if the counting off is correct
    */
   public static ListNode countingOff(ListNode p, int count, int n) {
      ListNode temp = p;
      temp = remove(p, count);
      return temp;
   }

   /*
    * removes the node after counting off count-1 nodes.
    */
   public static ListNode remove(ListNode p, int count) {
      Object initialValue = p.getValue();
      ListNode temp = p;
      ListNode node = null;
      int counter = 1;
      int count2 = 0;
      p = p.getNext();
      while (p.getValue() != initialValue) {
         p = p.getNext();
         counter++;
      }
      if (counter == 1)
         return new ListNode(temp.getValue(), null);
      else if (counter == 2)
         return new ListNode(temp.getNext().getValue(), null);

      count = count % counter + 1;
      count2 = count;

      p = temp;

      for (int i = 0; i < count - 2; i++) {
         if (p.getNext() != null)
            p = p.getNext();
      }
      p.setNext(p.getNext().getNext());
      p = p.getNext();
      count += 1;
      while (count <= counter) {
         node = insert(node, p.getValue());
         p = p.getNext();
         count++;
      }
      int i = 0;
      while (i < count2 - 1) {
         node = insert(node, temp.getValue());
         temp = temp.getNext();
         i++;

      }
      return node;
   }

   /*
    * prints the circular linked list.
    */
   public static void print(ListNode p) {
      Object initialValue = p.getValue();
      ListNode temp = p;
      // ListNode temp = p;
      if (p.getNext() == null)
         System.out.println(p.getValue());
      else {
         p = p.getNext();
         while (p.getValue() != initialValue) {
            System.out.print(p.getValue() + " ");
            p = p.getNext();
         }

         System.out.println(temp.getValue());
      }

   }

   /*
    * replaces the value (the String) at the winning node.
    */
   public static void replaceAt(ListNode p, Object obj, int pos) {

      for (int i = 0; i < pos; i++)
         p = p.getNext();
      p.setValue(obj);

   }
}
/**********************************************************
 * 
 * B C D E F
 * How many names (2-20)? 5
 * How many names to count off each time? 2
 * 1 2 3 4 5
 * 3 4 5 1
 * 5 1 3
 * 3 5
 * 3
 * 3 wins!
 **** 
 * Now start all over. ****
 * 
 * Where should Josephus stand? 3
 * Michael Hannah Josephus Ruth Matthew
 * Josephus Ruth Matthew Michael
 * Matthew Michael Josephus
 * Josephus Matthew
 * Josephus
 * Josephus wins!
 * 
 * ----jGRASP: operation complete.
 * 
 **************************************************/
