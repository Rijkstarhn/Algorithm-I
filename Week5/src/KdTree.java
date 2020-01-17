import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
	
	private Node root;
	private int count;
	public static final boolean LB = true;
	public static final boolean RT = false;
	private double nearestDistance;
	
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
					if (direction) this.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.rect.xmax(), parent.p.y());
					else this.rect = new RectHV(parent.rect.xmin(), parent.p.y(), parent.rect.xmax(), parent.rect.ymax());
				}
				else {
					if (direction) this.rect = new RectHV(parent.rect.xmin(), parent.rect.ymin(), parent.p.x(), parent.rect.ymax());
					else this.rect = new RectHV(parent.p.x(), parent.rect.ymin(), parent.rect.xmax(), parent.rect.ymax());
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
	
	public void insert(Point2D p) {
		root = insert(p, root, null, false);
	}
	
	private Node insert(Point2D p, Node root, Node parent, boolean direction) {
		if (root == null) {
			count ++;
			return new Node(p, parent, direction);
		}
		if (root.orientation) {
			if (p.y() < root.p.y()) root.lb = insert(p, root.lb, root, LB);
			else root.rt = insert(p, root.rt, root, RT);
		}
		else {
			if (p.x() < root.p.x()) root.lb = insert(p, root.lb, root, LB);
			else root.rt = insert(p, root.rt, root, RT);
		}
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
	
	private void draw(Node son) {
		
		if (root == null) return;
		
		StdDraw.setPenRadius(0.02);
		StdDraw.setPenColor(StdDraw.BLACK);
		son.p.draw();
		StdDraw.setPenRadius(0.005);
		if (son.orientation) {
			StdDraw.setPenColor(StdDraw.BLUE);
			son.p.drawTo(new Point2D(son.rect.xmin(), son.p.y()));
			son.p.drawTo(new Point2D(son.rect.xmax(), son.p.y()));
		}
		else {
			StdDraw.setPenColor(StdDraw.RED);
			son.p.drawTo(new Point2D(son.p.x(), son.rect.ymin()));
			son.p.drawTo(new Point2D(son.p.x(), son.rect.ymax()));			
		}
		
		if (son.lb != null) draw(son.lb);
		if (son.rt != null) draw(son.rt);
		
		return;
	}
	
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) throw new IllegalArgumentException("rectangle should not be null!");
		ArrayList<Point2D> insideNode = new ArrayList<Point2D>();
		range(rect, root, insideNode);
		return insideNode;
		}
	
	private void range(RectHV rect, Node root, ArrayList<Point2D> insideNode) {
		if (root == null) return;
		if (rect.contains(root.p)) insideNode.add(root.p);
		if (rect.intersects(root.rect)) {
			range(rect, root.lb, insideNode);
			range(rect, root.rt, insideNode);
		}
		else return;
	}
	
	public Point2D nearest(Point2D query) {
		if (query == null) throw new IllegalArgumentException("query point should not be null!");
		if (root == null) return null;
		// Initialize the nearestDistance
		nearestDistance = query.distanceSquaredTo(root.p);
		Point2D nearestPoint =  nearest(query, root, root.p);
		return nearestPoint;
	}
	
//	private Point2D nearest(Point2D query, Node root, Point2D nearest, double nearestDistance) {
//		if (root == null) return nearest;
//		double distance;
//		distance = query.distanceSquaredTo(root.p);
//		if (distance <= nearestDistance) {
//			nearestDistance = distance;
//			nearest = root.p;
//		}
//		if (root.lb != null && root.rt != null) {
//			if (root.lb.rect.distanceSquaredTo(query) < nearestDistance) {
//				if (root.rt.rect.distanceSquaredTo(query) < nearestDistance) {
//					if (root.orientation) {
//						// if query is closer to the lb, then search lb first
//						if (query.y() - root.p.y() < 0) {
//							nearest = nearest(query, root.lb, nearest, nearestDistance);
//							nearest = nearest(query, root.rt, nearest, nearestDistance);
//							}
//						// if query is closer to the rt, then search rt first
//						else {
//							nearest = nearest(query, root.rt, nearest, nearestDistance);
//							nearest = nearest(query, root.lb, nearest, nearestDistance);
//						}
//					}
//					else {
//						// if query is closer to the lb, then search lb first
//						if (query.x() - root.p.x() < 0) {
//							nearest = nearest(query, root.lb, nearest, nearestDistance);
//							nearest = nearest(query, root.rt, nearest, nearestDistance);
//						}
//						// if query is closer to the rt, then search rt first
//						else {
//							nearest = nearest(query, root.rt, nearest, nearestDistance);
//							nearest = nearest(query, root.lb, nearest, nearestDistance);
//						}
//					}
//				}
//				else nearest = nearest(query, root.lb, nearest, nearestDistance);
//			}
//			else if (root.rt.rect.distanceSquaredTo(query) < nearestDistance) nearest = nearest(query, root.rt, nearest, nearestDistance);
//		}
//		else if (root.lb != null && root.rt == null) nearest = nearest(query, root.lb, nearest, nearestDistance);
//		else if (root.lb == null && root.rt != null) nearest = nearest(query, root.rt, nearest, nearestDistance);
//		return nearest;
//	}
	
	private Point2D nearest(Point2D query, Node root, Point2D nearest) {
		if (root == null) return nearest;
		double distance;
		distance = query.distanceSquaredTo(root.p);
		if (distance <= nearestDistance) {
			nearestDistance = distance;
			nearest = root.p;
		}
		if (root.lb != null && root.rt != null) {
			if (root.lb.rect.distanceSquaredTo(query) < nearestDistance) {
				if (root.rt.rect.distanceSquaredTo(query) < nearestDistance) {
					if (root.orientation) {
						// if query is closer to the lb, then search lb first
						if (query.y() - root.p.y() < 0) {
							nearest = nearest(query, root.lb, nearest);
							nearest = nearest(query, root.rt, nearest);
							}
						// if query is closer to the rt, then search rt first
						else {
							nearest = nearest(query, root.rt, nearest);
							nearest = nearest(query, root.lb, nearest);
						}
					}
					else {
						// if query is closer to the lb, then search lb first
						if (query.x() - root.p.x() < 0) {
							nearest = nearest(query, root.lb, nearest);
							nearest = nearest(query, root.rt, nearest);
						}
						// if query is closer to the rt, then search rt first
						else {
							nearest = nearest(query, root.rt, nearest);
							nearest = nearest(query, root.lb, nearest);
						}
					}
				}
				else nearest = nearest(query, root.lb, nearest);
			}
			else if (root.rt.rect.distanceSquaredTo(query) < nearestDistance) nearest = nearest(query, root.rt, nearest);
		}
		else if (root.lb != null && root.rt == null) nearest = nearest(query, root.lb, nearest);
		else if (root.lb == null && root.rt != null) nearest = nearest(query, root.rt, nearest);
		return nearest;
	}
	
	public static void main(String[] args) {
		// initialize the two data structures with point from file
        String filename = args[0];
        In in = new In(filename);
        KdTree tree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            tree.insert(p);
        }

		System.out.println(tree.contains(tree.new Node(new Point2D(0.9, 0.3),null, false)));
		tree.draw();
		Iterable<Point2D> a = new ArrayList<Point2D>();
		RectHV r = new RectHV(0.372, 0.497, 0.7, 0.8);
		a = tree.range(r);
		r.draw();
		for (Point2D node : a) {
			System.out.println(node);
		}
		Point2D p = new Point2D(0.2, 0.2);
		p.draw();
		System.out.println("nearest:");
		System.out.println(tree.nearest(p));
	}
	
}

