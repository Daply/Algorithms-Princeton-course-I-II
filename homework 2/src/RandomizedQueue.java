import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] items;
	private int count = 0;

   public RandomizedQueue(){
       items = (Item[]) new Object[1];
   }
   
   public boolean isEmpty(){
       return count == 0;
   }
   
   public int size(){
	 return count;
   }
   
   public void enqueue(Item item){
       if(item == null){
           throw new IllegalArgumentException("item is null"); 
       }
       if(count == items.length){
           resize(2*count);
       }
       items[count] = item;
       count++;
   }
   
   public Item dequeue(){
       int random = StdRandom.uniform(count);
       if(isEmpty()){
           throw new NoSuchElementException("queue is empty");
       }
       Item item = items[random];
       
       // deleting by copying all elements except
       // one with random number
       Item[] copy = (Item[]) new Object[count];
       for (int i = 0; i < random; i++)
          copy[i] = items[i];
       for (int i = random; i < items.length; i++)
           copy[i] = items[i];
       items = copy;

       if (count > 0 && count == items.length/4) 
           resize(items.length/2);
       return item;
   }
   
   public Item sample(){
       int random = StdRandom.uniform(count);
       if(isEmpty()){
           throw new NoSuchElementException("queue is empty");
       }
       Item item = items[random];
       return item;
   }
   
   private void resize(int capacity){
       Item[] copy = (Item[]) new Object[capacity];
       for (int i = 0; i < count; i++)
          copy[i] = items[i];
       items = copy;
   }
   
   public Iterator<Item> iterator(){
	   return new RandomizedQueueIterator();
   }
   
   private class RandomizedQueueIterator implements Iterator<Item>
   {
       private int currentIndex = 0;
       public boolean hasNext() {  return items[currentIndex] != null;  }  
       public void remove() {
           throw new UnsupportedOperationException("unsupported operation");
       } 
       public Item next()
       {
           if(currentIndex >= count){
               throw new NoSuchElementException("no more items to return");
           }
           Item item = items[currentIndex];
           currentIndex = currentIndex++; 
           return item;
       }
   }
   
   public static void main(String[] args){
       
       RandomizedQueue<String> rnq;
       
       int choice = StdIn.readInt();
       switch(choice){
       case(0):
           // testing isEmpty()
           
           rnq = new RandomizedQueue<String>();
           for(int i = 0; i < 3; i++){
               String s = StdIn.readString();
               rnq.enqueue(s);
           }
           for(int i = 0; i < 3; i++){
               StdOut.println(rnq.dequeue());
               StdOut.println("Is queue empty: " + rnq.isEmpty());
           }
       
           break;
           
       case(1):
           // testing size()
           
           rnq = new RandomizedQueue<String>();
           for(int i = 0; i < 3; i++){
               String s = StdIn.readString();
               rnq.enqueue(s);
               StdOut.println("Size: " + rnq.size());
           }
           for(int i = 0; i < 3; i++){
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
           int num = StdIn.readInt();
           rnq = new RandomizedQueue<String>();
           for(int i = 0; i < 9; i++){
               String s = StdIn.readString();
               rnq.enqueue(s);
           }
           
           for(int i = 0; i < num; i++){
               StdOut.println(rnq.dequeue());
           }
           
           break;
           
       case(6):
           // testing iterator()
           
           rnq = new RandomizedQueue<String>();
           rnq.enqueue("LNlnl");
           rnq.enqueue("vsd");
           rnq.enqueue("dfg");
           rnq.enqueue("vsoyikd");
           rnq.enqueue("hrr");
           rnq.enqueue("luil");
           Iterator it = rnq.iterator();
           while (it.hasNext()){
               String s = (String) it.next();
               StdOut.println(s);
           }
           
           break;
           
       case(7):
           //end of testing
           
           break;
           
       default:  StdOut.println("end");
       }
       
   }
}