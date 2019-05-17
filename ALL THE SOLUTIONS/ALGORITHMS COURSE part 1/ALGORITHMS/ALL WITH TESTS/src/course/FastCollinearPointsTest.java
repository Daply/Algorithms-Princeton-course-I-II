package course;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

/**
 * 
 * @author Pleshchankova Daria
 *
 */
public class FastCollinearPointsTest {
    
    public Point[] readFile(String filename) {
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
    
    public boolean segmentsCompare(String file) {
        Point[] points = readFile(file);
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        // passed all tests in grader but not timing
        FastCollinearPoints2 collinear1 = new FastCollinearPoints2(points);
        // print and draw the line segments
        int countTheSame = 0;
        for (LineSegment segment : collinear.segments()) {
            if (segment != null) {
                for (LineSegment segment1 : collinear1.segments()) {
                    if (segment1 != null) {
                        if (segment.equals(segment1)) {
                            countTheSame++;
                        }
                    }
                }
            }
        }
        if (countTheSame == collinear1.segments().length)
            return true;
        else
            return false;
    }
    
    public String segmentsResult(String file) {
        Point[] points = readFile(file);
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        StringBuilder sb = new StringBuilder();
        
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
           p.draw();
        }
        StdDraw.show();
        
        // print and draw the line segments
        for (LineSegment segment : collinear.segments()) {
            if (segment != null) {
                StdOut.println(segment);
                sb.append(segment + " -> ");
                segment.draw();
            }
        }
        StdDraw.show();
        
        sb.delete(sb.lastIndexOf("->"), sb.length());
        return sb.toString().trim();
    }
    
    @Test
    public void testSegments() {
//        - student segment 0: (6000, 6000) -> (9000, 9000) 
//        - reference segment 0: (1000, 1000) -> (2000, 2000) -> (3000, 3000) -> (4000, 4000) -> (5000, 5000) -> (6000, 6000) -> (7000, 7000) -> (8000, 8000) -> (9000, 9000) 
        assertTrue(segmentsCompare("src//fastcollinear_test//input9.txt"));
        
//        - student segment 0: (2000, 22000) -> (4000, 30000) 
//        - reference segment 1: (1000, 18000) -> (2000, 22000) -> (3000, 26000) -> (3500, 28000) -> (4000, 30000) 
        assertTrue(segmentsCompare("src//fastcollinear_test//input10.txt"));
        
//        - student segment 0: (4096, 20992) -> (7168, 20992) 
//        - reference segment 0: (4096, 20992) -> (5120, 20992) -> (6144, 20992) -> (7168, 20992) -> (8128, 20992) 
        assertTrue(segmentsCompare("src//fastcollinear_test//input20.txt"));
        
//        - student segment 1: (18000, 23000) -> (18000, 30000) 
//        - reference segment 6: (18000, 13000) -> (18000, 23000) -> (18000, 26000) -> (18000, 27000) -> (18000, 30000) 
        assertTrue(segmentsCompare("src//fastcollinear_test//input50.txt"));
        
        
//        - student segment 2: (16000, 6000) -> (1000, 21000) 
//        - reference segment 2: (17000, 5000) -> (16000, 6000) -> (12000, 10000) -> (9000, 13000) -> (1000, 21000) 
        assertTrue(segmentsCompare("src//fastcollinear_test//input80.txt"));

        // error
        assertTrue(segmentsCompare("src//fastcollinear_test//inarow.txt"));
    }

}
