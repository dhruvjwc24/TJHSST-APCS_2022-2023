// Name:              Date:
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   
   /*  
    To be consistent with the test in CodePost, 
    all the non-leaf nodes must store empty strings ("") 
    and each leaf node must store the letter as a String ("M").
    
    Do not store them as NULL and char!
    */  
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode root = new TreeNode("");
      TreeNode current = root;
      while(huffLines.hasNext()) {
         String line = huffLines.nextLine();
         String letter = line.substring(0, 1);
         String[] lineArr = line.substring(1).split("");
         String n = "";
         for(String c : lineArr) {
            if(c.equals("0")) {
               if(current.getLeft() == null) current.setLeft(new TreeNode(""));
               current = current.getLeft();
            }
            
            if(c.equals("1")) {
               if(current.getRight() == null) current.setRight(new TreeNode(""));
               current = current.getRight();
            }
            n = c;
         }
         current.setValue(letter);
         current = root;
      }
      return root;
   }
   
   public static String dehuff(String text, TreeNode root)
   {
      TreeNode current = root;
      String[] line = text.split("");
      String message = "";
      for(String n : line) {
         if(n.equals("0") && current.getLeft() != null) {
            current = current.getLeft();
            if(!current.getValue().equals("")) {
               message += current.getValue();
               current = root;
            }
         }
         
         if(n.equals("1") && current.getRight() != null) {
            current = current.getRight();
            if(!current.getValue().equals("")) {
               message += current.getValue();
               current = root;
            }
         }
      }
      
      return message;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
