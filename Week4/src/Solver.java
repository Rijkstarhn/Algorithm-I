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
	private SearchNode initialNode;
	private boolean solvable;
	private SearchNode twinDelNode;
	private ArrayList<SearchNode> twinSolution;
	private Iterable<SearchNode> twinNeighborsNodes;
	private Iterable<Board> twinNeighborsBoards;
	private SearchNode twinInitialNode;
	
	
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initialBoard) {
    	// corner case
    	if (initialBoard == null) throw new IllegalArgumentException("input cannot be null");
    	
    	// create the twinInitialBoard 
    	Board twinInitialBoard = initialBoard.twin();
    	solution = new ArrayList<SearchNode>();
    	neighborsNodes = new ArrayList<SearchNode>();
    	twinSolution = new ArrayList<SearchNode>();
    	twinNeighborsNodes = new ArrayList<SearchNode>();
    	
    	// Initialize the initialNode & twinInitialNode and their minPQ
    	initialNode = new SearchNode(initialBoard);
    	delNode = initialNode;
    	twinInitialNode = new SearchNode(twinInitialBoard);
    	twinDelNode = twinInitialNode;
    	
    	//solve
    	while (!delNode.board.isGoal() && !twinDelNode.board.isGoal()) {
			delNode.addDeleteNodes();// Add the delNode to the Solver.solution
			twinDelNode.addTwinDeleteNodes();
			this.numOfMoves ++;
			neighborsBoards = delNode.board.neighbors();
			twinNeighborsBoards = twinDelNode.board.neighbors();
			this.neighborsNodes = convertBoardsToNodes(neighborsBoards);
			this.twinNeighborsNodes = convertBoardsToNodes(twinNeighborsBoards);
			MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();
			MinPQ<SearchNode> twinMinPQ = new MinPQ<SearchNode>();
			for (SearchNode node : neighborsNodes) {
				if (this.numOfMoves < 2) minPQ.insert(node);
				else if (node.notRepeat()) minPQ.insert(node);
			}
			for (SearchNode node : twinNeighborsNodes) {
				if (this.numOfMoves < 2) twinMinPQ.insert(node);
				else if (node.twinNotRepeat()) twinMinPQ.insert(node);
			}
			delNode = minPQ.delMin();
			twinDelNode = twinMinPQ.delMin();
    	}
    	// add the final goal board to the Solver.solution
    	delNode.addDeleteNodes();
    	this.numOfMoves ++;
    	if (delNode.board.isGoal()) this.solvable = true;
    	else this.solvable = false;
    }
    
    // convert the Board to SearchNode for processing convenience
    private Iterable<SearchNode> convertBoardsToNodes(Iterable<Board> Boards){
    	ArrayList<SearchNode> neighborsNodes = new ArrayList<SearchNode>();
    	for (Board b : Boards) {
    		neighborsNodes.add(new SearchNode(b));
    	}
    	return neighborsNodes;
    }
    
    
    private class SearchNode implements Comparable<SearchNode>{
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
    		if (Solver.this.solution.get(Solver.this.solution.size()-1).board.equals(this.board)) return false;
    		if (Solver.this.solution.get(Solver.this.solution.size()-2).board.equals(this.board)) return false;
        	return true;
        }
    	
    	private boolean twinNotRepeat() {
    		if (Solver.this.twinSolution.get(Solver.this.twinSolution.size()-1).board.equals(this.board)) return false;
    		if (Solver.this.twinSolution.get(Solver.this.twinSolution.size()-2).board.equals(this.board)) return false;
        	return true;
        }
    	
    	// get the deleted boards
        private Iterable<SearchNode> addDeleteNodes(){
        	Solver.this.solution.add(this);
        	return Solver.this.solution;
        }
        
        private Iterable<SearchNode> addTwinDeleteNodes(){
        	Solver.this.twinSolution.add(this);
        	return Solver.this.twinSolution;
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