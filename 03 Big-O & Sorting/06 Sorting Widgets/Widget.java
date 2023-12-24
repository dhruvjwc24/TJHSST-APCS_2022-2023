// Name: B5-07-22
// Date: 11/18/22

public class Widget implements Comparable<Widget>
{
   //fields
   int cubits = 0;
   int hands = 0;
   
   //constructors
   
   public Widget() {
      cubits = 0;
      hands = 0;
   }
   
   public Widget(Widget w) {
      cubits = w.cubits;
      hands = w.hands;
   }
   
   
   public Widget(int c, int h) {
      cubits = c;
      hands = h;
   }
   
   //accessors and modifiers
   public int getCubits() {
      return cubits;
   }
   
   public int getHands() {
      return hands;
   }
   
   public void setCubits(int c) {
      cubits = c;
   }
   
   public void setHands(int h) {
      hands = h;
   }
   
   //compareTo and equals
   
   //toString
   public String toString() {
      return String.format("%d cubits %d hands", cubits, hands);
   }
   
   public int compareTo(Widget w) {
      return 0;
   }
   
   public boolean equals(Widget w) {
      if(cubits == w.cubits && hands == w.hands) {         
          return true;
      }

      return false;
   }  
   
}
