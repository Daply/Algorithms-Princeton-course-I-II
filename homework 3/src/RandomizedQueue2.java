import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue2<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int count = 0;
    
    
    private class Node{
        Item item;
        Node next;
    }
    
    public RandomizedQueue2(){
    }
    
    public boolean isEmpty(){
       return first == null;
    }
    public int size(){
       return count;
    }
    
    public void enqueue(Item item){
        if(item == null){
            throw new IllegalArgumentException("item is null"); 
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        last.next = newNode;
        last = newNode;
        count++;
    }

    
    public Item sample(){
        int random = StdRandom.uniform(count);
        if(isEmpty()){
            throw new NoSuchElementException("queue is empty");
        }
        Item item;
        Iterator it = iterator();
        int index = 0;
        while(it.hasNext()){
            item = (Item) it.next();
            index++;
            if(index == random){
                return item;
            }
        }      
        return null;
    }
    
    public Iterator<Item> iterator(){
        return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext() {  return current != null;  } 
        public void remove() {
            throw new UnsupportedOperationException("unsupported operation");
        }
        public Item next()
        {
            Item item = current.item;
            current   = current.next; 
            return item;
        }
    }
    
    public static void main(String[] args){
        int num = StdIn.readInt();
        
        RandomizedQueue2<String> rnq;
        rnq = new RandomizedQueue2<String>();
        for(int i = 0; i < 9; i++){
            String s = StdIn.readString();
            rnq.enqueue(s);
        }
        
        for(int i = 0; i < num; i++){
            StdOut.println(rnq.sample());
        }
        
    }
}
