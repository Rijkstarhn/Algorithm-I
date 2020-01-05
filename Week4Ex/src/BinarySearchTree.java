
public class BinarySearchTree <Key extends Comparable<Key>, Value>{
	
	private Node root;
	
	private class Node{
		private Key key;
		private Value value;
		private Node left, right;
		
		public Node(Key key, Value value){
			this.key = key;
			this.value = value;
		}
	}
	
	public void put(Key key, Value value) {
		root = put(root, key, value);
	}
	
	private Node put(Node node, Key key, Value value) {
		if (node == null) return new Node(key, value);
		if (key.compareTo(node.key) < 0) node.left = put(node.left, key, value);
		if (key.compareTo(node.key) > 0) node.right = put(node.right, key, value);
		if (key.compareTo(node.key) == 0) node.value = value;
		return node;
	}
	
	public Value get(Key key) {
		Node currentNode = root;
		while(currentNode != null) {
			if (key.compareTo(currentNode.key) < 0) currentNode = root.left;
			if (key.compareTo(currentNode.key) > 0) currentNode = root.right;
			if (key.compareTo(currentNode.key) == 0) return currentNode.value;
		}
		return null;
	}
	
	public void delete(Key key) {
		
	}
	
//	public Iterable<Key> iterator() {
//		
//	}
	
	public static void main(String[] args) {
		BinarySearchTree<Integer, String> testBST = new BinarySearchTree<Integer, String>();
		testBST.put(0, "Java");
		
	}
}
