// Name:
// Date: 

interface BST_Generic_interface<E>
{
   public int size();
   public TreeNode<E> getRoot() ;
   public boolean contains(E obj);
   public void add(E obj);         //does not balance
   public void addBalanced(E obj); //AVL
   public void remove(E obj);      //does not balance
   public E min();
   public E max();
   public String display();
   public String toString();
}

/*******************
Copy your BST code.  Implement generics.
If you skipped remove() and/or addBalanced(), just leave the method bodies empty.
**********************/
public class BST_Generic<E extends Comparable<E>> implements BST_Generic_interface<E>
{
   /*  copy your BST code  here  */
   private TreeNode<E> root;
   private int size;
   public BST_Generic()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
     return size;
   }
   public TreeNode<E> getRoot()   //accessor method
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(E s) 
   {
      root = add(root, s);
      size++;
   }
   private TreeNode<E> add(TreeNode<E> t, E s) //recursive helper method
   {      
      if(t == null) {
      
         return new TreeNode((E) s);
      }
      
      if(s.compareTo(t.getValue()) <= 0) t.setLeft(add(t.getLeft(), s));
      else t.setRight(add(t.getRight(), s));
      return t;
   }
     /*************************
      Copy the display() method from TreeLab. 
      **********************/
   public String display()
   {
      return display(root, 0);
   }
   private String display(TreeNode<E> t, int level) //recursive helper method
   {
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
   
   public boolean contains( E obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode<E> t, E x) //recursive helper method
   {
      if(t == null) return false;
      if(x.compareTo(t.getValue()) < 0) return contains(t.getLeft(), x);
      if(x.compareTo(t.getValue()) > 0) return contains(t.getRight(), x);
      return true;
   }
   
   public E min()
   {
      return min(root);
   }
   private E min(TreeNode<E> t)  //use iteration
   {
      if(t == null) {
         return null;
      }
      
      while(t.getLeft() != null) {
         t = t.getLeft();
      }
      return t.getValue();
   }
   
   public E max()
   {
      return max(root);
   }
   private E max(TreeNode<E> t)  //recursive helper method
   {
      if(t == null) return null;
      if(t.getRight() == null) return t.getValue();
      return max(t.getRight());
   }
   
   public void remove(E target) {
      remove(root, target);
   }
   
   private TreeNode<E> remove(TreeNode<E> current, E target)
   {
      // case 1a
      if(current == null) return current;
      if(target.compareTo(current.getValue()) < 0) {
         current.setLeft(remove(current.getLeft(), target));
      }
      else if(target.compareTo(current.getValue()) > 0) {
         current.setRight(remove(current.getRight(), target));
      }
      else {
         // if only right child
         if(current.getLeft() == null) return current.getRight();
         
         // if only left child
         if(current.getRight() == null) return current.getLeft();
         
         // if both children, find the min of the right child, make that the new root, and remove the min from the right child
         current.setValue(min(current.getRight()));
         current.setRight(remove(current.getRight(), current.getValue()));
 
      }  
      
     return current;
      // case 2a
      
      // case 2b
      
      // etc.
   
   } 

   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode<E> t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
         if (t == null)
            return "";
         toReturn += toString(t.getLeft());
         toReturn += t.getValue() + " ";
         toReturn += toString(t.getRight());
         return toReturn;
   }




   /*  start the addBalanced methods */
   private int calcBalance(TreeNode<E> current) //height to right minus 
   {                                    //height to left
      if (current == null) return 0;
      return height(current.getRight()) - height(current.getLeft());
   }

   private int height(TreeNode<E> current)   //from TreeLab
   {
      if(current == null) return -1;
      return 1 + Math.max(height(current.getLeft()), height(current.getRight()));
   }

   public void addBalanced(E value)  
   {
      add(value);
      root = balanceTree( root );   // for an AVL tree. Put in the arguments you want.
   }
   private TreeNode<E> balanceTree( TreeNode<E> current )  //recursive.  Whatever makes sense.
   {  
      int bal = calcBalance(current);
      
      if(current == null) return current;
      
      current.setRight(balanceTree(current.getRight()));
      current.setLeft(balanceTree(current.getLeft()));
      
      if(bal > 1) {
         if(calcBalance(current.getRight()) < 0) {
            current.setRight(rightRot(current.getRight()));
            current = leftRot(current);
         }
         else current = leftRot(current);
      }   
      
      else if(bal < -1) {
         if(calcBalance(current.getLeft()) > 0) {
            current.setLeft(leftRot(current.getLeft()));
            current = rightRot(current);
         }
         else current = rightRot(current);
      } 
      
      return current;  
   }
   
   
   // 4 rotation helper methods
   private TreeNode<E> leftRot(TreeNode<E> current) {
      TreeNode<E> n = current.getRight();
      TreeNode<E> n2 = n.getLeft();
      n.setLeft(current);
      current.setRight(n2);
      return n;
   }
   
   private TreeNode<E> rightRot(TreeNode<E> current) {
      TreeNode<E> n = current.getLeft();
      TreeNode<E> n2 = n.getRight();
      n.setRight(current);
      current.setLeft(n2);
      return n;
   }   

}

/*******************
  Copy your TreeNode code.  Implement generics.
**********************/
class TreeNode<E>
{
   private E value; 
   private TreeNode<E> left, right;

    public TreeNode(E initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }

    public TreeNode(E initValue, TreeNode<E> initLeft, TreeNode<E> initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }

    public E getValue()
   { 
      return value; 
   }

    public TreeNode<E> getLeft() 
   { 
      return left; 
   }

    public TreeNode<E> getRight() 
   { 
      return right; 
   }

    public void setValue(E theNewValue) 
   { 
      value = theNewValue; 
   }

    public void setLeft(TreeNode<E> theNewLeft) 
   { 
      left = theNewLeft;
   }

    public void setRight(TreeNode<E> theNewRight)
   { 
      right = theNewRight;
   }
}