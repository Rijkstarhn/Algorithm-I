import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private final int range;
	private boolean [] status;
	private int count;
	private final WeightedQuickUnionUF uf;
	private final WeightedQuickUnionUF ufTop;
	
	// creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	if (n <= 0) throw new IllegalArgumentException("n should be greater than 0!");
    	range = n;
    	count  = 0;
    	status = new boolean[n*n+2];
    	uf = new WeightedQuickUnionUF(n*n+2);
    	ufTop = new WeightedQuickUnionUF(n*n+1);
		status[0] = true;
		status[n*n +1] = true;
    }
	// transform row&col to position
    private int position(int row, int col) {
    	int pos = (row-1) * range + col;
    	return pos;
    }
    
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	if (row > range || row <= 0) {
    		throw new IllegalArgumentException("row out of bounds!");
    	}
    	if (col > range || col <= 0) {
    		throw new IllegalArgumentException("column out of bounds!");
    	}
    	
    	// open the site and count +1
    	if (!status[position(row, col)]) {
    		status[position(row, col)] = true;
        	count++;
    	
    	
    	// connect the site to its neighbor open sites
    	int rowUp = row -1; 
    	int colUp = col; 
    	int posUp = position(rowUp, colUp);
    	int rowDown = row +1; 
    	int colDown = col; 
    	int posDown = position(rowDown, colDown);
    	int rowLeft = row; 
    	int colLeft = col -1; 
    	int posLeft = position(rowLeft, colLeft);
    	int rowRight = row; 
    	int colRight = col +1; 
    	int posRight = position(rowRight, colRight);
    	int pos = position(row, col);
    	
    	if (row == 1) {
    		uf.union(0, pos);
    		ufTop.union(0, pos);
    	}
    	if (row == range) {
    		uf.union(pos, range * range + 1);
    	}
    	if (rowUp >= 1 && status[position(rowUp, colUp)]) {
    		uf.union(pos, posUp);
    		ufTop.union(pos, posUp);
    	}
    	if (rowDown <= range && status[position(rowDown, colDown)]) {
    		uf.union(pos, posDown);
    		ufTop.union(pos, posDown);
    	}
    	if (colLeft >= 1 && status[position(rowLeft, colLeft)]) {
    		uf.union(pos, posLeft);
    		ufTop.union(pos, posLeft);
    	}
    	if (colRight <= range && status[position(rowRight, colRight)]) {
    		uf.union(pos, posRight);
    		ufTop.union(pos, posRight);
    	}
    	}
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	if (row > range || row <= 0) {
    		throw new IllegalArgumentException("row out of bounds!");
    	}
    	if (col > range || col <= 0) {
    		throw new IllegalArgumentException("column out of bounds!");
    	}
    	return status[position(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	if (row > range || row <= 0) {
    		throw new IllegalArgumentException("row out of bounds!");
    	}
    	if (col > range || col <= 0) {
    		throw new IllegalArgumentException("column out of bounds!");
    	}
    	if (status[position(row, col)]) {
    		int pos = position(row, col);
    		return ufTop.find(pos) == ufTop.find(0);
    	}
    	else return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
    	return count;
    }

    // does the system percolate?
    public boolean percolates() {
    	return uf.find(0) == uf.find(range * range +1);
    }

    // test client (optional)
	public static void main(String[] args) {
		
		// Initialize 
	}

}