import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class TestPointSET {

    public static void testInsertSizeIsEmpty() {
        PointSET brute = new PointSET();
        StdOut.println(brute.isEmpty());
        StdOut.println(brute.size());
        brute.insert(new Point2D(0.372, 0.497));
        StdOut.println(brute.size());
        brute.insert(new Point2D(0.564, 0.413));
        brute.insert(new Point2D(0.226, 0.577));
        StdOut.println(brute.isEmpty());
        brute.insert(new Point2D(0.144, 0.179));
        brute.insert(new Point2D(0.083, 0.510));
        brute.insert(new Point2D(0.320, 0.708));
        brute.insert(new Point2D(0.417, 0.362));
        brute.insert(new Point2D(0.862, 0.825));
        brute.insert(new Point2D(0.785, 0.725));
        brute.insert(new Point2D(0.499, 0.208));
        StdOut.println(brute.isEmpty());
        StdOut.println(brute.size());
        brute = new PointSET();
        StdOut.println(brute.isEmpty());
        StdOut.println(brute.size());
        brute.insert(new Point2D(0.7, 0.2));
        brute.insert(new Point2D(0.5, 0.4));
        StdOut.println(brute.isEmpty());
        brute.insert(new Point2D(0.2, 0.3));
        StdOut.println(brute.size());
        brute.insert(new Point2D(0.4, 0.7));
        brute.insert(new Point2D(0.9, 0.6));
        brute.insert(new Point2D(0.4, 0.8));
        StdOut.println(brute.size());
    }
    
    public static void testRandomInsert(){
        PointSET brute = new PointSET();
          for (int i = 0; i < 10000; i++) {
              double x = StdRandom.uniform(0.0, 1.0);
              double y = StdRandom.uniform(0.0, 1.0);
              brute.insert(new Point2D(x, y));
          }
    }
    
    public static void testContains(){
        PointSET brute = new PointSET();
        brute.insert(new Point2D(1.0, 0.0));
        StdOut.println(brute.contains(new Point2D(1.0, 0.0)));
        StdOut.println(brute.contains(new Point2D(0.0, 1.0)));
        
        brute = new PointSET();
//      A 0.0 0.0 
//      B 1.0 0.25
//      C 0.75 1.0 
//      D 1.0 1.0 
//      E 0.75 0.75 
//      F 0.0 0.25 
//      G 0.0 1.0 
//      H 1.0 0.75 
//      I 0.5 1.0 
//      J 0.0 0.75
//      query point (0.75, 0.75)
        brute.insert(new Point2D(0.0, 0.0));
        brute.insert(new Point2D(1.0, 0.25));
        brute.insert(new Point2D(0.75, 1.0));
        brute.insert(new Point2D(1.0, 1.0));
        brute.insert(new Point2D(0.75, 0.75));
        brute.insert(new Point2D(0.0, 0.25));
        brute.insert(new Point2D(0.0, 1.0));
        brute.insert(new Point2D(1.0, 0.75));
        brute.insert(new Point2D(0.5, 1.0));
        brute.insert(new Point2D(0.0, 0.75));
        StdOut.println(brute.contains(new Point2D(0.75, 0.75)));
        
        brute = new PointSET();
//      A 0.25 0.875 
//      B 0.5 0.25 
//      C 0.375 0.5 
//      D 0.75 0.25 
//      E 0.0 0.5 
//      F 0.125 0.875 
//      G 0.75 0.75 
//      H 1.0 0.625 
//      I 0.625 0.375 
//      J 0.25 1.0 
//      K 0.5 0.125 
//      L 1.0 0.125 
//      M 0.0 1.0 
//      N 0.25 0.375 
//      O 1.0 0.0 
//      P 1.0 0.375 
//      Q 0.125 0.625 
//      R 0.75 0.625 
//      S 0.875 0.125 
//      T 0.0 0.25
//      query point (0.25, 0.375)
        brute.insert(new Point2D(0.25, 0.875));
        brute.insert(new Point2D(0.5, 0.25));
        brute.insert(new Point2D(0.375, 0.5));
        brute.insert(new Point2D(0.75, 0.25));
        brute.insert(new Point2D(0.0, 0.5));
        brute.insert(new Point2D(0.125, 0.875));
        brute.insert(new Point2D(0.75, 0.75));
        brute.insert(new Point2D(1.0, 0.625));
        brute.insert(new Point2D(0.625, 0.375));
        brute.insert(new Point2D(0.25, 1.0));
        brute.insert(new Point2D(0.5, 0.125));
        brute.insert(new Point2D(1.0, 0.125));
        brute.insert(new Point2D(0.0, 1.0));
        brute.insert(new Point2D(0.25, 0.375));
        brute.insert(new Point2D(1.0, 0.0));
        brute.insert(new Point2D(1.0, 0.375));  
        brute.insert(new Point2D(0.125, 0.625));
        brute.insert(new Point2D(0.75, 0.625));
        brute.insert(new Point2D(0.875, 0.125));
        brute.insert(new Point2D(0.0, 0.25));
        StdOut.println(brute.contains(new Point2D(0.25, 0.375)));       
    }
    
    public static void testNearest(){
        PointSET brute = new PointSET();
//      A 0.372 0.497 
//      B 0.564 0.413 
//      C 0.226 0.577
//      D 0.144 0.179 
//      E 0.083 0.51 
//      F 0.32 0.708 
//      G 0.417 0.362 
//      H 0.862 0.825 
//      I 0.785 0.725 
//      J 0.499 0.208
//      query point (0.72, 0.81)
//      student nearest() = (0.785, 0.725)
//      distanceSquaredTo() = 0.01145
//      student sequence: A C D E F B G J H I
//      reference sequence : A B H I 
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
        StdOut.println(brute.nearest(new Point2D(0.72, 0.81)));
    }
    
    public static void testRange(){
        PointSET brute = new PointSET();
        brute.insert(new Point2D(0.7, 0.2));
        brute.insert(new Point2D(0.5, 0.4));
        brute.insert(new Point2D(0.2, 0.3));
        brute.insert(new Point2D(0.4, 0.7));
        brute.insert(new Point2D(0.9, 0.6));
        RectHV rect = new RectHV(0.6, 0.1, 0.98, 0.7);
        for (Point2D p: brute.range(rect)) {
            StdOut.println(p);
        }
    }
    
    public static void main(String[] args) {
        // unit testing of the methods (optional) 
        testRange();

    }
}
