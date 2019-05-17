/******************************************************************************
 *  Compilation:  javac NearestNeighborVisualizer.java
 *  Execution:    java NearestNeighborVisualizer input.txt
 *  Dependencies: PointSET.java KdTree.java
 *
 *  Read points from a file (specified as a command-line argument) and
 *  draw to standard draw. Highlight the closest point to the mouse.
 *
 *  The nearest neighbor according to the brute-force algorithm is drawn
 *  in red; the nearest neighbor using the kd-tree algorithm is drawn in blue.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

public class NearestNeighborVisualizer {

    public static void main(String[] args) {

        // initialize the two data structures with point from file
//        String filename = args[0];
//        In in = new In(filename);
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
//        while (!in.isEmpty()) {
//            double x = in.readDouble();
//            double y = in.readDouble();
//            Point2D p = new Point2D(x, y);
//            kdtree.insert(p);
//            brute.insert(p);
//        }
        
//        kdtree.insert(new Point2D(0.372, 0.497));
//        kdtree.insert(new Point2D(0.564, 0.413));
//        kdtree.insert(new Point2D(0.226, 0.577));
//        kdtree.insert(new Point2D(0.144, 0.179));
//        kdtree.insert(new Point2D(0.083, 0.510));
//        kdtree.insert(new Point2D(0.320, 0.708));
//        kdtree.insert(new Point2D(0.417, 0.362));
//        kdtree.insert(new Point2D(0.862, 0.825));
//        kdtree.insert(new Point2D(0.785, 0.725));
//        kdtree.insert(new Point2D(0.499, 0.208));
//        
//        brute.insert(new Point2D(0.372, 0.497));
//        brute.insert(new Point2D(0.564, 0.413));
//        brute.insert(new Point2D(0.226, 0.577));
//        brute.insert(new Point2D(0.144, 0.179));
//        brute.insert(new Point2D(0.083, 0.510));
//        brute.insert(new Point2D(0.320, 0.708));
//        brute.insert(new Point2D(0.417, 0.362));
//        brute.insert(new Point2D(0.862, 0.825));
//        brute.insert(new Point2D(0.785, 0.725));
//        brute.insert(new Point2D(0.499, 0.208));

        // A 0.7 0.2 B 0.5 0.4 C 0.2 0.3 D 0.4 0.7 E 0.9 0.6
        // (0.67, 0.68)
        
        
      kdtree.insert(new Point2D(0.372, 0.497));
      kdtree.insert(new Point2D(0.564, 0.413));
      kdtree.insert(new Point2D(0.226, 0.577));
      kdtree.insert(new Point2D(0.144, 0.179));
      kdtree.insert(new Point2D(0.083, 0.51));
      kdtree.insert(new Point2D(0.32, 0.708));
      kdtree.insert(new Point2D(0.417, 0.362));
      kdtree.insert(new Point2D(0.862, 0.825));
      kdtree.insert(new Point2D(0.785, 0.725));
      kdtree.insert(new Point2D(0.499, 0.208));
        
      brute.insert(new Point2D(0.372, 0.497));
      brute.insert(new Point2D(0.564, 0.413));
      brute.insert(new Point2D(0.226, 0.577));
      brute.insert(new Point2D(0.144, 0.179));
      brute.insert(new Point2D(0.083, 0.51));
      brute.insert(new Point2D(0.32, 0.708));
      brute.insert(new Point2D(0.417, 0.362));
      brute.insert(new Point2D(0.862, 0.825));
      brute.insert(new Point2D(0.785, 0.725));
      brute.insert(new Point2D(0.499, 0.208));        
        
        // process nearest neighbor queries
        StdDraw.enableDoubleBuffering();
        while (true) {

            // the location (x, y) of the mouse
            double x = StdDraw.mouseX();
            double y = StdDraw.mouseY();
            Point2D query = new Point2D(x, y);

            // draw all of the points
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            brute.draw();

            // draw in red the nearest neighbor (using brute-force algorithm)
            StdDraw.setPenRadius(0.03);
            StdDraw.setPenColor(StdDraw.RED);
            brute.nearest(query).draw();
            StdDraw.setPenRadius(0.02);

            // draw in blue the nearest neighbor (using kd-tree algorithm)
            StdDraw.setPenColor(StdDraw.BLUE);
            kdtree.nearest(query).draw();
            StdDraw.show();
            StdDraw.pause(10);
        }
    }
}
