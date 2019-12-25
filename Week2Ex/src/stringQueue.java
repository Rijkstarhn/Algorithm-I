
public class stringQueue {

	private class node {
		String context;
		node next;
	}
	
	private node first = null;
	private node last = null;
	
	public String dequeue() {
		String deqContext = first.context;
		first = first.next;
		return deqContext;
	}
	
	public void enqueue(String enqContext) {
		node oldLast = last;
		last = new node();
		last.context = enqContext;
		last.next = null;
		if(isEmpty()) {
			first = last;
		}
		else oldLast.next = last;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public static void main(String[] args) {
		stringQueue test = new stringQueue();
		test.enqueue("1");
		System.out.println(test.isEmpty());
		System.out.println(test.dequeue());
		System.out.println(test.isEmpty());
	}

}
