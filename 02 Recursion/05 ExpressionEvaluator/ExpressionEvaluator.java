
// Name: B5-07-22
// Date: 10/2/22
import java.util.*;

/**
 * This program calculates the value of an expression
 * consisting of numbers, arithmetic operators, and parentheses.
 */
public class ExpressionEvaluator {
   public static void main(String[] args) {
      Scanner in = new Scanner(System.in);
      System.out.print("Enter an expression: ");
      // 3+4*5 23
      // (3+4)*5 35
      // (4+5)-5*3 -6
      // (3+4)*(5+6) 77
      // (3*(4+5)-2)/5 5
      // 2*3*4-9/3 21
      /* extension, left-to-right processing */
      // 6*8/6 6
      // 2-3+5 -6
      // 3*4/6 0
      // 3+4-5+2*2 6

      String input = in.nextLine().trim();
      Evaluator e = new Evaluator(input);
      int value = e.getExpressionValue();
      // int value = e.getExpressionV); //extension
      System.out.println(input + " = " + value);
   }
}

/**
 * A class that can compute the value of an arithmetic expression.
 */
class Evaluator {
   private ExpressionTokenizer tokenizer;

   /**
    * Constructs an evaluator.
    * 
    * @param anExpression a string containing the expression
    *                     to be evaluated
    */
   public Evaluator(String anExpression) {
      tokenizer = new ExpressionTokenizer(anExpression);
   }

   /**
    * Evaluates the expression.
    * 
    * @return the value of the expression.
    */
   public int getExpressionValue() {
      int term1 = getTermValue();
      if (tokenizer.peekToken() != null && tokenizer.peekToken().equals("+")) {
         tokenizer.nextToken();
         // tokenizer.nextToken();
         return term1 + getExpressionValue();
      } else if (tokenizer.peekToken() != null && tokenizer.peekToken().equals("-")) {
         tokenizer.nextToken();
         // tokenizer.nextToken();
         return term1 - getExpressionValue();
      } else {
         return term1;
      }
   }

   /**
    * Evaluates the next term found in the expression.
    * 
    * @return the value of the term
    */
   public int getTermValue() {
      int factor1 = getFactorValue();
      if (tokenizer.peekToken() != null && tokenizer.peekToken().equals("*")) {
         tokenizer.nextToken();
         return factor1 * getTermValue();
      } else if (tokenizer.peekToken() != null && tokenizer.peekToken().equals("/")) {
         tokenizer.nextToken();
         return factor1 / getTermValue();
      } else {
         return factor1;
      }
   }

   /**
    * Evaluates the next factor found in the expression.
    * 
    * @return the value of the factor
    */
   public int getFactorValue() {
      int value;
      String next = tokenizer.peekToken();
      if ("(".equals(next)) {
         tokenizer.nextToken(); // Discard "("
         value = getExpressionValue();
         tokenizer.nextToken(); // Discard ")"
      } else {
         value = Integer.parseInt(tokenizer.nextToken());
      }
      return value;
   }

   /**
    * Extension
    * 
    */
   public int getExpressionValueEXT() {
      return -99; // placeholder
   }

   /**
    * Extension
    * 
    */
   public int getTermValueEXT() {
      return -99; // placeholder
   }

   /**
    * Extension
    * 
    */
   public int getFactorValueEXT() {
      return -99; // placeholder
   }
}

/**
 * This class breaks up a string describing an expression
 * into tokens: numbers, parentheses, and operators.
 */
class ExpressionTokenizer {
   private String input;
   private int startNew; // The start of the current token
   private int endNew; // The position after the end of the current token

   /**
    * Constructs a tokenizer.
    * 
    * @param anInput the string to tokenize
    */
   public ExpressionTokenizer(String anInput) {
      input = anInput;
      startNew = 0;
      endNew = 0;
      nextToken(); // Find the first token
   }

   /**
    * Peeks at the next token without consuming it.
    * 
    * @return the next token or null if there are no more tokens
    */
   public String peekToken() {
      if (startNew >= input.length()) {
         return null;
      } else {
         return input.substring(startNew, endNew);
      }
   }

   /**
    * Gets the next token and moves the tokenizer to the following token.
    * 
    * @return the next token or null if there are no more tokens
    */
   public String nextToken() {
      String r = peekToken();
      startNew = endNew;
      if (startNew >= input.length()) {
         return r;
      }
      if (Character.isDigit(input.charAt(startNew))) {
         endNew = startNew + 1;
         while (endNew < input.length()
               && Character.isDigit(input.charAt(endNew))) {
            endNew++;
         }
      } else {
         endNew = startNew + 1;
      }
      return r;
   }
}