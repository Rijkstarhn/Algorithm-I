import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;

public class KdTree {
	
	class Node implements Comparable<Node> {
		private Point2D p;
		private Node lb;
		private Node rt;
		private boolean orientation;// false vertical; true horizontal
		
		public Node(Point2D inputP) {
			p = inputP;
		}
		
		public int compareTo(Node that) {
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
	
	private TreeSet<Node> tree;
	
	public void insert(Node node) {
		tree.add(node);
	}
	
	public KdTree() {
		tree = new TreeSet<Node>();
	}
	
}
