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
		pointsSet.add(p);
	}
	
	// does the set contain point p? 
	public boolean contains(Point2D p) {
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
		ArrayList<Point2D> insidePoint = new ArrayList<Point2D>();
		for (Point2D p : this.pointsSet) {
			if (p.x() == 0.0 || p.x() == 1.0 || p.y() == 0.0 || p.y() == 1.0) insidePoint.add(p);
			if (p.x() < rect.xmax() && p.x() > rect.xmin() && p.y() < rect.ymax() && p.y() > rect.ymin()) insidePoint.add(p);
		}
		return insidePoint;
	}
	
	// a nearest neighbor in the set to point p; null if the set is empty
	public Point2D nearest(Point2D p) {
		double distance = 4.0;
		Point2D nearestPoint2d;
		for (Point2D point : this.pointsSet) {
			
		}
	}
	
	public static void main(String[] args) {
		PointSET testSet = new PointSET();
		System.out.println(testSet.isEmpty());
		Point2D point2d = new Point2D(0.2, 0.5);
		testSet.insert(point2d);
		testSet.insert(new Point2D(0.2, 0.5));
		System.out.println(testSet.size());
		System.out.println(testSet.isEmpty());
		testSet.insert(new Point2D(0.2, 0.7));
		testSet.draw();
		RectHV r = new RectHV(0.1, 0.1, 0.3, 0.8);
		r.draw();
		for (Point2D p : testSet.range(r)) {
			System.out.println(p.toString());
		}
	}
}
