
// Name: B5-07-22    
// Date: 11/30/22
import java.util.*;

public class ListLab1 {
   public static void main(String[] args) {
      ListNode head = new ListNode("hello", null);
      head = new ListNode("foo", head);
      head = new ListNode("boo", head);
      head = new ListNode("nonsense", head);
      head = new ListNode("computer",
            new ListNode("science",
                  new ListNode("java",
                        new ListNode("coffee", head))));
      print(head);
      print(head);

      /**** uncomment the code below for ListLab1 Assignment ****/

      ListNode a = copyNode(head);
      System.out.println("The head has a value \"" + head.getValue() + "\" at " + head);
      System.out.println("The copy of head has a value of \"" + a.getValue() + "\" at " + a);
      //
      System.out.print("Copy the list: ");
      ListNode copy = copyList(head);
      print(copy);
      //
      System.out.print("The rest of the list: ");
      ListNode theRest = rest(copy);
      print(theRest);
      //
      System.out.println("First of the rest = " + first(theRest));
      System.out.println("Second of the rest = " + second(theRest));
      ListNode p = pointerToLast(theRest);
      System.out.println("Pointer to Last = " + p.getValue() + " at " + p);
      ListNode c = copyOfLast(theRest);
      System.out.println("Copy of Last = " + c.getValue() + " at " + c);
      //
      Scanner in = new Scanner(System.in);
      System.out.print("Insert what? ");
      String x = in.next();
      theRest = insertFirst(theRest, x);
      theRest = insertLast(theRest, x);
      print(theRest);
   }

   public static void print(ListNode head) {
      System.out.print("[");
      while (head != null) {
         System.out.print(head.getValue());
         head = head.getNext();
         if (head != null)
            System.out.print(", ");
      }
      System.out.println("]");
   }

   /* enter your code here */
   public static ListNode copyNode(ListNode n) {
      if (n == null)
         return null;
      return new ListNode(n.getValue(), n.getNext());
   }

   public static ListNode copyList(ListNode n) {
      if (n == null)
         return null;
      return new ListNode(n.getValue(), copyList(n.getNext()));
   }

   public static ListNode rest(ListNode n) {
      return (copyList(n.getNext()));
   }

   public static Object first(ListNode n) {
      return (n.getValue());
   }

   public static Object second(ListNode n) {
      return (n.getNext().getValue());
   }

   public static ListNode pointerToLast(ListNode n) {
      while (n.getNext() != null) {
         n = n.getNext();
      }
      return n;
   }

   public static ListNode copyOfLast(ListNode n) {
      n = pointerToLast(n);
      return new ListNode(n.getValue(), n.getNext());
   }

   public static ListNode insertFirst(ListNode n, Object x) {
      ListNode ln = new ListNode(x, n);
      return ln;
   }

   public static ListNode insertLast(ListNode n, Object x) {
      ListNode head = n;
      ListNode ln = new ListNode(x, null);
      while (n.getNext() != null) {
         n = n.getNext();
      }
      n.setNext(ln);
      return head;

   }

}

/*****************************************
 * 
 * [computer, science, java, coffee, nonsense, boo, foo, hello]
 * [computer, science, java, coffee, nonsense, boo, foo, hello]
 * The head has a value "computer" at ListNode@15db9742
 * The copy of head has a value of "computer" at ListNode@6d06d69c
 * Copy the list: [computer, science, java, coffee, nonsense, boo, foo, hello]
 * The rest of the list: [science, java, coffee, nonsense, boo, foo, hello]
 * First of the rest = science
 * Second of the rest = java
 * Pointer to Last = hello at ListNode@7852e922
 * Copy of Last = hello at ListNode@4e25154f
 * Insert what? p
 * [p, science, java, coffee, nonsense, boo, foo, hello, p]
 * 
 **********************************************/