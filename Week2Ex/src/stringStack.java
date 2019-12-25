
public class stringStack {
	
	private class node{
		String context;
		node next;
	}
	
	private node first = null;
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void push(String input) {
		node oldFirst = first;
		first = new node();
		first.context = input;
		first.next = oldFirst;
	}
	
	public String pop() {
		String popContext = first.context;
		first = first.next;
		return popContext;
	}
	
	public static void main(String args[]) {
		stringStack ssOne = new stringStack();
		ssOne.push("a");
		ssOne.push("b");
		ssOne.push("c");
		System.out.println(ssOne.pop());
		System.out.println(ssOne.pop());
		System.out.println(ssOne.pop());
		System.out.println(ssOne.isEmpty());
	}
}


//public class Stack<Item> implements Iterable<Item> {
//	public Iterator<Item> iterator1(){
//		return new ReverseArrayIterator();
//	}
//	
//	private class Node() {
//		Item item;
//		
//	}
//	
//	private class ReverseArrayIterator implements Iterator<Item>{
//		private Node current = first;
//		
//		public boolean hasNext() {
//			return current != null;
//		}
//		public void remove() {
//			//
//		}
//		public Item next() {
//			Item item = current.item;
//			current = current.next;
//			return item;
//		}
//	}
//
//}
