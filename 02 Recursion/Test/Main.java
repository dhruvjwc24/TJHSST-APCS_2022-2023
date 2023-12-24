public class Main {
   public static void main (String[] args) {
      int[] a = {1, 3, 4, 7, 9, 11, 13};
      System.out.println(disarray(a, 7));
   }
   
   public static void disarray(int[] a, int n) {
      if(n > 1) {
         disarray(a, n - 1);
         a[n - 1] += a[n - 2];
      }
   }
}