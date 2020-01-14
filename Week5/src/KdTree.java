import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
	
	private Node root;
	private int count;
	
	class Node implements Comparable<Node> {
		
		private Point2D p;
		private Node lb;
		private Node rt;
		private RectHV rect;
		private boolean orientation;// false vertical; true horizontal
		
		public Node(Point2D inputP, boolean orientation) {
			this.p = inputP;
			this.orientation = !orientation;
			//if (inputP == null) this.rect = new RectHV(0, 0, 1, 1);
		}
		
		public int compareTo(Node that) {
			if (this.p.y() == that.p.y() && this.p.x() == that.p.x()) return 0;
			if (that.orientation) {
				if (this.p.y() < that.p.y()) return -1;
				else return 1;
			}
			else {
				if (this.p.x() < that.p.x()) return -1;
				else return 1;
			}
		}
	}
	
	public KdTree() {
		root = null;
		count = 0;
	}
	
	public boolean isEmpty() {
		if (this.count == 0) return true;
		else return false;
	}
	
	public int size() {
		return this.count;
	}
	
	public void insert(Node node) {
		root = insert(node, root, true);
	}
	
	private Node insert(Node node, Node root, boolean orientation) {
		if (root == null) {
			count ++;
			return new Node(node.p, orientation);
		}
		if (node.compareTo(root) < 0) root.lb = insert(node, root.lb, root.orientation);
		else if (node.compareTo(root) > 0) root.rt = insert(node, root.rt, root.orientation);
		return root;
	}
	
	public boolean contains(Node node) {
		Node x = root;
		while (x != null) {
			if (node.compareTo(x) < 0) x = x.lb;
			else if (node.compareTo(x) > 0) x = x.rt;
			else return true;
		}
		return false;
	}
	
	public void draw() {
		draw(root);
	}
	
	private void draw(Node root) {
		if (root == null) return;
		if (root.lb != null) draw(root.lb);
	}
	
	
	public static void main(String[] args) {
		KdTree tree = new KdTree();
		Node node1 = tree.new Node(new Point2D(0.7, 0.2),false);
		Node node2 = tree.new Node(new Point2D(0.5, 0.4),false);
		Node node3 = tree.new Node(new Point2D(0.2, 0.3),false);
		Node node4 = tree.new Node(new Point2D(0.4, 0.7),false);
		Node node5 = tree.new Node(new Point2D(0.9, 0.6),false);
		tree.insert(node1);
		tree.insert(node2);
		tree.insert(node3);
		tree.insert(node4);
		tree.insert(node5);
		System.out.println(tree.contains(tree.new Node(new Point2D(0.9, 0.3),false)));
	}
	
}

