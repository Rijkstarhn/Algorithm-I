import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;

public class Board {

	private final int[] board;
	private final int dimension;
	private int zeroX;
	private int zeroY;
	
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
    	this.board = new int[tiles.length * tiles.length];
    	this.dimension = tiles.length;
    	for (int i = 0; i < tiles.length; i++) 
			for (int j = 0; j < tiles.length; j++) {
				board[pos(i, j)] = tiles[i][j];
				if (tiles[i][j] == 0) {
					zeroX = i;
					zeroY = j;
				}
			}
    }
                                           
    // string representation of this board
    public String toString() {
    	String outString = Integer.toString(this.dimension) + "\r\n";
    	for (int i = 0; i < this.dimension; i++) {
			for (int j = 0; j < this.dimension; j++) {
				outString += Integer.toString(this.board[pos(i, j)]);
				outString += " ";
			}
			outString += "\r\n"; 
		}
    	return outString;
    }

    // board dimension n
    public int dimension() {
    	return this.dimension;
    }

    // number of tiles out of place
    public int hamming() {
    	int count = 0;
    	for (int i = 0; i < this.dimension; i++) {
			for (int j = 0; j < this.dimension; j++) {
				if (this.board[pos(i, j)] == 0) continue;
				if (this.board[pos(i, j)] != goalPos(i, j)) {
					count ++;
				}
			}
		}
    	return count;
    }
    
    // the position of the entry in the array, e.g. 
    private int pos(int i, int j) {
    	return this.dimension* i + j;
    }
    
    private int goalPos(int i, int j) {
    	return this.dimension * i + j + 1;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
    	int count = 0;
    	// Must hamming first, or the manhattanDistance will be 0 because we need the hamming[i][j] for manhattanDistance calculation
    	for (int i = 0; i < this.dimension; i++) {
			for (int j = 0; j < this.dimension; j++) {
				if (this.board[pos(i, j)] == 0) continue;
				if (this.board[pos(i, j)] != goalPos(i, j)) {
					count += Math.abs(this.posX(this.board[pos(i, j)] - 1) - i) + Math.abs(this.posY(this.board[pos(i, j)] - 1)-j);// this.board[i][j] - 1 because the entry of goal board starts from 1 instead of 0 
				}
			}
		}
    	return count;
    }
    
    // the X position of the entry
    private int posX(int entry) {
    	return entry / this.dimension;
    }

    // the Y position of the entry
    private int posY(int entry) {
    	return entry % this.dimension;
    }
    
    // is this board the goal board?
    public boolean isGoal() {
    	int[] goal = new int[this.board.length];
    	for (int i = 0; i < goal.length - 1; i++) {
			goal[i] = i + 1;
		}
    	goal[goal.length - 1] = 0;
    	return Arrays.equals(this.board, goal);
    }

    // does this board equal y?
    public boolean equals(Object y) {
    	if (y == null || this == null) return false;
    	if (this.getClass() != y.getClass()) return false;
    	Board yBoard = (Board)y;
    	if (this.board.length != yBoard.board.length) return false;
    	if (!Arrays.equals(this.board, yBoard.board)) return false;
    	return true;
    }
    
    // all neighboring boards
    public Iterable<Board> neighbors(){
    	ArrayList<Board> neighbors = new ArrayList<Board>();
    	if (zeroX - 1 >= 0) {
    		int[] neighborOneD = Arrays.copyOf(this.board, this.board.length);
    		int[][] neighborTwoD = convertToTwoD(neighborOneD);
    		exch(neighborTwoD, zeroX, zeroY, zeroX - 1, zeroY); // exch first, or the ZeroX and ZeroY will be wrong ( be the before exch Board's ZeroX and ZeroY)
    		Board neighbor = new Board(neighborTwoD);// use the copy of thisBoard's board array to initialize the neighbor board
    		neighbors.add(neighbor);
    	}
    	if (zeroX + 1 < this.dimension) {
    		int[] neighborOneD = Arrays.copyOf(this.board, this.board.length);
    		int[][] neighborTwoD = convertToTwoD(neighborOneD);
    		exch(neighborTwoD, zeroX, zeroY, zeroX + 1, zeroY); // exch first, or the ZeroX and ZeroY will be wrong ( be the before exch Board's ZeroX and ZeroY)
    		Board neighbor = new Board(neighborTwoD);// use the copy of thisBoard's board array to initialize the neighbor board
    		neighbors.add(neighbor);
    	}
    	if (zeroY - 1 >= 0) {
    		int[] neighborOneD = Arrays.copyOf(this.board, this.board.length);
    		int[][] neighborTwoD = convertToTwoD(neighborOneD);
    		exch(neighborTwoD, zeroX, zeroY - 1, zeroX, zeroY); // exch first, or the ZeroX and ZeroY will be wrong ( be the before exch Board's ZeroX and ZeroY)
    		Board neighbor = new Board(neighborTwoD);// use the copy of thisBoard's board array to initialize the neighbor board
    		neighbors.add(neighbor);
    	}
    	if (zeroY + 1 < this.dimension) {
    		int[] neighborOneD = Arrays.copyOf(this.board, this.board.length);
    		int[][] neighborTwoD = convertToTwoD(neighborOneD);
    		exch(neighborTwoD, zeroX, zeroY + 1, zeroX, zeroY); // exch first, or the ZeroX and ZeroY will be wrong ( be the before exch Board's ZeroX and ZeroY)
    		Board neighbor = new Board(neighborTwoD);// use the copy of thisBoard's board array to initialize the neighbor board
    		neighbors.add(neighbor);
    	}
    	return neighbors;
    }
    
    
    
    // Convert from 1D array to 2D array
    private int[][] convertToTwoD(int[] board) {
    	int [][] twoDForm = new int[this.dimension][this.dimension];
    	for (int k = 0; k < board.length; k++) {
			int i = k / this.dimension;
			int j = k % this.dimension;
			twoDForm[i][j] = board[k];
		}
    	return twoDForm;
    }
    
    private void exch(int[][] copyboard, int xa, int ya, int xb, int yb) {
    	
    	int mid = copyboard[xa][ya];
    	copyboard[xa][ya] = copyboard[xb][yb];
    	copyboard[xb][yb] = mid;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
    	int xa;
    	int ya;
    	int xb;
    	int yb;
    	if (this.board[pos(0, 0)] != 0) {
    		xa = 0;
    		ya = 0;
    		if (this.board[pos(0, 1)] != 0) {
    			xb = 0;
    			yb = 1;
    		}
        	else {
        		xb = 1;
        		yb = 0;
        	}
    	}	
    	else {
    		xa = 0;
    		ya = 1;
    		xb = 1;
    		yb = 0;
    	}
    	int[] copy = Arrays.copyOf(this.board, this.board.length);
    	int[][] twinboard = convertToTwoD(copy);
    	exch(twinboard, xa, ya, xb, yb);
    	Board twin = new Board(twinboard);
    	return twin;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
    	// create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);
        System.out.println(initial.hamming());
        System.out.println(initial.hamming());
        System.out.println(initial.manhattan());
        System.out.println(initial.manhattan());
        System.out.println(initial.toString());
        System.out.println(initial.isGoal());
        Iterable<Board> xArrayList = initial.neighbors();
        for (Board board : xArrayList) {
        	System.out.println(board.toString());
        }
        Board twin = initial.twin();
        System.out.println("twin\n" + twin.toString());
    }

}