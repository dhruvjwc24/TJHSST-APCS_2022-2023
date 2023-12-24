 //Name:   
 //Date:
 
import java.util.*;

/* implement the API for java.util.PriorityQueue
 *     a min-heap of objects in an ArrayList<E> in a resource class
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public ArrayList<E> getHeap()   //for Codepost
   {
      return myHeap;
   }
   
   public int lastIndex()
   {
      return myHeap.size()-1;
   }
   
   public boolean isEmpty()
   {
      return myHeap.size()<=1;
   }
   
   public boolean add(E obj)
   {
      myHeap.add(obj);
      if(myHeap.size()>=3)
      heapUp(lastIndex());
      return true;
   }
   
   public E remove()
   {
   
     E rem = myHeap.get(1);
     
     if(myHeap.isEmpty())
      return null;
      
     if(myHeap.size() < 3)
      myHeap.remove(1);
      
     else {
      swap(1, myHeap.size()-1);
      myHeap.remove(myHeap.size()-1);
      heapDown(1, myHeap.size());
      
     }
     
     return rem;
   }
   
   public E peek()
   {
      return myHeap.get(myHeap.size()-1);
   }
   
   //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapUp(int k)
   {
   
      int p = k/2;
      if(p>=1) {
         if(myHeap.get(k).compareTo(myHeap.get(p)) <= 0) {
            swap(k, p);
            heapUp(p);
         }
      }
      
   }
   
   private void swap(int a, int b)
   {
     E temp = myHeap.get(a);
     myHeap.set(a, myHeap.get(b));
     myHeap.set(b, temp);
   }
   
  //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapDown(int k, int lastIndex)
   {
      int ch1 = 2*k;
      int ch2 = 2*k+1;
      if(k>=myHeap.size()) return;
      if(ch1<myHeap.size() && ch2 < myHeap.size()) {
         int maxCh;
         if(myHeap.get(ch1).compareTo(myHeap.get(ch2))<0) maxCh = ch1;
         else maxCh = ch2;
         
         if(myHeap.get(k).compareTo(myHeap.get(maxCh)) > 0)
         {
            swap(k,maxCh);
            heapDown(maxCh, myHeap.size());
         }
      }
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
