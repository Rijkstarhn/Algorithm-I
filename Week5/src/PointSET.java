import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

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
			p.draw();
		}
	}
	
	// all points that are inside the rectangle (or on the boundary) 
	public Iterable<Point2D> range(RectHV rect) {
		if (rect == null) throw new IllegalArgumentException("rectang should not be null!");
		ArrayList<Point2D> insidePoint = new ArrayList<Point2D>();
		for (Point2D p : this.pointsSet) {
			if (p.x() == 0.0 || p.x() == 1.0 || p.y() == 0.0 || p.y() == 1.0) insidePoint.add(p);
			if (p.x() < rect.xmax() && p.x() > rect.xmin() && p.y() < rect.ymax() && p.y() > rect.ymin()) insidePoint.add(p);
		}
		return insidePoint;
	}
	
	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		if (p == null) throw new IllegalArgumentException("point should not be null!");
		double distance;
		Point2D nearestPoint2d = this.pointsSet.first();
		if (this.pointsSet.isEmpty()) return null;
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
		System.out.println(testSet.isEmpty());
		Point2D point2d = new Point2D(0.2, 0.5);
		testSet.insert(point2d);
		System.out.println(testSet.contains(new Point2D(0.2, 0.1)));
		System.out.println(testSet.contains(new Point2D(0.2, 0.5)));
		System.out.println(testSet.size());
		System.out.println(testSet.isEmpty());
		testSet.insert(new Point2D(0.2, 0.7));
		testSet.draw();
		RectHV r = new RectHV(0.1, 0.1, 0.15, 0.8);
		r.draw();
		for (Point2D p : testSet.range(r)) {
			System.out.println(p.toString());
		}
		System.out.println(testSet.nearest(new Point2D(0.2, 0.55)).toString());
	}
}
