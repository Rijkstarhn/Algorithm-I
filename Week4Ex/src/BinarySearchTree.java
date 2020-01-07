import edu.princeton.cs.algs4.Queue;

public class BinarySearchTree <Key extends Comparable<Key>, Value>{
	
	private Node root;
	
	private class Node{
		private Key key;
		private Value value;
		private Node left, right;
		private int count;
		
		public Node(Key key, Value value, int size){
			this.key = key;
			this.value = value;
			this.count = size;
		}
	}
	
	public void put(Key key, Value value) {
		root = put(root, key, value);
	}
	
	public int size() {
		return size(root);
	}
	
	private int size(Node node) {
		if (node == null) return 0;
		return node.count;
	}
	
	private Node put(Node node, Key key, Value value) {
		if (node == null) return new Node(key, value, 1);
		if (key.compareTo(node.key) < 0) node.left = put(node.left, key, value);
		else if (key.compareTo(node.key) > 0) node.right = put(node.right, key, value);
		else node.value = value;
		node.count = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	public Value get(Key key) {
		Node currentNode = root;
		while(currentNode != null) {
			if (key.compareTo(currentNode.key) < 0) currentNode = currentNode.left;
			else if (key.compareTo(currentNode.key) > 0) currentNode = currentNode.right;
			else return currentNode.value;
		}
		return null;
	}
	
	public Key floor(Key key) {
		Node currentNode = floor(root, key);
		if (currentNode == null) return null;
		return currentNode.key;
	}
	
	private Node floor(Node node, Key key) {
		if (node == null) return null;
		if (key.compareTo(node.key) < 0) return floor(node.left, key);
		else if (key.compareTo(node.key) == 0) return node;
		else {
			Node t = floor(node.right, key);
			if (t != null) return t;
			else return node;
		}
	}
	
	public Key ceiling(Key key) {
		Node currentNode = ceiling(root, key);
		if (currentNode == null) return null;
		return currentNode.key;
	}
	
	private Node ceiling(Node node, Key key) {
		if (node == null) return null;
		if (key.compareTo(node.key) > 0) return ceiling(node.right, key);
		else if (key.compareTo(node.key) == 0) return node;
		else {
			Node tNode = ceiling(node.left, key);
			if (tNode != null) return tNode;
			else return node;
		}
	}
	
	public Key minimum() {
		return minimum(root).key;
	}
	
	private Node minimum(Node node) {
		if (node.left == null) return node;
		return minimum(node.left);
	}
	
	public Key maximum() {
		return maximum(root).key;
	}
	
	private Node maximum(Node node) {
		if (node.right == null) return node;
		return maximum(node.right);
	}
	
	public void deleteMin() {
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node node) {
		if (node.left == null) return node.right;
		node.left = deleteMin(node.left);
		node.count = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	public void deleteMax() {
		root = deleteMax(root);
	}
	
	private Node deleteMax(Node node) {
		if (node.right == null) return node.left;
		node.right = deleteMax(node.right);
		node.count = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	public void delete(Key key) {
		root = delete(root, key);
	}
	
	private Node delete(Node node, Key key) {
		if (node == null) return null;
		if (key.compareTo(node.key) < 0) node.left = delete(node.left, key);
		else if (key.compareTo(node.key) > 0) node.right = delete(node.right, key);
		else {
			if (node.right == null) return node.left;
			if (node.left == null) return node.right;
			Node t = node;
			node = minimum(t.right);
			node.right = deleteMin(t.right);
			node.left = t.left;
		}
		node.count = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	// Return the number of keys in the symbol table strictly less than key
	public int rank(Key key) {
		return rank(root, key);
	}
	
	private int rank(Node node, Key key) {
		if (node == null) return 0;
		if (key.compareTo(node.key) < 0) return rank(node.left, key);
		else if (key.compareTo(node.key) > 0) return 1 + size(node.left) + rank(node.right, key);
		else return size(node.left);
	}
	
	// return the (k+1)st smallest key in the symbol table.
	public Key select(int rank) {
		return select(root, rank);
	}
	
	private Key select(Node node, int rank) {
		if (node == null) return null;
		if (rank < size(node.left)) return select(node.left, rank);
		else if (rank > size(node.left)) return select(node.right, rank - size(node.left) - 1);
		else return node.key;
	}
	
	public Iterable<Key> keys() {
		Queue<Key> q = new Queue<Key>();
		inOrder(root,q);
		return q;
	}
	
	private void inOrder(Node node, Queue<Key> q) {
		if (node == null) return;
		inOrder(node.left, q);
		q.enqueue(node.key);
		inOrder(node.right,q);
	}
	
	
	public static void main(String[] args) {
		BinarySearchTree<Integer, Integer> testBST = new BinarySearchTree<Integer, Integer>();
//		System.out.println(testBST.minimum());
//		System.out.println(testBST.maximum());
		int[] input = new int[5];
		for (int i = 0; i < 5; i++) {
			input[i] = 5 - i;
		}
		for (int i = 0; i < 5; i++) {
			testBST.put(i, input[i]);
		}
		System.out.println(testBST.get(4));
		System.out.println(testBST.minimum());
		System.out.println(testBST.maximum());
		System.out.println(testBST.floor(-1));
		System.out.println(testBST.ceiling(19));
		System.out.println(testBST.get(15));
		System.out.println(testBST.size());
		System.out.println(testBST.rank(0));
		System.out.println(testBST.select(3));
		System.out.println("-------");
		testBST.delete(3);
		for (Integer k : testBST.keys()) {
			System.out.println(k);
		}
		System.out.println("-------");
		testBST.deleteMin();
		for (Integer k : testBST.keys()) {
			System.out.println(k);
		}
		System.out.println("-------");
		testBST.deleteMax();
		for (Integer k : testBST.keys()) {
			System.out.println(k);
		}
	}
}
