import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
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
	
	public static void main(String[] args) {
		PointSET testSet = new PointSET();
		Point2D point2d = new Point2D(0.2, 0.5);
		testSet.insert(point2d);
		testSet.insert(new Point2D(0.2, 0.5));
	}
}
