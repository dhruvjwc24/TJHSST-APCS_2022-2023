// name:        date: 
import java.util.*;
import java.io.*;
public class Huffman
{
   public static Scanner keyboard = new Scanner(System.in);
   public static void main(String[] args) throws IOException
   {
      //Prompt for two strings 
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat message? ");
      String message = keyboard.nextLine();
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.next();
   
      huffmanize( message, middlePart );
   }
   
   public static void huffmanize(String message, String middlePart) throws IOException
   {
         //Make a frequency table of the letters
         HashMap<String, Integer> map = new HashMap<>();
         String[] arr = message.split("");
         HashSet<String> letters = new HashSet<String>();
         for(String s : arr) {
            letters.add(s);
         }
         
         int count = 0;
         for(String l : letters) {
            count = 0;
            for(String a : arr) {
               if(a.equals(l)) {
                  count++;
               }
            }
            
            map.put(l, count);
         }

         System.out.println(map);
         
      	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
   		//        node into a priority queue (or a min-heap). 
            
         PriorityQueue<HuffmanTreeNode> q = new PriorityQueue<>();
         
         for(String key : map.keySet()) {
            HuffmanTreeNode t = new HuffmanTreeNode(key, map.get(key));
            q.add(t);
         }
         
         System.out.println(q);
           
           
      	//Use the priority queue of nodes to build the Huffman tree
         HuffmanTreeNode root = new HuffmanTreeNode("*", 0);
         while(q.size()!=1) {
            HuffmanTreeNode c1 = q.remove();
            HuffmanTreeNode c2 = q.remove();
            root.setLeft(c1);
            root.setRight(c2);
            root.setFreq(c1.getFreq()+c2.getFreq());
            q.add(root);
            root = new HuffmanTreeNode("*", 0);
         }
         
         HuffmanTreeNode t = q.remove();
         getCode(t, "");
         
      	//Process the string letter-by-letter and search the tree for the 
   		//       letter. It's recursive. As you recur, build the path through the tree, 
   		//       where going left is 0 and going right is 1.
         //System.out.println the binary message 
      	//Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
         //System.out.println the scheme from the tree--needs a recursive helper method
      	//Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")
         
            
   }
   
   public static void getCode(HuffmanTreeNode t, String code) {
      if(t == null) return;
      if(!t.getLetter().equals("*")) {
         System.out.print(t.getLetter() + code);
         System.out.println();
      }
      
      getCode(t.getLeft(),code + "0");
      getCode(t.getRight(), code + "1");
   }
}
	/*
	  * This tree node stores two values.  Look at TreeNode's API for some help.
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
     

class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private String letter;
   private int freq;
   private HuffmanTreeNode left, right;
  
   public HuffmanTreeNode(Integer f) {
      freq = f;
      letter = "";
   }
   
   public HuffmanTreeNode(String s, Integer f) {
      letter = s;
      freq = f;
      left = null;
      right = null;
   }
   
   public HuffmanTreeNode(String s, int f) {
      letter = s;
      freq = f;
      left = null;
      right = null;
   }
  
   public int compareTo(HuffmanTreeNode o) {
      return freq-o.getFreq();
   }
  
   public void setFreq(int f) {
      freq = f;
   }
   
   public Integer getFreq() {
      return freq;
   }
   
   public String toString() {
      return letter + ": " + freq;
   }
   
   public String getLetter() {
      return letter;
   }
      
   public HuffmanTreeNode getLeft() 
   { 
      return left; 
   }
   
   public HuffmanTreeNode getRight() 
   { 
      return right; 
   }
   
   public void setLeft(HuffmanTreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(HuffmanTreeNode theNewRight)
   { 
      right = theNewRight;
   }

}
   
  
