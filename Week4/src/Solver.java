import java.util.ArrayList;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	
	private int numOfMoves;
	private SearchNode delNode;
	private ArrayList<SearchNode> solution;// the boards that have been delete
	private Iterable<SearchNode> neighborsNodes;
	private Iterable<Board> neighborsBoards;
	
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initialBoard) {
    	if (initialBoard == null) throw new IllegalArgumentException("input cannot be null");
    	solution = new ArrayList<SearchNode>();
    	neighborsNodes = new ArrayList<SearchNode>();
    	SearchNode initialNode = new SearchNode(initialBoard);
    	MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();
    	minPQ.insert(initialNode);
    	do {
			delNode = minPQ.delMin();
			delNode.addDeleteNodes();
			this.numOfMoves ++;
			neighborsBoards = delNode.board.neighbors();
			this.neighborsNodes = convertBoardsToNodes(neighborsBoards);
			for (SearchNode node : neighborsNodes) {
				if (node.notRepeat()) minPQ.insert(node);
			}
		} while (!delNode.board.isGoal());
    }
    
    private Iterable<SearchNode> convertBoardsToNodes(Iterable<Board> Boards){
    	ArrayList<SearchNode> neighborsNodes = new ArrayList<SearchNode>();
    	for (Board b : Boards) {
    		neighborsNodes.add(new SearchNode(b));
    	}
    	return neighborsNodes;
    }
    
    class SearchNode implements Comparable<SearchNode>{
    	Board board;
    	int priority;
    	int numOfMoves;
    	SearchNode prevNode;
    	
    	public SearchNode(Board inputBoard) {
    		this.board = inputBoard;
    		prevNode = Solver.this.delNode;
    		this.numOfMoves = Solver.this.numOfMoves;
    		priority = inputBoard.manhattan() + this.numOfMoves;
    	}
    	
    	// check if the Node has repeat ones in the ArrayList<SearchNode> solution
    	private boolean notRepeat() {
        	for (SearchNode nodeInSolution : Solver.this.solution) {
    			if (nodeInSolution.board.equals(this.board)) return false;
    		}	
        	return true;
        }
    	
    	// get the deleted boards
        private Iterable<SearchNode> addDeleteNodes(){
        	Solver.this.solution.add(this);
        	return Solver.this.solution;
        }
    	
    	public int compareTo(SearchNode x) {
			// since the smaller priority will be in the head of the MinPQ, so when...
    		// ...this. priority > x.priority( pri(k/2) > pri(k) ), we will return 1. ...
    		// ...then the swim() in MinPQ will exch(k/2,k) to let the smaller priority...
    		// ...node to go to the head.
    		return this.priority > x.priority? 1 : this.priority == x.priority? 0 : -1;
		}
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
    	return true;
    }

    // min number of moves to solve initial board
    public int moves() {
    	return this.numOfMoves - 1;// do not include the initial board, so - 1
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
    	ArrayList<Board> solution = new ArrayList<Board>();
    	for (SearchNode node : this.solution) {
    		solution.add(node.board);
    	}
    	return solution;
    }

    // test client (see below) 
    public static void main(String[] args) {
    	In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);
    	Solver solver = new Solver(initial);
    	StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }

}