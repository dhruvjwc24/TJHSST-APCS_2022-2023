// Name: 
// Date:  
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */

import java.util.*;

public class BXT {
   public static final String operators = "+ - * / % ^ !";
   private TreeNode root;

   public BXT() {
      root = null;
   }

   public TreeNode getRoot() // for Codepost
   {
      return root;
   }

   public void buildTree(String str) {
      Stack<TreeNode> stk = new Stack<>();
      String[] a = str.split(" ");
      for(String o : a) {
         if(!isOperator(o)) {
            stk.push(new TreeNode((Object) o));
         }
         else {
            Object init = (Object) o;
            TreeNode r = stk.pop();
            TreeNode l = stk.pop();
            stk.push(new TreeNode(init, l, r));
         }
      }
      root = stk.pop();
   }
   

   public double evaluateTree() {
      return evaluateNode(root);
   }

   private double evaluateNode(TreeNode t) // recursive
   {
      if(t.getLeft() == null || t.getRight() == null) {
         return new Double(t.getValue().toString());
      }
      
      String operator = (String) t.getValue();
      double a = evaluateNode(t.getLeft());
      double b = evaluateNode(t.getRight());
      
      return computeTerm(operator, a, b);
   }

   private double computeTerm(String s, double a, double b) {
      switch (s) {
         case "+":
            return a + b;
         case "-":
            return a - b;
         case "*":
            return a * b;
         case "/":
            return a / b;
         case "^":
            return Math.pow(a, b);
         case "%":
            return a % b;
         case "!":
            double val = 1;
            double count = a;
            while (count != 1) {
               val *= count;
               count--;
            }
            return val;

      }
      return -1.0;
   }

   private boolean isOperator(String s) {
      if (operators.contains(s))
         return true;
      return false;
   }

   public String display() {
      return display(root, 0);
   }

   private String display(TreeNode t, int level) {
      String toRet = "";
      if (t == null)
         return "";
      toRet += display(t.getRight(), level + 1); // recurse right
      for (int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); // recurse left
      return toRet;
   }

   public String inorderTraverse() {
      return inorderTraverse(root);
   }

   private String inorderTraverse(TreeNode t) {
      String toReturn = "";
      if (t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());
      toReturn += t.getValue() + " ";
      toReturn += inorderTraverse(t.getRight());
      return toReturn;
   }

   public String preorderTraverse() {
      return preorderTraverse(root);
   }

   private String preorderTraverse(TreeNode root) {
      String toReturn = "";
      if (root == null)
         return "";
      toReturn += root.getValue() + " "; // process root
      toReturn += preorderTraverse(root.getLeft()); // recurse left
      toReturn += preorderTraverse(root.getRight()); // recurse right
      return toReturn;
   }

   /* extension */
   // public String inorderTraverseWithParentheses()
   // {
   // return inorderTraverseWithParentheses(root);
   // }
   //
   // private String inorderTraverseWithParentheses(TreeNode t)
   // {
   // return "";
   // }
}