import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack{

    private Node last;
    private Node max;
    private int count = 0;   
    
    private class Node{
        Integer item;
        Node next;
        Node previous;
    }
    
    public Stack(){
    }
    public boolean isEmpty(){
       return last == null;
    }
    public int size(){
       return count;
    }
    
    public void push(Integer item){
        if(item == null){
               throw new IllegalArgumentException("item is null"); 
        }
        Node newNode = new Node();
        newNode.item = item;
        newNode.next = null;
        newNode.previous = last;
        if (isEmpty()){
            last = newNode;
            max = newNode;
        }
        else{
            last.next = newNode;
            last = newNode;
            if(compare(newNode.item, max.item)){
                max = newNode;
            }
        }
        count++;
    }
    public Integer pop(){
        if(isEmpty()){
               throw new NoSuchElementException("stack is empty");
        }
        Node oldLast = last;
        if(oldLast == max){
            searchMax();
        }
        last = last.previous;
        count--;
        return oldLast.item;
    }
    
    public Integer getMax(){
        if(isEmpty()){
            throw new NoSuchElementException("stack is empty");
       }
        return max.item;
    }
    
    public void searchMax(){
        if(isEmpty()){
               throw new NoSuchElementException("stack is empty");
        }
        Node node = last;
        while (node != null){
            if(compare(node.item, max.item)){
                max = node;
            }
            node = node.previous;
        }
    }
    
    private boolean compare(Integer i1, Integer i2){
        if(i1.intValue() > i2.intValue()) {
            return true;
        }
        else {
            return false; 
        }
    }
    
    public Iterator<Integer> iterator(){
        return new StackIterator();
    }
    
    private class StackIterator implements Iterator<Integer>
    {
        private Node current = last;
        public boolean hasNext() {  return current != null;  } 
        public void remove() {
            throw new UnsupportedOperationException("unsupported operation");
        }
        public Integer next()
        {
            Integer item = current.item;
            current   = current.previous; 
            return item;
        }
    }
    
}
