package homework;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Perc {
	
	// -1 - is closed
	// 0 - is open
	// 1 - full
	public int [][] grid;
	// number of open sites
	private int numOfOpenSites = 0;
	
	public Perc(int n) {
		if (n < 0) {
            throw new IllegalArgumentException("n: "+ n + " is less than 0 ");  
        }
		grid = new int[n][n];
		for (int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				grid[i][j] = -1;
			}
		}
	}
	
	public void open(int row, int col) {
		if (row < 0 || row >= grid.length) {
            throw new IllegalArgumentException(" row: " + row + " is less than 0 ");  
        }
		if (col < 0 || col >= grid[row].length) {
            throw new IllegalArgumentException("col: " + col + " is less than 0 ");  
        }
		grid[row][col] = 0;
		numOfOpenSites++;
	}
	public boolean isOpen(int row, int col) {
		if (row < 0 || row >= grid.length) {
            throw new IllegalArgumentException(" row: " + row + " is less than 0 ");  
        }
		if (col < 0 || col >= grid[row].length) {
            throw new IllegalArgumentException("col: " + col + " is less than 0 ");  
        }
		if(grid[row][col] == 0) {
			return true;
		}
		return false;
	}
	public boolean isFull(int row, int col) {
		if (row < 0 || row >= grid.length) {
            throw new IllegalArgumentException(" row: " + row + " is less than 0 ");  
        }
		if (col < 0 || col >= grid[row].length) {
            throw new IllegalArgumentException("col: " + col + " is less than 0 ");  
        }
		if(grid[row][col] == 1) {
			return true;
		}
		return false;
	}
	public int numberOfOpenSites() {
		return numOfOpenSites;
	}
	public boolean percolates() {
		return false;
	}

	public static void main(String [] args) throws IOException {
        int n = StdIn.readInt();
        Perc perc = new Perc(n);
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n*n+2);
        while (!StdIn.isEmpty()) {
            int i = StdIn.readInt();
            i--;
            if(i > n) {
            	break;
            }
            int j = StdIn.readInt();
            j--;
            
            perc.open(i, j);
            
			// top node
			if(i == 0) {
				if (!uf.connected(i, j+1))
					uf.union(i, j+1);
			}
			
			// look through neighbors
			if(i > 0) {
				// count number of node in array
				int num = i*n + j + 1;
				
				int upNeighbor = num - n;
				int leftNeighbor = num - 1;
				int rightNeighbor = num + 1;
				int bottomNeighbor = num + n;
				
				if(upNeighbor >= 0 && upNeighbor <= n*n+1 &&
						i - 1 >= 0 && perc.isOpen(i-1,j)) {
					if (!uf.connected(num, upNeighbor))
						uf.union(num, upNeighbor);
				}
				if(leftNeighbor >= 0 && leftNeighbor <= n*n+1 &&
						j - 1 >= 0 && perc.isOpen(i,j-1)) {
					if (!uf.connected(num, leftNeighbor))
						uf.union(num, leftNeighbor);
				}
				if(rightNeighbor >= 0 && rightNeighbor <= n*n+1 &&
						j + 1 < n && perc.isOpen(i,j+1)) {
					if (!uf.connected(num, rightNeighbor))
						uf.union(num, rightNeighbor);
				}
				if(bottomNeighbor >= 0 && bottomNeighbor <= n*n+1 &&
						i + 1 < n && perc.isOpen(i+1,j)) {
					if (!uf.connected(num, bottomNeighbor))
						uf.union(num, bottomNeighbor);
				}
			}
			
			// bottom node
			if(i == n-1) {
				int num = i*n + j + 1;
				if (!uf.connected(num, n*n+1))
					uf.union(num, n*n+1);
			}
            
        }
    
        for (int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				StdOut.print(perc.grid[i][j] + " ");
			}
			StdOut.println(" ");
        }
        
        
//        for (int i = 0; i < n; i++) {
//			for(int j = 0; j < n; j++) {
//				if(perc.isOpen(i,j)) {
//					
//					// top node
//					if(i == 0) {
//						if (!uf.connected(i, j+1))
//							uf.union(i, j+1);
//					}
//					
//					// look through neighbors
//					if(i > 0) {
//						// count number of node in array
//						int num = i*n + j + 1;
//						
//						int upNeighbor = num - n;
//						int leftNeighbor = num - 1;
//						int rightNeighbor = num + 1;
//						int bottomNeighbor = num + n;
//						
//						if(upNeighbor >= 0 && upNeighbor <= n*n+1 &&
//								i - 1 >= 0 && perc.isOpen(i-1,j)) {
//							if (!uf.connected(num, upNeighbor))
//								uf.union(num, upNeighbor);
//						}
//						if(leftNeighbor >= 0 && leftNeighbor <= n*n+1 &&
//								j - 1 >= 0 && perc.isOpen(i,j-1)) {
//							if (!uf.connected(num, leftNeighbor))
//								uf.union(num, leftNeighbor);
//						}
//						if(rightNeighbor >= 0 && rightNeighbor <= n*n+1 &&
//								j + 1 < n && perc.isOpen(i,j+1)) {
//							if (!uf.connected(num, rightNeighbor))
//								uf.union(num, rightNeighbor);
//						}
//						if(bottomNeighbor >= 0 && bottomNeighbor <= n*n+1 &&
//								i + 1 < n && perc.isOpen(i+1,j)) {
//							if (!uf.connected(num, bottomNeighbor))
//								uf.union(num, bottomNeighbor);
//						}
//					}
//					
//					// bottom node
//					if(i == n-1) {
//						int num = i*n + j + 1;
//						if (!uf.connected(num, n*n+1))
//							uf.union(num, n*n+1);
//					}
//				}
//			}
//        }
        
        if(uf.connected(0, n*n+1)) {
        	StdOut.println(" percolates ");
        }
        else {
        	StdOut.println(" not percolates ");
        }
        
        StdOut.println(uf.count() + " components");
	}

}
