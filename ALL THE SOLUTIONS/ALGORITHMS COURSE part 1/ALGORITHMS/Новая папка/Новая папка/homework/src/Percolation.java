
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	// -1 - is closed
	// 0 - is open
	// 1 - full
	private boolean [][] grid;
	// number of open sites
	private int numOfOpenSites = 0;
	// flag if system percolates
	private boolean isPercolate = false;
	// union-find
	private WeightedQuickUnionUF uf = null;
	
	public Percolation(int n) {
		if (n <= 0) {
            throw new IllegalArgumentException("n: "+ n + " is less or equal than 0 ");  
        }
		grid = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = false;
			}
		}
		uf = new WeightedQuickUnionUF(n*n+2);
	}
	private void init(int i, int j) {
        int n = grid.length;
		int num = 0;
		int upNeighbor = 0;
		int leftNeighbor = 0;
		int rightNeighbor = 0;
		int bottomNeighbor = 0;
            
        // top node
		if (i == 0) {
			if (!uf.connected(i, j+1))
				uf.union(i, j+1);
		}
			
		// look through neighbors
		// count number of node in array
		num = i*n + j + 1;
				
		upNeighbor = num - n;
		leftNeighbor = num - 1;
		rightNeighbor = num + 1;
		bottomNeighbor = num + n;
				
		if (upNeighbor >= 0 && upNeighbor <= n*n+1 &&
				i - 1 >= 0 && grid[i-1][j]) {
			if (!uf.connected(num, upNeighbor))
				uf.union(num, upNeighbor);
		}
		if (leftNeighbor >= 0 && leftNeighbor <= n*n+1 &&
				j - 1 >= 0 && grid[i][j-1]) {
			if (!uf.connected(num, leftNeighbor))
				uf.union(num, leftNeighbor);
		}
		if (rightNeighbor >= 0 && rightNeighbor <= n*n+1 &&
				j + 1 < n && grid[i][j+1]) {
			if (!uf.connected(num, rightNeighbor))
				uf.union(num, rightNeighbor);
		}
		if (bottomNeighbor >= 0 && bottomNeighbor <= n*n+1 &&
				i + 1 < n && grid[i+1][j]) {
			if (!uf.connected(num, bottomNeighbor))
				uf.union(num, bottomNeighbor);
		}			

		// bottom node
		if (i == n-1) {
			num = i*n + j + 1;
			if (!uf.connected(num, n*n+1))
				uf.union(num, n*n+1);
		}
		
        if (uf.connected(0, n*n+1)) {
        	isPercolate = true;
        }
        else {
        	isPercolate = false;
        }
	
	}
	public void open(int row, int col) {
	    row--;
	    col--;
	    validation(row, col);
		if (!grid[row][col]) {
			grid[row][col] = true;
			numOfOpenSites++;
			init(row, col);
		}
	}
	public boolean isOpen(int row, int col) {
	    row--;
        col--;
        validation(row, col);
		if (grid[row][col]) {
			return true;
		}
		return false;
	}
	public boolean isFull(int row, int col) {
	    row--;
        col--;
        validation(row, col);
		int num = row*grid.length + col + 1;
		if (uf.connected(0, num)) {
			return true;
        }
		return false;
	}
	private void validation(int row, int col) {
	    if (row < 0 || row >= grid.length) {
            throw new IllegalArgumentException(" row: " + row + " is not in interval [0, " +
                                               grid.length + "]");  
        }
        if (col < 0 || col >= grid[row].length) {
            throw new IllegalArgumentException("col: " + col + " is not in interval [0, " +
                                                grid[row].length + "]");  
        }
	}
	public int numberOfOpenSites() {
		return numOfOpenSites;
	}
	public boolean percolates() {
		return isPercolate;
	}
}
