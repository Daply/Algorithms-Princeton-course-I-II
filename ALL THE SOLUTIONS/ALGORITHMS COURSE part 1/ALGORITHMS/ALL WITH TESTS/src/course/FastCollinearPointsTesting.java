package course;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * 
 * @author Pleshchankova Daria
 *
 */
public class FastCollinearPointsTesting {
    
    public static Point[] readFile(String filename) {
        Scanner scanner = null;
        Point[] points = null;
        try {
            scanner = new Scanner(new File(filename));
            int n = scanner.nextInt();
            points = new Point[n];
            int i = 0;
            for (int index = 0; index < n; index++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                points[i] = new Point(x, y);
                i++;
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return points;
    }
    
    public static String segmentsResult(String file) {
        Point[] points = readFile(file);
        
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.clear();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
           p.draw();
        }
        StdDraw.show();
        
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        StringBuilder sb = new StringBuilder();
        
        // print and draw the line segments
        for (LineSegment segment : collinear.segments()) {
            if (segment != null) {
                StdOut.println(segment);
                sb.append(segment + " -> ");
                segment.draw();
            }
        }
        StdDraw.show();
        
        if (sb.lastIndexOf("->") > 0)
            sb.delete(sb.lastIndexOf("->"), sb.length());
        return sb.toString().trim();
    }
    
    public static void main(String[] args) {
        
        LineSegment line = new LineSegment(new Point(10000, 0), new Point(0, 10000));
        LineSegment line1 = new LineSegment(new Point(10000, 0), new Point(0, 10000));
        System.out.println(line.equals(line1));
        
        System.out.println("Test 1");
        segmentsResult("src//fastcollinear_test//input8.txt");
        System.out.println("Test 1.1");
        segmentsResult("src//fastcollinear_test//input9.txt");
        
//      - student segment 0: (2000, 22000) -> (4000, 30000) 
//      - reference segment 1: (1000, 18000) -> (2000, 22000) -> (3000, 26000) -> (3500, 28000) -> (4000, 30000) 
        System.out.println("Test 2");
        segmentsResult("src//fastcollinear_test//input10.txt");
      
//      - student segment 0: (4096, 20992) -> (7168, 20992) 
//      - reference segment 0: (4096, 20992) -> (5120, 20992) -> (6144, 20992) -> (7168, 20992) -> (8128, 20992) 
        System.out.println("Test 3");
        segmentsResult("src//fastcollinear_test//input20.txt");
      
//      - student segment 1: (18000, 23000) -> (18000, 30000) 
//      - reference segment 6: (18000, 13000) -> (18000, 23000) -> (18000, 26000) -> (18000, 27000) -> (18000, 30000) 
        System.out.println("Test 4");
        segmentsResult("src//fastcollinear_test//input50.txt");
      
      
//      - student segment 2: (16000, 6000) -> (1000, 21000) 
//      - reference segment 2: (17000, 5000) -> (16000, 6000) -> (12000, 10000) -> (9000, 13000) -> (1000, 21000) 
        System.out.println("Test 5");
        segmentsResult("src//fastcollinear_test//input80.txt");

      // error
        System.out.println("Test 6");
        segmentsResult("src//fastcollinear_test//inarow.txt");       
        
        System.out.println("Test 7");
        segmentsResult("src//fastcollinear_test//horizontal5.txt"); 

    }
    
}
