// Name: B5-07-23
// Date: 01/26/23
//uses PostfixEval

import java.util.*;

public class InfixPostfixEval {
   public static final String LEFT = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";

   public static void main(String[] args) {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /* build your list of Infix expressions here */
      List<String> infixExp = new ArrayList<>();
      infixExp.add("3 + 4 * 5");
      infixExp.add("3 * 4 + 5");
      infixExp.add("1.3 + 2.7 + -6 * 6");
      infixExp.add("( 33 + -43 ) * ( -55 + 65 )");
      infixExp.add("3 * 4 + 5 / 2 - 5");
      infixExp.add("8 + 1 * 2 - 9 / 3");
      infixExp.add("3 * ( 4 * 5 + 6 )");
      infixExp.add("2 + 7 % 3");
      infixExp.add("4 - 3 + 2 + 5 * 2 / 3 % 2");

      for (String infix : infixExp) {
         String pf = infixToPostfix(infix); // get the conversion to work first
         // System.out.println(infix + "\t\t\t" + pf );
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf)); // PostfixEval must work!
      }
   }

   public static String infixToPostfix(String infix) {
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      Stack<String> stk = new Stack<>();

      String result = "";
      for (String s : nums) {
         if (!(operators.contains(s) || LEFT.contains(s) || RIGHT.contains(s)))
            result += s + " ";
         else if (LEFT.contains(s))
            stk.push(s);
         else if (RIGHT.contains(s)) {
            while (!stk.empty()) {
               String p = stk.pop();
               if (LEFT.contains(p))
                  break;
               else
                  result += p + " ";
            }
         } else if (operators.contains(s)) {
            if (stk.empty())
               stk.push(s);
            else if (LEFT.contains(stk.peek()))
               stk.push(s);
            else if (isStrictlyLower(stk.peek(), s))
               stk.push(s);
            else {
               while (!(stk.empty() || LEFT.contains(stk.peek()) || isStrictlyLower(stk.peek(), s)))
                  result += stk.pop() + " ";
               stk.push(s);
            }
         }
      }
      while (!stk.empty()) {
         result += stk.pop() + " ";
      }

      return result.trim();
   }

   // enter your precedence method below
   public static boolean isStrictlyLower(String t, String c) {
      if ((t.equals("+") || t.equals("-")) && (c.equals("+") || c.equals("-")))
         return false;
      if ((t.equals("*") || t.equals("/") || t.equals("%")) && (c.equals("*") || c.equals("/") || c.equals("%")))
         return false;
      if ((t.equals("^") || t.equals("!")) && (c.equals("^") || c.equals("!")))
         return false;
      if (operators.indexOf(t) < operators.indexOf(c))
         return true;
      else
         return false;
   }

}

/********************************************
 * 
 * Infix --> Postfix --> Evaluate
 * 5 - 1 - 1 5 1 - 1 - 3.0
 * 5 - 1 + 1 5 1 - 1 + 5.0
 * 12 / 6 / 2 12 6 / 2 / 1.0
 * 3 + 4 * 5 3 4 5 * + 23.0
 * 3 * 4 + 5 3 4 * 5 + 17.0
 * 1.3 + 2.7 + -6 * 6 1.3 2.7 + -6 6 * + -32.0
 * ( 33 + -43 ) * ( -55 + 65 ) 33 -43 + -55 65 + * -100.0
 * 8 + 1 * 2 - 9 / 3 8 1 2 * + 9 3 / - 7.0
 * 3 * ( 4 * 5 + 6 ) 3 4 5 * 6 + * 78.0
 * 3 + ( 4 - 5 - 6 * 2 ) 3 4 5 - 6 2 * - + -10.0
 * 2 + 7 % 3 2 7 3 % + 3.0
 * ( 2 + 7 ) % 3 2 7 + 3 % 0.0
 * 
 ***********************************************/
