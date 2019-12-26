import java.util.Arrays;

import edu.princeton.cs.algs4.Count;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;


public class BruteCollinearPoints {
	
	private LineSegment[] lineSegment;
	private int numberOfSegments = 0;
	
	// finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		if (points == null) throw new IllegalArgumentException("null input!");
		for(Point p : points) {
			if (p == null) throw new IllegalArgumentException("Point is null!");
		}
		for (int i = 0; i < points.length; i++) {
			for (int j = i+1; j < points.length; j++) {
				if (points[i].compareTo(points[j]) == 0) throw new IllegalArgumentException("Repeat points!");
			}
		}
		lineSegment = new LineSegment[points.length * points.length];
		for (int i = 0; i < points.length; i++) {
			for (int j = i+1; j < points.length; j++) {
				for (int j2 = j+1; j2 < points.length; j2++) {
					for (int k = j2+1; k < points.length; k++) {
						if (points[i].slopeTo(points[k]) == points[i].slopeTo(points[j2]) && 
								points[i].slopeTo(points[j2]) == points[i].slopeTo(points[j])) {
							Point[] sameSlopePoints = new Point[4];
							sameSlopePoints[0] = points[i];
							sameSlopePoints[1] = points[j];
							sameSlopePoints[2] = points[j2];
							sameSlopePoints[3] = points[k];
							Arrays.sort(sameSlopePoints);
							LineSegment segment = new LineSegment(sameSlopePoints[0], sameSlopePoints[3]);
							lineSegment[this.numberOfSegments++] = segment;
						}		
					}
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
    	while(lineSegment[i ++] != null);
    	LineSegment[] copyOfNumberOfSegments = new LineSegment[i-1];
    	for (int j = 0; j < copyOfNumberOfSegments.length; j++) {
    		copyOfNumberOfSegments[j] = lineSegment[j];
		}
    	return copyOfNumberOfSegments;
    }
    
//    public static void main(String[] args) {
//
//        // read the n points from a file
//        In in = new In(args[0]);
//        int n = in.readInt();
//        Point[] points = new Point[n];
//        for (int i = 0; i < n; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
//        }
//
//        // draw the points
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (Point p : points) {
//            p.draw();
//        }
//        StdDraw.show();
//        
//        BruteCollinearPoints brutecolliniar = new BruteCollinearPoints(points);
//        System.out.println(brutecolliniar.numberOfSegments);
//        for (LineSegment segment : brutecolliniar.segments()) {
//        	System.out.println(segment);
//        	segment.draw();
//        	StdDraw.show();
//        }
        
        // print and draw the line segments
//        FastCollinearPoints collinear = new FastCollinearPoints(points);
//        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
//            segment.draw();
//        }
//        StdDraw.show();
//    }
}