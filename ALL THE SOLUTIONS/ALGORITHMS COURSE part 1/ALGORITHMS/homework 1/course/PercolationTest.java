package course;

import java.awt.Font;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationTest {
    
    // delay in miliseconds (controls animation speed)
    private static final int DELAY = 50;
    public static boolean draw = true;
    
    public static void testIsFull (String file, int numberOfOpenSites, int indI, int indJ) {
        
        // turn on animation mode
        if (draw)
            StdDraw.enableDoubleBuffering();
        
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        try(FileInputStream fis=new FileInputStream(file))
        {
            // System.out.printf("File size: %d bytes \n", fis.available());
              
            int i=-1;
            String num = "";
            while((i=fis.read())!=-1){
                if (((char)i) == '\n' || ((char)i) == ' ') {
                    if (num.length() > 0)
                        numbers.add(Integer.parseInt(num));
                    num = "";
                }
                else
                    num += ((char)i);
            }   
        }
        catch(IOException ex){        
            System.out.println(ex.getMessage());
        } 
        
        int n = numbers.get(0);
        Percolation perc = new Percolation(n);
        if (draw) {
            draw(perc, n);
            StdDraw.show();
            StdDraw.pause(DELAY);
        }
        int i = 0;
        int j = 0;
        ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
        for (int ind = 1; ind < n - 1; ind++) {
            i = numbers.get(ind);
            j = numbers.get(ind + 1);
            listOfNumbers.add(i);
            listOfNumbers.add(j);
            if (!perc.isOpen(i, j))
                perc.open(i, j);
            if (draw) {
                draw(perc, n);
                StdDraw.show();
                StdDraw.pause(DELAY);
            }
        }
        
        // debug here
        if (perc.isFull(indI, indJ)) {
            perc = new Percolation(n);
            for (int ind = 0; ind < n - 1; ind++) {
                i = listOfNumbers.get(ind);
                j = listOfNumbers.get(ind + 1);
                if (!perc.isOpen(i, j))
                    perc.open(i, j);
            }
        }
            
        
        System.out.println(perc.isFull(indI, indJ));
    }
    
    public static void testIsFullRandom (int n, int numberOfOpenSites, int indI, int indJ) {  
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
        System.out.println(perc.isFull(indI, indJ));
    }
    
    public static void testWhileNotPercolates (int n, int trials) {
        int i = 0;
        int j = 0;
        for (int ind = 0; ind < trials; ind++) {
            Percolation perc = new Percolation(n);
            if (draw) {
                draw(perc, n);
                StdDraw.show();
                StdDraw.pause(DELAY);
            }
            while (!perc.percolates()) {  
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
        }
    }
    
    public static void testIsPercolates (String file, int numberOfOpenSites) {
        // turn on animation mode
        if (draw)
            StdDraw.enableDoubleBuffering();
        
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        try(FileInputStream fis=new FileInputStream(file))
        {
            // System.out.printf("File size: %d bytes \n", fis.available());
              
            int i=-1;
            String num = "";
            while((i=fis.read())!=-1){
                if (((char)i) == '\n' || ((char)i) == ' ') {
                    if (num.length() > 0)
                        numbers.add(Integer.parseInt(num));
                    num = "";
                }
                else
                    num += ((char)i);
            }   
        }
        catch(IOException ex){        
            System.out.println(ex.getMessage());
        } 
        
        int n = numbers.get(0);
        Percolation perc = new Percolation(n);
        if (draw) {
            draw(perc, n);
            StdDraw.show();
            StdDraw.pause(DELAY);
        }
        int i = 0;
        int j = 0;
        ArrayList<Integer> listOfNumbers = new ArrayList<Integer>();
        for (int ind = 1; ind < n - 1; ind++) {
            i = numbers.get(ind);
            j = numbers.get(ind + 1);
            listOfNumbers.add(i);
            listOfNumbers.add(j);
            if (!perc.isOpen(i, j))
                perc.open(i, j);
            if (draw) {
                draw(perc, n);
                StdDraw.show();
                StdDraw.pause(DELAY);
            }
            if (perc.numberOfOpenSites() == numberOfOpenSites) {
                System.out.println(perc.percolates());
                break;
            }
        }
        
        
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
    
    public static void main(String args[] ) throws Exception {
//        testIsFull("src//percolation_test//input20.txt", 231, 18, 1);
//        testIsFull("src//percolation_test//input10.txt", 56, 9, 1);
//        testIsFull("src//percolation_test//input50.txt", 1412, 22, 28);
//        testIsFull("src//percolation_test//jerry47.txt", 1076, 11, 47);
//        testIsFull("src//percolation_test//sedgewick60.txt", 1577, 21, 59);
//        testIsFull("src//percolation_test//wayne98.txt", 3851, 69, 9);
//        
//        testIsFull("src//percolation_test//input3.txt", 4, 3, 1);
//        testIsFull("src//percolation_test//input4.txt", 7, 4, 4);
//        testIsFull("src//percolation_test//input7.txt", 12, 6, 1);
//        
//         testIsFullRandom(3, 6, 3, 3);
        
         testWhileNotPercolates(5, 1);
         
//         testIsPercolates("src//percolation_test//input6.txt", 5);
//         testIsPercolates("src//percolation_test//input10-no.txt", 55);
//         testIsPercolates("src//percolation_test//greeting57.txt", 2522);
//         testIsPercolates("src//percolation_test//input2.txt", 1);
//         testIsPercolates("src//percolation_test//input2-no.txt", 1);
    }
}
