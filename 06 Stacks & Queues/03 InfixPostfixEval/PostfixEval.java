// Name:
// Date:

import java.util.*;
import java.lang.Math;

public class PostfixEval
{
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
      postfixExp.add("3 4 5 * +");
      postfixExp.add("3 4 * 5 +");
      postfixExp.add("10 20 + -6 6 * +");
      postfixExp.add("3 4 + 5 6 + *");
      postfixExp.add("3 4 5 + * 2 - 5 /");
      postfixExp.add("8 1 2 * + 9 3 / -");
      postfixExp.add("2 3 ^");
      postfixExp.add("20 3 %");
      postfixExp.add("21 3 %");
      postfixExp.add("22 3 %");
      postfixExp.add("23 3 %");
      postfixExp.add("5 !");
      postfixExp.add("1 1 1 1 1 + + + + !");
      
   
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static double eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      Stack<Double> stk = new Stack<>();
      Double val1 = 0.0;
      Double val2 = 0.0;
      /*  enter your code here  */
      for(String s : postfixParts) {
         
         if(!isOperator(s)) stk.push(Double.valueOf(s));
         else if(s.compareTo("!") == 0) {
            val1 = stk.pop();
            stk.push(eval(val1, 0.0, s));
         }
         else {
            val2 = stk.pop();
            val1 = stk.pop();
            stk.push(eval(val1, val2, s));
         }
      }
      
      return Double.valueOf(stk.get(0));
      
   
   }
   
   public static double eval(double a, double b, String op)
   {
      switch(op) {
         case "+": return a + b;
         case "-": return a - b;
         case "*": return a * b;
         case "/": return a / b;
         case "^": return Math.pow(a, b);
         case "%": return a % b;
         case "!": 
            double val = 1;
            double count = a;
            while(count != 1) {
               val *= count;
               count--;
            }
            return val;
                     
      }
      return -1.0;    
   }
   
   public static boolean isOperator(String op)
   {
      if("+-*/^%!".contains(op)) return true;
      return false;
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/