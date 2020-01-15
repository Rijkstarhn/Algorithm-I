import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	
	private Node root;
	private int count;
	public static final boolean LB = true;
	public static final boolean RT = false;
	
	class Node implements Comparable<Node> {
		
		private Point2D p;
		private Node lb;
		private Node rt;
		private RectHV rect;
		private boolean orientation;// false vertical; true horizontal
		
		public Node(Point2D inputP, Node parent, boolean direction) {
			this.p = inputP;
			if (parent == null) this.orientation = false;
			else this.orientation = !parent.orientation;
			if (parent == null) this.rect = new RectHV(0.0, 0.0, 1.0, 1.0);
			else {
				if (parent.orientation) {
					if (direction) this.rect = new RectHV(0.0, 0.0, parent.rect.xmax(), parent.p.y());
					else this.rect = new RectHV(0, parent.p.y(), parent.rect.xmax(), parent.rect.ymax());
				}
				else {
					if (direction) this.rect = new RectHV(0.0, 0.0, parent.p.x(), parent.rect.ymax());
					else this.rect = new RectHV(parent.p.x(), 0.0, parent.rect.xmax(), parent.rect.ymax());
				}
			}
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
		root = insert(node, root, null, false);
	}
	
	private Node insert(Node node, Node root, Node parent, boolean direction) {
		if (root == null) {
			count ++;
			return new Node(node.p, parent, direction);
		}
		if (node.compareTo(root) < 0) root.lb = insert(node, root.lb, root, LB);
		else if (node.compareTo(root) > 0) root.rt = insert(node, root.rt, root, RT);
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
		draw(root, null, false);
	}
	
	private void draw(Node root, Node son, boolean direction) {
		if (root == null) return;
		if (son == null) {
			StdDraw.setPenRadius(0.02);
			StdDraw.setPenColor(StdDraw.BLACK);
			root.p.draw();
			StdDraw.setPenRadius(0.005);
			StdDraw.setPenColor(StdDraw.RED);
			root.p.drawTo(new Point2D(root.p.x(), 0.0));
			root.p.drawTo(new Point2D(root.p.x(), 1.0));
			son = root;
		}
		else {
			StdDraw.setPenRadius(0.02);
			StdDraw.setPenColor(StdDraw.BLACK);
			son.p.draw();
			StdDraw.setPenRadius(0.005);
			if (son.orientation) {
				StdDraw.setPenColor(StdDraw.BLUE);
				if (direction) {
					son.p.drawTo(new Point2D(0.0, son.p.y()));
					son.p.drawTo(new Point2D(root.p.x(), son.p.y()));
				}
				else {
					son.p.drawTo(new Point2D(root.p.x(), son.p.y()));
					son.p.drawTo(new Point2D(1.0, son.p.y()));
				}
			}
			else {
				StdDraw.setPenColor(StdDraw.RED);
				if (direction) {
					son.p.drawTo(new Point2D(son.p.x(), 0.0));
					son.p.drawTo(new Point2D(son.p.x(), root.p.y()));
				}
				else {
					son.p.drawTo(new Point2D(son.p.x(), 1.0));
					son.p.drawTo(new Point2D(son.p.x(), root.p.y()));
				}
				
			}
		}
		if (son.lb != null) draw(son, son.lb, LB);
		if (son.rt != null) draw(son, son.rt, RT);
		return;
	}
	
	
	public static void main(String[] args) {
		KdTree tree = new KdTree();
		Node node1 = tree.new Node(new Point2D(0.7, 0.2),null, false);
		Node node2 = tree.new Node(new Point2D(0.5, 0.4),null, false);
		Node node3 = tree.new Node(new Point2D(0.2, 0.3),null, false);
		Node node4 = tree.new Node(new Point2D(0.4, 0.7),null, false);
		Node node5 = tree.new Node(new Point2D(0.9, 0.6),null, false);
		tree.insert(node1);
		tree.insert(node2);
		tree.insert(node3);
		tree.insert(node4);
		tree.insert(node5);
		System.out.println(tree.contains(tree.new Node(new Point2D(0.9, 0.3),null, false)));
		tree.draw();
	}
	
}

