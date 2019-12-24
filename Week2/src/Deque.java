import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private int itemsInDeque;
    private Node first;
	private Node last;
    
    private class Node{
    	Item content;
    	Node previous;
    	Node next;
    }
	
	// construct an empty deque
    public Deque() {
    	itemsInDeque = 0;
    	first = null;
    	last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
    	return itemsInDeque == 0;
    }

    // return the number of items on the deque
    public int size() {
    	return itemsInDeque;
    }

    // add the item to the front
    public void addFirst(Item item) {
    	if(item == null) throw new IllegalArgumentException("Cannot add NULL to deque!");
    	Node oldFirstNode = first;
    	first = new Node();
    	if(isEmpty()) {
    		last = first;
    	}
    	else {
    		oldFirstNode.previous = first;
    	}
    	first.content = item;
    	first.next = oldFirstNode;
    	//first.previous = null;
    	itemsInDeque ++;
    }

    // add the item to the back
    public void addLast(Item item) {
    	if(item == null) throw new IllegalArgumentException("Cannot add NULL to deque!");
    	Node oldLastNode = last;
    	last = new Node();
    	if(isEmpty()) {
    		first = last;
    	}
    	else {
    		oldLastNode.next = last;
    	}
    	last.content = item;
    	last.previous = oldLastNode;
    	//last.next = null;
    	itemsInDeque ++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
    	if(this.isEmpty()) throw new java.util.NoSuchElementException("Cannot remove when deque is empty");
    	Item removeContent = first.content;
    	first = first.next;
    	itemsInDeque --;
    	if (first == null) {
			last = null;
		}
    	else first.previous =null;
    	return removeContent;
    }

    // remove and return the item from the back
    public Item removeLast() {
    	if(this.isEmpty()) throw new java.util.NoSuchElementException("Cannot remove when deque is empty");
    	Item removeContent = last.content;
    	last = last.previous;
    	itemsInDeque --;
    	if(last == null) {
    		first = null;
    	}
    	else {
    		last.next =null;
    	}
    	return removeContent;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
    	return new dequeIterator();
    }
    
    private class dequeIterator implements Iterator<Item>{

    	Node current = first;
		
		public boolean hasNext() {
			return current != null;
		}

		public Item next() {
			if (!hasNext()) throw new java.util.NoSuchElementException("there are no more items to return!");
			Item item = current.content;
			current = current.next;
			return item;
		}
		
		public void remove() {
			throw new UnsupportedOperationException("Remove is not supported!");
		}
    	
    }

    // unit testing (required)
    public static void main(String[] args) {
    	Deque<String> testDeque = new Deque<String>();
    	System.out.println(testDeque.itemsInDeque);
    	testDeque.addFirst("1");
    	testDeque.removeLast();
    	System.out.println(testDeque.itemsInDeque);
    	System.out.println(testDeque.isEmpty());
    	testDeque.addLast("Java");
    	testDeque.addLast("is");
    	testDeque.addLast("beautiful!");
    	System.out.println(testDeque.isEmpty());
    	for(String input : testDeque) {
    		System.out.print(input+" ");
    	}
    	System.out.println(testDeque.removeLast());	
    	System.out.println(testDeque.removeFirst());
    	System.out.println(testDeque.isEmpty());
    	System.out.println(testDeque.removeFirst());
    	testDeque.addLast("Java");
    	System.out.println(testDeque.removeLast());	
    	System.out.println(testDeque.isEmpty());
    }

}
