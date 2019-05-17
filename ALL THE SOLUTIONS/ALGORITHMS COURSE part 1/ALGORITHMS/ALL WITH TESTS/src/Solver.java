import java.util.Stack;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    
    private int moves = -1;
    private boolean isSolvable = true;
    private Stack<Board> solution = new Stack<Board> ();
    
    public Solver(Board initial)  {
        // find a solution to the initial board (using the A* algorithm)
        
        if (initial == null) {
            throw new IllegalArgumentException("argument is null");
        }
        
        checkSolvability(initial);
        
        if (this.isSolvable) { 
            MinPQ<SearchNode> minQ = new MinPQ<SearchNode> ();
            SearchNode initNode = new SearchNode(initial, this.moves);
            minQ.insert(initNode);
            SearchNode minNode = initNode;
            Stack<Board> saveSolution = new Stack<Board> ();
            int maximumSteps = (int) (Math.pow(Math.pow(initial.dimension(), initial.dimension()), initial.dimension()));
            while (!minNode.currentBoard.isGoal()) {
                minNode = minQ.delMin();
                for (Board neighbor: minNode.currentBoard.neighbors()) {
                    if (minNode.previousNode == null || 
                            !minNode.previousNode.currentBoard.equals(neighbor)) {
                        SearchNode neighborNode = new SearchNode(neighbor, this.moves);
                        neighborNode.previousNode = minNode;
                        minQ.insert(neighborNode);
                    }
                }
                this.moves++;
            }
            
            if (this.isSolvable) {
                saveSolution.push(minNode.currentBoard);
                while (minNode.previousNode != null) {
                    if (minNode.currentBoard.equals(initial)) {
                        break;
                    }
                    saveSolution.push(minNode.previousNode.currentBoard);
                    minNode = minNode.previousNode;
                }
                
                while (!saveSolution.empty())
                    this.solution.push(saveSolution.pop());
                
                if (this.solution != null) {
                    this.moves = this.solution.size()-1;
                }   
            }
        }
    }
    public boolean isSolvable() {
        // is the initial board solvable?
        return this.isSolvable;
    }
    
    private boolean checkSolvability(Board board) {
        // is the initial board solvable?
        int[] inversBlocks = new int [board.dimension()*board.dimension()];
        int ind = 0;
        int emptyBI = 0;
        String boardAsString = board.toString();
        
        if (!boardAsString.equals("")) {
        
            String[] lines = boardAsString.split("\\n");
            for (int i = 1; i < lines.length; i++) {
                String[] str = lines[i].split("\\s");
                for (int j = 0; j < str.length; j++) {
                    if (!str[j].equals("") && str[j].trim().matches("[0-9]+")) {
                        int num = Integer.parseInt(str[j].trim());
                        inversBlocks[ind] = num;
                        ind++;
                        if (num == 0) {
                            emptyBI = i;
                        }
                    }
                }
            }
            
            int countInversions = 0;
            for (int i = 0; i < board.dimension()*board.dimension(); i++) {
                for (int j = i+1; j < board.dimension()*board.dimension(); j++) {
                    if (inversBlocks[i] > inversBlocks[j] &&
                            inversBlocks[i] != 0 && inversBlocks[j] != 0) {
                        countInversions++;
                    }
                }
            }
            
            if ((board.dimension() % 2 != 0 && countInversions % 2 == 0) ||
                    (board.dimension() % 2 == 0 && countInversions % 2 != 0 &&
                    (board.dimension() - emptyBI + 1) % 2 == 0) ||
                    (board.dimension() % 2 == 0 && countInversions % 2 == 0 &&
                    (board.dimension() - emptyBI + 1) % 2 != 0)) {
                this.isSolvable = true;
            }
            else {
                this.isSolvable = false;
            }
            
        }
        
        return this.isSolvable;
    }
    
    public int moves() {
        // min number of moves to solve initial board; -1 if unsolvable
        return this.moves;
    }
    public Iterable<Board> solution() {
        // sequence of boards in a shortest solution; null if unsolvable
        return this.solution;
    }
    
    private static class SearchNode implements Comparable<SearchNode> {

        private Board currentBoard = null;
        private SearchNode previousNode = null;
        private int searchMoves = 0;
        
        public SearchNode(Board board, int moves) {
            this.currentBoard = board;
            this.searchMoves = moves;
        }
        
        @Override
        public int compareTo(SearchNode node) {
            // TODO Auto-generated method stub
            if (this.currentBoard.manhattan()+this.searchMoves > 
                    node.currentBoard.manhattan()+node.searchMoves) {
                return 1;
            }
            if (this.currentBoard.manhattan()+this.searchMoves < 
                    node.currentBoard.manhattan()+node.searchMoves) {
                return -1;
            }
            return 0;
        }
        
    }
    
    public static void main(String[] args) {
        // solve a slider puzzle (given below)
//         int[][] blocks = new int [][] { {5, 1, 8}, {2, 7, 3}, {4, 0, 6} } ;
        // unsolvable
//         int[][] blocks = new int [][] { {1, 2, 3}, {4, 6, 5}, {7, 8, 0} } ;
//         int[][] blocks = new int [][] { {4, 3, 1}, {0, 2, 6}, {7, 8, 5} } ;
         int[][] blocks = new int [][] { {1, 2, 3}, {5, 8, 0}, {4, 6, 7} } ;
//         int[][] blocks = new int [][] { {2, 3}, {1, 0}} ;
//         int[][] blocks = new int [][] { {1, 2}, {0, 3}} ;
        // create initial board from file
//        In in = new In(args[0]);
//        int n = in.readInt();
//        int[][] blocks = new int[n][n];
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++)
//                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
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
