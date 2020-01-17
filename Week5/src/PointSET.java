import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;
import java.util.TreeSet;

public class PointSET {
	
	private TreeSet<Point2D> pointsSet;
	
	// construct an empty set of points
	public PointSET() {
		pointsSet = new TreeSet<Point2D>();
	}
	
	// is the set empty? 
	public boolean isEmpty() {
		return pointsSet.isEmpty();
	}
	
	// number of points in the set
	public int size() {
		return pointsSet.size();
	}
	
	// add the point to the set (if it is not already in the set)
	public void insert(Point2D p) {
		if (p == null) throw new IllegalArgumentException("point should not be null!");
		pointsSet.add(p);
	}
	
	// does the set contain point p? 
	public boolean contains(Point2D p) {
		if (p == null) throw new IllegalArgumentException("point should not be null!");
		return pointsSet.contains(p);
	}
	
	// draw all points to standard draw 
	public void draw() {
		for (Point2D p : this.pointsSet) {
			StdDraw.setPenRadius(0.01);
			p.draw();
		}
	}
	
	// all points that are inside the rectangle (or on the boundary) 
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) throw new IllegalArgumentException("rectangle should not be null!");
		ArrayList<Point2D> insidePoint = new ArrayList<Point2D>();
		for (Point2D p : this.pointsSet) {
			if (p.x() <= rect.xmax() && p.x() >= rect.xmin() && p.y() <= rect.ymax() && p.y() >= rect.ymin()) insidePoint.add(p);
		}
		return insidePoint;
	}
	
	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (p == null) throw new IllegalArgumentException("point should not be null!");
		double distance;
		if (this.pointsSet.isEmpty()) return null;
		Point2D nearestPoint2d = this.pointsSet.first();
		double minDistance = Math.pow(pointsSet.first().x() - p.x(), 2.0) + Math.pow(pointsSet.first().y() - p.y(), 2.0);
		for (Point2D point : this.pointsSet) {
			distance = Math.pow(point.x() - p.x(), 2.0) + Math.pow(point.y() - p.y(), 2.0);
			if (distance < minDistance) {
				minDistance = distance;
				nearestPoint2d = point;
			}
		}
		return nearestPoint2d;
	}
	
	public static void main(String[] args) {
		PointSET testSet = new PointSET();
//		testSet.isEmpty();
//		testSet.size();
//		testSet.contains(new Point2D(1.0, 1.0));
//		Point2D p1 = testSet.nearest(new Point2D(1.0, 1.0));
//		System.out.println(p1);
		
		testSet.insert(new Point2D(0.125, 0.375));
		testSet.insert(new Point2D(0.8125, 0.5));
		RectHV r = new RectHV(0.125, 0.1875, 0.5, 0.9375);
		testSet.draw();
		r.draw();
		for (Point2D p : testSet.range(r)) {
			System.out.println(p.toString());
		}
//		System.out.println(testSet.isEmpty());
//		Point2D p1 = new Point2D(0.1, 0.6);
//		Point2D p2 = new Point2D(0.8, 0.3);
//		Point2D p3 = new Point2D(0.2, 0.4);
//		Point2D p4 = new Point2D(0.5, 0.7);
//		testSet.insert(p1);
//		testSet.insert(p2);
//		testSet.insert(p3);
//		testSet.insert(p4);
//		System.out.println(testSet.contains(new Point2D(0.2, 0.1)));
//		System.out.println(testSet.contains(new Point2D(0.2, 0.4)));
//		System.out.println(testSet.size());
//		System.out.println(testSet.isEmpty());
//		testSet.insert(new Point2D(0.2, 0.7));
//		testSet.draw();
//		RectHV r = new RectHV(0.1, 0.1, 0.15, 0.8);
//		r.draw();
//		for (Point2D p : testSet.range(r)) {
//			System.out.println(p.toString());
//		}
//		System.out.println(testSet.nearest(new Point2D(0.2, 0.55)).toString());
	}
}
