import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * 
 * @author Pleshchankova Daria
 *
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] items;
	private int count = 0;

	/**
	 * Constructor
	 */
   public RandomizedQueue() {
       items = (Item[]) new Object[1];
   }

   /**
    * Check if queue is empty
    * @return true if empty, 
    *         false if it is not
    */
   public boolean isEmpty() {
       return count == 0;
   }
   
   /**
    * Size of queue
    * @return size
    */
   public int size() {
	 return count;
   }
   
   /**
    * Add new element
    * @param item
    */
   public void enqueue(Item item) {
       if (item == null) {
           throw new IllegalArgumentException("item is null"); 
       }
       if (count == items.length) {
           resize(2*count);
       }
       items[count] = item;
       count++;
   }
   
    /**
     * Delete item from queue
     * @return deleted item
     */
   public Item dequeue() {
       if (isEmpty()) {
           throw new NoSuchElementException("queue is empty");
       }
       int random = StdRandom.uniform(count);
       Item item = items[random];
       
       // deleting by copying last element to
       // randomly chosen and nulling last object
       if (item != null) {          
           items[random] = items[count-1];
           items[count-1] = null;
           count--;
       }

       if (count > 0 && count == items.length/4) 
           resize(items.length/2);
       return item;
   }
   
   /**
    * Return random index item
    * @return item
    */
   public Item sample() {
       if (isEmpty()) {
           throw new NoSuchElementException("queue is empty");
       }
       int random = getNotNullElement();
       Item item = items[random];
       return item;
   }
   
   /**
    * Return random index of not null item
    * @return random index
    */
   private int getNotNullElement() {
       int index = StdRandom.uniform(count);
       while (items[index] == null) {
           index = StdRandom.uniform(count);
       }
       return index;
   }
   
   /**
    * Resize an array after deleting elements
    * from it 
    * @param capacity
    */
   private void resize(int capacity) {
       Item[] copy = (Item[]) new Object[capacity];
       for (int i = 0; i < count; i++)
          copy[i] = items[i];
       items = copy;
   }
   
   /**
    * Create an iterator
    */
   public Iterator<Item> iterator() {
	   return new RandomizedQueueIterator();
   }

   private class RandomizedQueueIterator implements Iterator<Item>
   {
       private int currentIndex = 0;
       public boolean hasNext() {  return currentIndex < count;  }  
       public void remove() {
           throw new UnsupportedOperationException("unsupported operation");
       } 
       public Item next()
       {
           StdRandom.shuffle(items);
           if (currentIndex >= count) {
               throw new NoSuchElementException("no more items to return");
           }
           int random = getNotNullElement();
           Item item = items[random];
           currentIndex++; 
           return item;
       }
   }
   
   public static void main(String[] args) {
       
       RandomizedQueue<String> rnq;
       
       StdOut.println("0 - test isEmpty()");
       StdOut.println("1 - test size()");
       StdOut.println("2 - test enqueue()");
       StdOut.println("3 - test dequeue()");
       StdOut.println("4 - test sample()");
       StdOut.println("5 - test iterator()");
       StdOut.println("6 - test all");
       StdOut.println("7 - one of tests");
       StdOut.println("8 - exit");
       int choice = StdIn.readInt();
       switch(choice){
       case(0):
           // testing isEmpty()
           
           rnq = new RandomizedQueue<String>();
           for (int i = 0; i < 3; i++) {
               String s = StdIn.readString();
               rnq.enqueue(s);
           }
           for (int i = 0; i < 3; i++) {
               StdOut.println(rnq.dequeue());
               StdOut.println("Is queue empty: " + rnq.isEmpty());
           }
       
           break;
           
       case(1):
           // testing size()
           
           rnq = new RandomizedQueue<String>();
           for (int i = 0; i < 3; i++) {
               String s = StdIn.readString();
               rnq.enqueue(s);
               StdOut.println("Size: " + rnq.size());
           }
           for (int i = 0; i < 3; i++) {
               StdOut.println(rnq.dequeue());
               StdOut.println("Size: " + rnq.size());
           }
           
           break;
           
       case(2):
           // testing enqueue()
           
           rnq = new RandomizedQueue<String>();
           rnq.enqueue("LNlnl");
           rnq.enqueue("vsd");
           rnq.enqueue(null);
           
           break;

       case(3):
           // testing dequeue()
           
           rnq = new RandomizedQueue<String>();
           rnq.enqueue("LNlnl");
           rnq.enqueue("vsd");
           StdOut.println(rnq.dequeue());
           StdOut.println(rnq.dequeue());
           rnq.enqueue("dfg");
           rnq.enqueue("vsoyikd");
           StdOut.println(rnq.dequeue());
           rnq.enqueue("hrr");
           rnq.enqueue("luil");
           StdOut.println(rnq.dequeue());
           StdOut.println(rnq.dequeue());
           StdOut.println(rnq.dequeue());
           
           break;

       case(4):
           // testing sample()
           
           rnq = new RandomizedQueue<String>();
           rnq.enqueue("LNlnl");
           rnq.enqueue("vsd");
           rnq.enqueue("dfg");
           rnq.enqueue("vsoyikd");
           rnq.enqueue("hrr");
           rnq.enqueue("luil");
           StdOut.println(rnq.sample());
           StdOut.println(rnq.sample());
           StdOut.println(rnq.sample());
           
           break;
           
       case(5):
           // testing iterator()
           
           rnq = new RandomizedQueue<String>();
           rnq.enqueue("LNlnl");
           rnq.enqueue("vsd");
           rnq.enqueue("dfg");
           rnq.enqueue("vsoyikd");
           rnq.enqueue("hrr");
           rnq.enqueue("luil");
           Iterator<String> it = rnq.iterator();
           while (it.hasNext()) {
               String s = it.next();
               StdOut.println(s);
           }    
           
           break;
           
       case(6):
           // testing all()
        
           int num = StdIn.readInt();
           rnq = new RandomizedQueue<String>();
           for (int i = 0; i < 9; i++) {
               String s = StdIn.readString();
               rnq.enqueue(s);
           }
           
           for (int i = 0; i < num; i++) {
               StdOut.println(rnq.dequeue());
           }
           
           break;
           
       case(7):
           // test

           rnq = new RandomizedQueue<String>();
           rnq.enqueue("IFKXBQPXCK");
           rnq.enqueue("BCEISEFNVE");
           rnq.enqueue("EGFEVQVWLA");
           StdOut.println(rnq.dequeue());     // ==> "BCEISEFNVE"
           rnq.enqueue("XSHPRFAZCH");
           StdOut.println(rnq.dequeue());     // ==> "null"
           
           break;
       case(8):
           // end
           break;
           
       default:  StdOut.println("end");
           break;
       }
       
   }
}