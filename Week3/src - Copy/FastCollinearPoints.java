import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	
	private int numberOfSegments;
	private LineSegment[] lineSegment;
	// the two variables below are used for storing the segment points for judging subsegment 
	private double[] slopeOfSegments;
	private Point[] endPoints;
	
	public FastCollinearPoints(Point[] points) {
		if (points == null) throw new IllegalArgumentException("null input!");
		for(Point p : points) {
			if (p == null) throw new IllegalArgumentException("Point is null!");
		}
		for (int i = 0; i < points.length; i++) {
			for (int j = i+1; j < points.length; j++) {
				if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException("Repeat points!");
			}
		}
		
		numberOfSegments = 0;
		lineSegment = new LineSegment[points.length * points.length];
		slopeOfSegments = new double [points.length * points.length];
		endPoints = new Point[points.length * points.length];
		
		// Sort points[] first to avoid repeat calculation,
		// use a copy of points[] in case of 'check that data type does not mutate the constructor argument' problem
		Point[] copyOfPoints = new Point[points.length];
		for (int i = 0; i < copyOfPoints.length; i++) {
			copyOfPoints[i] = points[i];
		}
		Arrays.sort(copyOfPoints);
		
		for (int k = points.length -1; k >= 3; k--) {
			// create a copy array for sorting points[]
			Point[] pointsCopy = new Point[k];
			for (int i = 0; i < k; i++) {
				pointsCopy[i] = copyOfPoints[i];
			}
			Point origin = copyOfPoints[k];
			// Sort the points via slopeOrder
			Arrays.sort(pointsCopy,origin.slopeOrder());
			
			// Calculate the slopes
			double[] slope = new double[pointsCopy.length];
			for (int i = 0; i < slope.length; i++) {
				slope[i] = origin.slopeTo(pointsCopy[i]);
			}
			
			// Find the points with similar slope
			for (int i = 1; i < slope.length; i++) {
				int j = i;
				int count = 1;
				while (j < slope.length && slope[j] == slope[j-1]) {
					count++;
					j++;
				}
				if (count >= 3) {
					boolean noRepeatSegment = true;
					for (int numOfSlope = 0; numOfSlope < numberOfSegments; numOfSlope++) {
						if (slopeOfSegments[numOfSlope] == slope[j-1] && endPoints[numOfSlope].compareTo(pointsCopy[i-1]) == 0) {
							noRepeatSegment = false;
							break;
						}
					}
					if (noRepeatSegment) {
						slopeOfSegments[this.numberOfSegments] = slope[j-1];
						endPoints[this.numberOfSegments] = pointsCopy[i-1];
						lineSegment[this.numberOfSegments++] = new LineSegment(origin, pointsCopy[i-1]);
					}
					i = j;// if two lines are vertical, then in one k-loop, there will be more than one segment
				}
			}
		}
		
	}
	
	// the number of line segments
    public int numberOfSegments() {
    	int copyOfNumberOfSegments = numberOfSegments;
    	return copyOfNumberOfSegments;
    }
   
    // the line segments
    public LineSegment[] segments() {
    	int i = 0;
//    	Arrays.sort(lineSegment);
    	while(lineSegment[i ++] != null);
    	LineSegment[] copyOfNumberOfSegments = new LineSegment[i-1];
    	for (int j = 0; j < copyOfNumberOfSegments.length; j++) {
    		copyOfNumberOfSegments[j] = lineSegment[j];
		}
    	return copyOfNumberOfSegments;
    }
	
	
	public static void main(String[] args) {

	    // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    StdDraw.setPenRadius(0.006);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	        StdDraw.show();
	    }
	    StdDraw.show();
	    System.out.println(collinear.numberOfSegments);
	}
}
