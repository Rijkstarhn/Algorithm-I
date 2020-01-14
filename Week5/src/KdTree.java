import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree {
	
	private Node root;
	private int count;
	
	public KdTree() {
		this.root = new Node(null);
		count = 1;
	}
	
	public boolean isEmpty() {
		if (this.count == 1) return true;
		else return false;
	}
	
	public int size() {
		return this.count - 1;
	}
	
	public void insert(Node node) {
		tree.add(node);
	}
	
	
	
	public static void main(String[] args) {
		KdTree tree = new KdTree();
		Point2D p1 = new Point2D(0.1, 0.6);
		Point2D p2 = new Point2D(0.2, 0.4);
		Point2D p3 = new Point2D(0.8, 0.3);
		Node node1 = new Node(p1);
		Node node2 = new Node(p2);
		Node node3 = new Node(p3);
		tree.insert(node1);
		tree.insert(node2);
		tree.insert(node3);
	}
	
}

class Node implements Comparable<Node> {
	private Point2D p;
	private Node lb;
	private Node rt;
	private RectHV rect;
	private boolean orientation;// false vertical; true horizontal
	
	public Node(Point2D inputP) {
		this.p = inputP;
		if (inputP == null) this.rect = new RectHV(0, 0, 1, 1);
	}
	
	public int compareTo(Node that) {
		if (! this.p.equals(that.p)) this.orientation = !that.orientation;
		if (that.orientation) {
			if (this.p.y() < that.p.y()) {
				//that.lb = this;
				return -1;
			}
			else {
				//that.rt = this;
				return 1;
			}
		}
		else {
			if (this.p.x() < that.p.x()) {
				//that.lb = this;
				return -1;
			}
			else {
				//that.rt = this;
				return 1;
			}
		}
	}
}