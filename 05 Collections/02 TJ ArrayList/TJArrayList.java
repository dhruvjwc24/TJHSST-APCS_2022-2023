// Name: 
// Date:

/**
 * Implements the cheat sheet's List interface. Implements generics.
 * The backing array is an array of (E[]) new Object[10];
 * 
 * @override toString()
 */
public class TJArrayList<E> {
   private int size; // stores the number of objects
   private E[] myArray;

   @SuppressWarnings("unchecked")
   public TJArrayList() {
      myArray = (E[]) new Object[10]; // default constructor instantiates a raw array with 10 cells

      size = 0;
   }

   public int size() {
      return size;
   }

   /*
    * appends obj to end of list; increases size;
    * must be an O(1) operation when size < array.length,
    * and O(n) when it doubles the length of the array.
    * 
    * @return true
    */
   @SuppressWarnings("unchecked")
   public boolean add(E obj) {

      E[] arr;

      if (size + 1 > myArray.length) {
         arr = (E[]) new Object[myArray.length + 10];
         int i = 0;
         while (i != size) {
            arr[i] = myArray[i];
            i++;
         }
         myArray = arr;
      }

      myArray[size] = obj;
      size += 1;
      return true;
   }

   /*
    * inserts obj at position index. increments size.
    */
   @SuppressWarnings("unchecked")
   public void add(int index, E obj) throws IndexOutOfBoundsException // this the way the real ArrayList is coded
   {
      if (index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

      E[] arr;

      if (size + 1 > myArray.length) {
         arr = (E[]) new Object[myArray.length + 10];
         int i = 0;
         while (i != size) {
            arr[i] = myArray[i];
            i++;
         }
         myArray = arr;
      }

      int i = size - 1;
      while (i >= index) {
         myArray[i + 1] = myArray[i];
         i--;
      }
      myArray[index] = obj;
      size++;
   }

   /*
    * return obj at position index.
    */
   public E get(int index) throws IndexOutOfBoundsException {
      if (index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      return myArray[index];

   }

   /**
    * Replaces obj at position index.
    * 
    * @return the object is being replaced.
    */
   public E set(int index, E obj) throws IndexOutOfBoundsException {
      if (index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      E replaced = myArray[index];
      myArray[index] = obj;
      return replaced;
   }

   /*
    * removes the node from position index. shifts elements
    * to the left. Decrements size.
    * 
    * @return the object that used to be at position index.
    */
   public E remove(int index) throws IndexOutOfBoundsException {
      if (index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

      E previous = myArray[index];
      int i = index + 1;
      while (myArray[i - 1] != null) {
         myArray[i - 1] = myArray[i];
         i++;
      }
      size--;
      return previous;

   }

   /*
    * This method compares objects.
    * Must use .equals(), not ==
    */
   public boolean contains(E obj) {
      for (int i = 0; i < size; i++) {
         if (myArray[i].equals(obj))
            return true;
      }
      return false;
   }

   /*
    * returns a String of E objects in the array with
    * square brackets and commas.
    */
   public String toString() {
      System.out.print("[");
      int i = 0;
      while (myArray[i + 1] != null) {
         System.out.print(myArray[i].toString() + ", ");
         i++;
      }
      return (myArray[i].toString() + "]");
   }
}