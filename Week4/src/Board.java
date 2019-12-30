
import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;

public class Board {

	private int[][] board;
	private int dimension;
	private int hammingDistance;
	private boolean[][] hamming;
	private int zeroX;
	private int zeroY;
	
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
    	this.board = tiles;
    	this.dimension = tiles.length;
    	this.hamming = new boolean[this.dimension][this.dimension];
    	for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board.length; j++) {
				if (board[i][j] == 0) {
					zeroX = i;
					zeroY = j;
				}
			}
		}
    }
                                           
    // string representation of this board
    public String toString() {
    	String outString = Integer.toString(this.dimension) + "\r\n";
    	for (int i = 0; i < this.dimension; i++) {
			for (int j = 0; j < this.dimension; j++) {
				outString += Integer.toString(this.board[i][j]);
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
    	for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				if (this.board[i][j] == 0) continue;
				if (this.board[i][j] != pos(i, j)) {
					this.hammingDistance ++;
					this.hamming[i][j] = true;
				}
			}
		}
    	return this.hammingDistance;
    }
    
    // the position of the entry in the array, e.g. 
    private int pos(int i, int j) {
    	return this.dimension()* i + j+1;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
    	int manhattanDistance = 0;
    	this.hamming();// Must hamming first, or the manhattanDistance will be 0 because we need the hamming[i][j] for manhattanDistance calculation
    	for (int i = 0; i < this.hamming.length; i++) {
			for (int j = 0; j < this.board.length; j++) {
				if (this.board[i][j] == 0) continue;
				if (this.hamming[i][j]) {
					manhattanDistance += Math.abs(this.posX(this.board[i][j] - 1) - i) + Math.abs(this.posY(this.board[i][j] - 1)-j);// this.board[i][j] - 1 because the entry of goal board starts from 1 instead of 0 
				}
			}
		}
    	return manhattanDistance;
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
    	int[][] goal = new int[this.board.length][this.board.length];
    	for (int i = 0; i < goal.length; i++) {
			for (int j = 0; j < goal.length; j++) {
					goal[i][j] = i * this.dimension + j + 1;
			}
		}
    	goal[goal.length -1][goal.length -1] = 0;
    	return this.equals(new Board(goal));
    }

    // does this board equal y?
    public boolean equals(Object y) {
    	Board yBoard = (Board)y;
    	if (this.dimension != yBoard.dimension) return false;
    	for (int i = 0; i < this.dimension; i++) {
			if (!Arrays.equals(yBoard.board[i], this.board[i])) return false;
		}
    	return true;
    }
    
    private int[][] copyBoard(int[][] board){
    	int[][] copy = new int[board.length][];
    	for (int i = 0; i < board.length; i++) {
			copy[i] = board[i].clone();
		}
    	return copy;
    }
    
    // all neighboring boards
    public Iterable<Board> neighbors(){
    	ArrayList<Board> neighbors = new ArrayList<Board>();
    	if (zeroX - 1 >= 0) {
    		Board neighbor = new Board(this.copyBoard(this.board));
    		neighbor.exch(zeroX, zeroY, zeroX - 1, zeroY);
    		neighbor = new Board(neighbor.board);// use the copy of thisBoard's board array to initialize the neighbor board
    		neighbors.add(neighbor);
    	}
    	if (zeroX + 1 < this.dimension) {
    		Board neighbor = new Board(this.copyBoard(this.board));
    		neighbor.exch(zeroX, zeroY, zeroX + 1, zeroY);
    		neighbor = new Board(neighbor.board);// use the copy of thisBoard's board array to initialize the neighbor board
    		neighbors.add(neighbor);
    	}
    	if (zeroY - 1 >= 0) {
    		Board neighbor = new Board(this.copyBoard(this.board));
    		neighbor.exch(zeroX, zeroY, zeroX, zeroY - 1);
    		neighbor = new Board(neighbor.board);// use the copy of thisBoard's board array to initialize the neighbor board
    		neighbors.add(neighbor);
    	}
    	if (zeroY + 1 < this.dimension) {
    		Board neighbor = new Board(this.copyBoard(this.board));
    		neighbor.exch(zeroX, zeroY, zeroX, zeroY + 1);
    		neighbor = new Board(neighbor.board);// use the copy of thisBoard's board array to initialize the neighbor board
    		neighbors.add(neighbor);
    	}
    	return neighbors;
    }
    
    private void exch(int xa, int ya, int xb, int yb) {
    	int mid = board[xa][ya];
    	board[xa][ya] = board[xb][yb];
    	board[xb][yb] = mid;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
    	return this;
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
        System.out.println(initial.dimension);
        System.out.println(initial.toString());
//        System.out.println(initial.hamming());
        System.out.println(initial.manhattan());
        Iterable<Board> xArrayList = initial.neighbors();
        Board copyBoard = new Board(initial.copyBoard(initial.board));
        int[][] anotherboard = {{1,2,0},{3,8,6},{7,5,4}};
        Board anotherBoard = new Board(anotherboard);
        System.out.println(anotherBoard.toString());
        System.out.println(initial.equals(anotherBoard));
        System.out.println(copyBoard.toString());
        System.out.println(initial.equals(copyBoard));
        System.out.println("------------------");
        for(Board b : xArrayList) {
        	System.out.println(b.toString());
        }
        System.out.println(anotherBoard.isGoal());
        int[][] goal = {{1,2},{3,0}};
        Board goalBoard = new Board(goal);
        System.out.println(goalBoard.isGoal());
    }

}