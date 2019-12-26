import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
    	return this.y == that.y? (this.x == that.x? Double.NEGATIVE_INFINITY : 0.0) : 
    		(this.x == that.x? Double.POSITIVE_INFINITY : (this.y - that.y) * 1.0 / (this.x - that.x));
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
    	return this.y == that.y? (this.x == that.x? 0 : (this.x < that.x? -1 : 1)) 
    			: (this.y < that.y? -1 : 1);
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
    	return new PointComparator();
    }
    
    private class PointComparator implements Comparator<Point>{
    	public int compare(Point a, Point b) {
    		return (Point.this.slopeTo(a) == Point.this.slopeTo(b))? 0 : (Point.this.slopeTo(a) < Point.this.slopeTo(b))? -1 : 1;
    	}
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    	Point[] testPoint = {new Point(3000, 2000), new Point(3000, 4729), new Point(5000, 2000), new Point(8765, 1234)};
    	Point[] testPoint1 = {new Point(3000, 2000), new Point(2000, 2001), new Point(4000, 2001), new Point(3000, 2001),
    			new Point(2000, 1999), new Point(4000, 1999), new Point(3000, 1999),
    			new Point(2000, 2000), new Point(4000, 2000)};
    	StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
    	for(Point xPoint : testPoint) {
    		System.out.println(testPoint[0].slopeTo(xPoint));
    	}
    	System.out.println("------------------");
    	for(Point xPoint : testPoint1) {
    		System.out.println(testPoint1[0].compareTo(xPoint));
    	}
    	StdDraw.show();
    }
}