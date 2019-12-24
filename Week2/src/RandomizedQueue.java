import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private int numOfItems;
	private Item[] RQ;
	
    // construct an empty randomized queue
    public RandomizedQueue() {
    	numOfItems = 0;
    	RQ = (Item[])new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
    	return numOfItems == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
    	return numOfItems;
    }

    // add the item
    public void enqueue(Item item) {
    	if(item == null) throw new IllegalArgumentException("NULL argument not allowed!");
    	if (numOfItems >= RQ.length) {
    		resize(numOfItems * 2);
    	}
    	RQ[numOfItems++] = item;
    }
    
    private void resize(int N) {
    	Item[] resizeRQ = (Item[]) new Object[N];
    	for(int i = 0; i < numOfItems; i++) {
    		resizeRQ[i] = RQ[i];
    	}
    	RQ = resizeRQ;
    }
    
    // remove and return a random item
    public Item dequeue() {
    	if(isEmpty()) throw new java.util.NoSuchElementException("Queue is empty!");
    	int currentPosition = StdRandom.uniform(numOfItems);
    	Item dequeueItem = RQ[currentPosition];
    	if(numOfItems * 4 < RQ.length) resize(RQ.length / 2);
    	RQ[currentPosition] = RQ[--numOfItems];
    	return dequeueItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
    	if(isEmpty()) throw new java.util.NoSuchElementException("Queue is empty!");
    	int currentPosition = StdRandom.uniform(numOfItems);
    	Item sampleItem = RQ[currentPosition]; 
    	return sampleItem;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
    	return new RandomizedQueueIterator();
    }
    
    private class RandomizedQueueIterator implements Iterator<Item>{
    	
    	int numberOfNext = 0;
    	int [] randomOrder;
    	
    	RandomizedQueueIterator() {
    		randomOrder = new int[numOfItems];
    		for(int i = 0; i < numOfItems; i++) randomOrder[i] = i;
    		StdRandom.shuffle(randomOrder);
    	}
    	
    	public boolean hasNext() {
			return numberOfNext < numOfItems;
		}

		public Item next() {
			if(!hasNext()) throw new java.util.NoSuchElementException("there are no more items to return!");
	    	return RQ[randomOrder[numberOfNext++]];
		}
		
		public void remove() {
			throw new java.lang.UnsupportedOperationException("Remove is not supported!");
		}
    }

    // unit testing (required)
    public static void main(String[] args) {
    	/*
    	RandomizedQueue<Integer> testRQ = new RandomizedQueue<Integer>();
    	System.out.println(testRQ.isEmpty());
    	for(int i = 0; i < 10; i++) {
    		testRQ.enqueue(i);
    	}
    	System.out.println(testRQ.isEmpty());
    	System.out.println(testRQ.size());
    	for(int i = 0; i < 10; i++) {
    		System.out.println(testRQ.sample());
    	}
    	System.out.println("end of sample");
    	System.out.println(testRQ.dequeue());
    	for(int k : testRQ) {
    		System.out.println(k);
    	}
    	System.out.println("end of test 1");
  */  	
    	/*
    	RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
    	         rq.size();     
    	         rq.isEmpty();    
    	         rq.isEmpty();
    	         rq.size();
    	         rq.isEmpty();
    	         rq.enqueue(472);
    	         rq.dequeue();
    	         rq.enqueue(26);
    	         */
    	int n = 5;
    	RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
    	for (int i = 0; i < n; i++)
    	    queue.enqueue(i);
    	for (int a : queue) {
    	    for (int b : queue)
    	        StdOut.print(a + "-" + b + " ");
    	    StdOut.println();
    	}
    }

}