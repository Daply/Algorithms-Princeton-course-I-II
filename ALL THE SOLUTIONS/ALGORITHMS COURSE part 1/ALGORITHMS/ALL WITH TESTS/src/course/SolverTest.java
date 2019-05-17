package course;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import edu.princeton.cs.algs4.StdOut;

public class SolverTest {
    
    public Board readFile(String filename) {
        Scanner scanner = null;
        int[][] blocks = new int [][] { {5, 1, 8}, {2, 7, 3}, {4, 0, 6} } ;
        try {
            scanner = new Scanner(new File(filename));
            int n = scanner.nextInt();
            blocks = new int[n][n];
            int num = 0; 
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    num = scanner.nextInt();
                    blocks[i][j] = num;
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return new Board(blocks);
    }
    
    public boolean compareSolutions(String filename) {
        Board init = readFile(filename);
        // solve the puzzle
        Solver solver = new Solver(init);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
        return true;
    }

    @Test
    public void testSolution() {
        assertFalse(compareSolutions("src//puzzle_test//puzzle4x4-unsolvable.txt"));
    }

}
