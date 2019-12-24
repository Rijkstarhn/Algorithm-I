import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private int opNum; 
	private final int amount;
	private double[] fraction;
	// store the mean and stddev to reduce repeat call when N is large
	private double meanValue = 0.0;
	private double stddevValue = 0.0;
	private final double confidence_95 = 1.96;
	
	// perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
    	if (n <= 0 || trials <= 0) throw new IllegalArgumentException("Oops");
    	opNum = 0;
		amount = n * n;
		fraction = new double[trials];
		for(int i = 0; i < trials; i++) {
    		Percolation p = new Percolation(n);
    		for(int j = 0; j < this.amount; j++) {
    			int row = StdRandom.uniform(1, n +1);
    			int col = StdRandom.uniform(1, n +1);
    			//prevent repeat position of open site
    			while(p.isOpen(row, col)) {
    				row = StdRandom.uniform(1, n +1);
    				col = StdRandom.uniform(1, n +1);
    			} 
    			p.open(row,col);
    			// check percolation when the last row open sites open
    				if (p.percolates()) {
        				this.opNum = p.numberOfOpenSites();
        				this.fraction[i] = this.opNum * 1.0/ this.amount;
        				break;
        		   }
		   }	
		}
		meanValue = StdStats.mean(fraction);
		stddevValue = StdStats.stddev(fraction);
    }

    // sample mean of percolation threshold
    public double mean() {
    	return meanValue;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
    	return stddevValue;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
    	double mt_confidencelo = meanValue - confidence_95 * stddevValue / Math.sqrt(fraction.length);
    	return mt_confidencelo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
    	double mt_confidencehi = meanValue + confidence_95 * stddevValue / Math.sqrt(fraction.length);
    	return mt_confidencehi;
    }

   // test client (see below)
   public static void main(String[] args) {
    	
    	int n = Integer.parseInt(args[0]);
    	int trials = Integer.parseInt(args[1]);
    	PercolationStats ps = new PercolationStats(n,trials);
    	
    	//Stas show
    	StdOut.println("mean= " + ps.meanValue);
    	StdOut.println("stddev= " + ps.stddevValue);
    	StdOut.println("95% confidence interval= [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
}		

}
