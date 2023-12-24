// Name:
// Date:

import java.util.*;

public class ParenMatch
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter test cases here */
      parenExp.add("5 + 7");
      parenExp.add("( 15 + -7 )");
      parenExp.add(") 5 + 7 (");
      parenExp.add("( [ 5.0 - 7.3 ] * 3.5 )");
      parenExp.add("< { 5 + 7 } * 3 >");
      parenExp.add("[ ( 5 + 7 ) * ] 3");
      parenExp.add("( 5 + 7 ) * 3");
      parenExp.add("5 + ( 7 * 3 )");
      parenExp.add("( ( 5 + 7 ) * 3");
      parenExp.add("[ ( 5 + 7 ] * 3 )");
      parenExp.add("[ ( 5 + 7 ) * 3 ] )");
      parenExp.add("( [ ( 5 + 7 ) * 3 ]");
      parenExp.add("( ( ( ) $ ) )");
      parenExp.add("( ) [ ]");
   
      for( String s : parenExp )
      {
         boolean good = checkParen(s);
         //System.out.println(String.format("Exp: %s\tParens: %s", s, good));
         
         if(good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
         
      }
   }
     
   //returns the index of the left parentheses or -1 if it is not there
   public static int isLeftParen(String p)
   {
      return LEFT.indexOf(p);
   }
  
   //returns the index of the right parentheses or -1 if it is not there
   public static int isRightParen(String p)
   {
      return RIGHT.indexOf(p);
   }
   
   public static boolean checkParen(String exp)
   {
     /* enter your code here */
      Stack<String> stk = new Stack<>();
      String[] expArr = exp.split("");
      ListIterator<String> it = stk.listIterator();
      
     
      for(String chr : expArr) {
         if(LEFT.contains(chr) || RIGHT.contains(chr)) {
            stk.push(chr);
         }
      }
      
      if(stk.size() % 2 == 1) return false;
      
      int lInd = 0;
      int rInd = stk.size()-1;
     
      while(lInd <= rInd) {
      
         if(RIGHT.contains(stk.get(lInd)) || LEFT.indexOf(stk.get(lInd)) != RIGHT.indexOf(stk.get(rInd))) 
            return false;
         lInd+=1;
         rInd-=1;
     
      }
      return true;
     
     
     
     //System.out.println(stk);    
     //return stk.toString();  //so it compiles    
   }
}

/*****************************************

Parentheses Match
5 + 7		 good!
( 15 + -7 )		 good!
) 5 + 7 (		 BAD
( ( 5.0 - 7.3 ) * 3.5 )		 good!
< { 5 + 7 } * 3 >		 good!
[ ( 5 + 7 ) * ] 3		 good!
( 5 + 7 ) * 3		 good!
5 + ( 7 * 3 )		 good!
( ( 5 + 7 ) * 3		 BAD
[ ( 5 + 7 ] * 3 )		 BAD
[ ( 5 + 7 ) * 3 ] )		 BAD
( [ ( 5 + 7 ) * 3 ]		 BAD
( ( ( ) $ ) )		 good!
( ) [ ]		 good!
 
 *******************************************/
