import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node first;
	private Node last;
	private int count = 0;
	
	
	private class Node{
		Item item;
		Node next;
		Node previous;
	}
	
	public Deque(){
	}
	public boolean isEmpty(){
	   return first == null;
	}
	public int size(){
	   return count;
	}
	public void addFirst(Item item){
	    if(item == null){
	           throw new IllegalArgumentException("item is null"); 
	    }
		Node newNode = new Node();
		newNode.item = item;
		newNode.next = first;
		newNode.previous = null;
		if (isEmpty()){
			first = newNode;
			last = newNode;
		}
		else{
			first.previous = newNode;
			first = newNode;
		}
		count++;
	}
	public void addLast(Item item){
	    if(item == null){
	           throw new IllegalArgumentException("item is null"); 
	    }
		Node newNode = new Node();
		newNode.item = item;
		newNode.next = null;
		newNode.previous = last;
		if (isEmpty()){
			first = newNode;
			last = newNode;
		}
		else{
			last.next = newNode;
			last = newNode;
		}
		count++;
	}
	public Item removeFirst(){
	    if(isEmpty()){
	           throw new NoSuchElementException("queue is empty");
	    }
		Node oldFirst = first;
		first = first.next;	
		count--;
		return oldFirst.item;
	}
	public Item removeLast(){
	    if(isEmpty()){
	           throw new NoSuchElementException("queue is empty");
	    }
		Node oldLast = last;
		last = last.previous;
		count--;
		return oldLast.item;
	}
	public Iterator<Item> iterator(){
		return new DequeIterator();
	}
	
    private class DequeIterator implements Iterator<Item>
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
			   
	}
}

