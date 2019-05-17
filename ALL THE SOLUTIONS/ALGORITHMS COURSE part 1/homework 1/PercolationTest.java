import static org.junit.Assert.*;

import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationTest {
    
    // delay in milliseconds (controls animation speed)
    private static final int DELAY = 50;
    public static boolean draw = false;
    
    public int[][] readFile(String filename) {
        Scanner scanner = null;
        int [][] grid = null;
        try {
            scanner = new Scanner(new File(filename));
            int n = scanner.nextInt();
            grid = new int [n][n];
            for (int index = 0; index < n; index++) {
                int i = scanner.nextInt();
                int j = scanner.nextInt();
                grid[i-1][j-1] = 1; 
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return grid;
    }
    
    public boolean isOpenResult(String file, int indI, int indJ) {
        int [][] grid = readFile(file);
        int n = grid.length;
        Percolation perc = new Percolation(n);
        if (draw) {
            draw(perc, n);
            StdDraw.show();
            StdDraw.pause(DELAY);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (!perc.isOpen(i+1, j+1))
                        perc.open(i+1, j+1);
                    if (draw) {
                        draw(perc, n);
                        StdDraw.show();
                        StdDraw.pause(DELAY);
                    }
                }
            }
        }
        return perc.isOpen(indI, indJ);
    }
    
    public boolean isFullResult(String file, int numberOfOpenSites, int indI, int indJ) {
        int [][] grid = readFile(file);
        int n = grid.length;
        Percolation perc = new Percolation(n);
        if (draw) {
            draw(perc, n);
            StdDraw.show();
            StdDraw.pause(DELAY);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (!perc.isOpen(i+1, j+1))
                        perc.open(i+1, j+1);
                    if (draw) {
                        draw(perc, n);
                        StdDraw.show();
                        StdDraw.pause(DELAY);
                    }
                }
            }
        }
        return perc.isFull(indI, indJ);
    }

    public static boolean isFullRandom (int n, int numberOfOpenSites, int indI, int indJ) {  
        Percolation perc = new Percolation(n);
        int i = 0;
        int j = 0;
        while (perc.numberOfOpenSites() != numberOfOpenSites) {
            if (draw) {
                draw(perc, n);
                StdDraw.show();
                StdDraw.pause(DELAY);
            }
            i = StdRandom.uniform(1, n+1);
            j = StdRandom.uniform(1, n+1);
            if (!perc.isOpen(i, j))
                perc.open(i, j);
            if (draw) {
                draw(perc, n);
                StdDraw.show();
                StdDraw.pause(DELAY);
            }
        }
        return perc.isFull(indI, indJ);
    }
    
    
    public boolean percolates(String file) {
        int [][] grid = readFile(file);
        int n = grid.length;
        Percolation perc = new Percolation(n);
        if (draw) {
            draw(perc, n);
            StdDraw.show();
            StdDraw.pause(DELAY);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (!perc.isOpen(i+1, j+1))
                        perc.open(i+1, j+1);
                    if (draw) {
                        draw(perc, n);
                        StdDraw.show();
                        StdDraw.pause(DELAY);
                    }
                }
            }
        }
        return perc.percolates();
    }
    
    public static boolean percolatesRandom (int n, int numberOfOpenSites) {  
        Percolation perc = new Percolation(n);
        int i = 0;
        int j = 0;
        while (perc.numberOfOpenSites() != numberOfOpenSites) {
            if (draw) {
                draw(perc, n);
                StdDraw.show();
                StdDraw.pause(DELAY);
            }
            i = StdRandom.uniform(1, n+1);
            j = StdRandom.uniform(1, n+1);
            if (!perc.isOpen(i, j))
                perc.open(i, j);
            if (draw) {
                draw(perc, n);
                StdDraw.show();
                StdDraw.pause(DELAY);
            }
        }
        return perc.percolates();
    }
    
    @Test
    public void testIsOpen() {
        assertTrue(isOpenResult("src//percolation_test//input2.txt", 1, 2));
        assertTrue(isOpenResult("src//percolation_test//input20.txt", 14, 20));
        assertTrue(isOpenResult("src//percolation_test//sedgewick60.txt", 58, 33));
        
        assertFalse(isOpenResult("src//percolation_test//input50.txt", 50, 7));
        assertFalse(isOpenResult("src//percolation_test//jerry47.txt", 46, 5));
        assertFalse(isOpenResult("src//percolation_test//wayne98.txt", 1, 13));
    }

    @Test
    public void testIsFull() {
        assertFalse(isFullResult("src//percolation_test//input20.txt", 231, 18, 1));
        assertFalse(isFullResult("src//percolation_test//input20.txt", 231, 18, 1));
        assertFalse(isFullResult("src//percolation_test//input10.txt", 56, 9, 1));
        assertFalse(isFullResult("src//percolation_test//input50.txt", 1412, 22, 28));
        assertFalse(isFullResult("src//percolation_test//jerry47.txt", 1076, 11, 47));
        assertFalse(isFullResult("src//percolation_test//sedgewick60.txt", 1577, 21, 59));
        assertFalse(isFullResult("src//percolation_test//wayne98.txt", 3851, 69, 9));
      
        assertFalse(isFullResult("src//percolation_test//input3.txt", 4, 3, 1));
        assertFalse(isFullResult("src//percolation_test//input4.txt", 7, 4, 4));
        assertFalse(isFullResult("src//percolation_test//input7.txt", 12, 6, 1));
        
        assertFalse(isFullRandom(3, 6, 3, 3));
    }

    @Test
    public void testPercolates() {
        // true
        assertTrue(percolates("src//percolation_test//input1.txt"));
        assertTrue(percolates("src//percolation_test//input2.txt"));
        assertTrue(percolates("src//percolation_test//input3.txt"));
        assertTrue(percolates("src//percolation_test//input4.txt"));
        assertTrue(percolates("src//percolation_test//input5.txt"));
        assertTrue(percolates("src//percolation_test//input6.txt"));
        assertTrue(percolates("src//percolation_test//input7.txt"));
        assertTrue(percolates("src//percolation_test//input8.txt"));
        assertTrue(percolates("src//percolation_test//input8-dups.txt"));
        assertTrue(percolates("src//percolation_test//input10.txt"));
        assertTrue(percolates("src//percolation_test//input20.txt"));
        assertTrue(percolates("src//percolation_test//input50.txt"));
        assertTrue(percolates("src//percolation_test//java60.txt"));
        assertTrue(percolates("src//percolation_test//jerry47.txt"));   
        assertTrue(percolates("src//percolation_test//snake13.txt"));
        assertTrue(percolates("src//percolation_test//snake101.txt"));
        assertTrue(percolates("src//percolation_test//wayne98.txt"));
        assertTrue(percolates("src//percolation_test//sedgewick60.txt"));
        // false
        assertFalse(percolates("src//percolation_test//greeting57.txt"));
        assertFalse(percolates("src//percolation_test//input1-no.txt"));
        assertFalse(percolates("src//percolation_test//input2-no.txt"));
        assertFalse(percolates("src//percolation_test//input8-no.txt"));
        assertFalse(percolates("src//percolation_test//input10-no.txt"));

    }
    
    // draw n-by-n percolation system
    public static void draw(Percolation perc, int n) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(-0.05 * n, 1.05 * n);
        StdDraw.setYscale(-0.05 * n, 1.05 * n);   // leave a border to write text
        StdDraw.filledSquare(n / 2.0, n / 2.0, n / 2.0);

        // draw n-by-n grid
        int opened = 0;
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (perc.isFull(row, col)) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    opened++;
                }
                else if (perc.isOpen(row, col)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    opened++;
                }
                else
                    StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledSquare(col - 0.5, n - row + 0.5, 0.45);
            }
        }

        // write status text
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.25 * n, -0.025 * n, opened + " open sites");
        if (perc.percolates()) StdDraw.text(0.75 * n, -0.025 * n, "percolates");
        else StdDraw.text(0.75 * n, -0.025 * n, "does not percolate");
    }
}
