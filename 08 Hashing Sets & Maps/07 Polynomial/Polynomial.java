 // Name:    
 // Date: 

import java.util.*;


interface PolynomialInterface
{
   public void makeTerm(Integer exp, Integer coef);
   public Map<Integer, Integer> getMap();
   public double evaluateAt(double x);
   
   //precondition: both polynomials are in standard form
   //postcondition: terms with zero disappear. If all terms disappear (the size is zero), 
   //               add pair (0,0).
   public Polynomial add(Polynomial other);
   
   //precondition: both polynomials are in standard form
   //postcondition: terms with zero disappear. If all terms disappear (the size is zero), 
   //               add pair (0,0)
   public Polynomial multiply(Polynomial other);
   public String toString();
}

class Polynomial implements PolynomialInterface
{
   private Map<Integer, Integer> myPoly = null;
   
   public Polynomial() {
      myPoly = new TreeMap<Integer, Integer>();
   }
   
   public Map<Integer, Integer> getMap() {
      return myPoly;
   }
   
   public double evaluateAt(double x) {
      double ret = 0.0;
      Iterator<Integer> exps = myPoly.keySet().iterator();
      Iterator<Integer> coefs = myPoly.values().iterator();
      while(exps.hasNext()) {
         ret+=(double)coefs.next() * Math.pow(x, (double)exps.next());
      }
      return ret;
      
      
   }
   
   public void makeTerm(Integer exp, Integer coef) {
      if(coef!=0) myPoly.put(exp, coef);
   }
   
   public Polynomial multiply(Polynomial other) {
      Polynomial ret = new Polynomial();
      Set<Integer> t = myPoly.keySet();
      Set<Integer> o = other.getMap().keySet();
      Iterator<Integer> itr = t.iterator();
      int pExp, pCoef, tExp, tCoef, oExp, oCoef, value;
      while(itr.hasNext()) {
         tExp = itr.next();
         tCoef = myPoly.get(tExp);
         Iterator<Integer> itr2 = o.iterator();
         while(itr2.hasNext()) {
            oExp = itr2.next();
            oCoef = other.getMap().get(oExp);
            pExp = tExp+oExp;
            pCoef = tCoef*oCoef;
            if(ret.getMap().containsKey(pExp)) {
               value = pCoef+ret.getMap().get(pExp);
               if(value==0)
                  ret.getMap().remove(pExp);
               else
                  ret.getMap().replace(pExp, value);
            }
            else {
               ret.makeTerm(pExp, pCoef);
            }
         }
         
      }
      return ret;
   }
   
   public Polynomial add(Polynomial other) {
      /*
      if(myPoly == null) {
         myPoly = other.getMap();
         return myPoly;
      }
      */
      Polynomial ret = new Polynomial();
      Set<Integer> t = myPoly.keySet();
      Set<Integer> o = other.getMap().keySet();
      Iterator<Integer> itr = t.iterator();
      
      int key;
      int value;
      ArrayList<Integer> bad = new ArrayList<>();
      while(itr.hasNext()) {
         key = itr.next();
         if(other.getMap().containsKey(key)) {
            value = myPoly.get(key)+other.getMap().get(key);
            if(value == 0) {
               bad.add(key);
            }
         }
         else {
            value = myPoly.get(key);
         }
         ret.makeTerm(key, value);
      }
      Iterator<Integer> itr2 = o.iterator();
      
      while(itr2.hasNext()) {
         key = itr2.next();
         if(bad.contains(key)) continue;
         if(!ret.getMap().containsKey(key)) {
            ret.makeTerm(key, other.getMap().get(key));
         }
      } 
      
      return ret; 
      
   }
   

   public String toString() {
      int exp, coef;
      StringBuffer ret = new StringBuffer();
      String temp = "";
      for(Integer expo : myPoly.keySet()) {
         exp = expo;
         coef = myPoly.get(exp);
         if(exp == 0) {
            ret.insert(0, String.valueOf(coef) + " + ");
         }
         
         else if(exp == 1) {
         /*
            temp = String.valueOf(coef) + "x";
            ret.insert(0, temp  + " + ");
         */
            if(coef == 1) {
               temp = "x";
               ret.insert(0, temp  + " + ");
            }
            else {
               temp = String.valueOf(coef) + "x";
               ret.insert(0, temp  + " + ");
            }
         }
         
         else if(coef == 1) {
            temp = "x^" + String.valueOf(exp);
            ret.insert(0, temp  + " + ");
         }
         
         else if(coef == -1) {
            temp = "-x^" + String.valueOf(exp);
            ret.insert(0, temp  + " + ");
         }
         
         else {
            temp = String.valueOf(coef) + "x^" + String.valueOf(exp);
            ret.insert(0, temp  + " + ");
         }

      }
      
      return ret.toString().substring(0, ret.toString().length()-2);
   }
}