import java.util.ArrayList;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	
	private int numOfMoves;
	private boolean solvable;
	private SearchNode delNode;
	private SearchNode twinDelNode;
	private Stack<Board> solution;
	
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initialBoard) {
    	// corner case
    	if (initialBoard == null) throw new IllegalArgumentException("input cannot be null");
    	
    	// create the twinInitialBoard 
    	Board twinInitialBoard = initialBoard.twin();
    	solution = new Stack<Board>();
    	
    	// Initialize the original & twin
    	SearchNode initialNode = new SearchNode(initialBoard,0,null);
    	SearchNode twinInitialNode = new SearchNode(twinInitialBoard,0,null);
    	Iterable<SearchNode> neighborsNodes = new ArrayList<SearchNode>();
    	Iterable<SearchNode> twinNeighborsNodes = new ArrayList<SearchNode>();
    	delNode = initialNode;
    	twinDelNode = twinInitialNode;
    	MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();
		MinPQ<SearchNode> twinMinPQ = new MinPQ<SearchNode>();
    	
    	//solve
    	while (!delNode.board.isGoal() && !twinDelNode.board.isGoal()) {
			//this.numOfMoves ++; we cannot use ++ to count the moves because with A algorithm we may ÈÆÂ· because all the undequeued nodes are stored in the minPQ
			Iterable<Board> neighborsBoards = delNode.board.neighbors();
			Iterable<Board> twinNeighborsBoards = twinDelNode.board.neighbors();
			neighborsNodes = convertBoardsToNodes(neighborsBoards, delNode.moves, delNode);
			twinNeighborsNodes = convertBoardsToNodes(twinNeighborsBoards, twinDelNode.moves, twinDelNode);
			
			for (SearchNode node : neighborsNodes) {
				if (node.notRepeat()) minPQ.insert(node);
			}
			for (SearchNode node : twinNeighborsNodes) {
				if (node.twinNotRepeat()) twinMinPQ.insert(node);
			}
			// wipe the unused nodes for saving memory ( avoid lotering)
			delNode = minPQ.delMin();
			twinDelNode = twinMinPQ.delMin();
    	}
    	// add the final goal board to the Solver.solution
    	//this.numOfMoves ++;
    	if (delNode.board.isGoal()) this.solvable = true;
    	else this.solvable = false;
    	
    	// calculate numOfMoves
    	while (this.delNode != null) {
    		solution.push(delNode.board);
    		numOfMoves ++;
    		delNode = delNode.prevNode;
    	}
    }
    
    // convert the Board to SearchNode for processing convenience
    private Iterable<SearchNode> convertBoardsToNodes(Iterable<Board> Boards, int previousMoves, SearchNode preNode){
    	ArrayList<SearchNode> neighborsNodes = new ArrayList<SearchNode>();
    	previousMoves ++;
    	for (Board b : Boards) {
    		neighborsNodes.add(new SearchNode(b, previousMoves, preNode));
    	}
    	return neighborsNodes;
    }
    
    
    private class SearchNode implements Comparable<SearchNode>{
    	Board board;
    	int priority;
    	int moves;
    	SearchNode prevNode;
    	
    	public SearchNode(Board inputBoard, int previousMoves, SearchNode preNode) {
    		board = inputBoard;
    		prevNode = preNode;
    		moves = previousMoves ++;
    		priority = inputBoard.manhattan() + this.moves;
    	}
    	
    	// check if the Node is identical to the solutions
    	private boolean notRepeat() {
    		SearchNode stepNode = Solver.this.delNode;
    		while (stepNode != null) {
    			if (this.board.equals(stepNode.board)) return false;
    			stepNode = stepNode.prevNode;
    		}
    		return true;
        }
    	
    	private boolean twinNotRepeat() {
    		SearchNode stepNode = Solver.this.twinDelNode;
    		while (stepNode != null) {
    			if (this.board.equals(stepNode.board)) return false;
    			stepNode = stepNode.prevNode;
    		}
    		return true;
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
    	return this.solvable;
    }

    // min number of moves to solve initial board
    public int moves() {
    	return numOfMoves - 1;// do not include the initial board, so - 1
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
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
    	// print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}