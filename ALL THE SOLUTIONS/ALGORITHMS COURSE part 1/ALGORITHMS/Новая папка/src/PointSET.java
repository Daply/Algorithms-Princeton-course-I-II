import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
// import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
    
    private Set<Point2D> pointSet = null;
    
    public PointSET() {
        // construct an empty set of points 
        this.pointSet = new TreeSet<Point2D>();
    }
    
    public boolean isEmpty() {
        // is the set empty? 
        return this.pointSet.isEmpty();
    }
    
    public int size() {
        // number of points in the set 
        return this.pointSet.size();
    }
    
    public void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        this.pointSet.add(p);
    }
    
    public boolean contains(Point2D p) {
        // does the set contain point p? 
        return this.pointSet.contains(p);
    }
    
    public void draw() {
        // draw all points to standard draw 
//        for (Point2D p: this.pointSet) {
//            StdDraw.point(p.x(), p.y());
//        }
    }
    
    public Iterable<Point2D> range(RectHV rect) {
        // all points that are inside the rectangle (or on the boundary) 
        List<Point2D> pointsInRect = new ArrayList<>();
        for (Point2D p: this.pointSet) {
            if (rect.contains(p)) {
                pointsInRect.add(p);
            }
        }
        return pointsInRect;
    }
    
    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to point p; null if the set is empty
        double minDistance = 2;
        double dist = 0;
        Point2D neighbor = null;
        for (Point2D p1: this.pointSet) {
            dist = p.distanceSquaredTo(p1);
            if (dist < minDistance) {
                neighbor = p1;
            }
        }
        return neighbor;
    }

    public static void main(String[] args) {
        // unit testing of the methods (optional) 
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0.372, 0.497));
        pointSET.insert(new Point2D(0.564, 0.413));
        pointSET.insert(new Point2D(0.226, 0.577));
        pointSET.insert(new Point2D(0.144, 0.179));
        pointSET.insert(new Point2D(0.083, 0.510));
        pointSET.insert(new Point2D(0.320, 0.708));
        pointSET.insert(new Point2D(0.417, 0.362));
        pointSET.insert(new Point2D(0.862, 0.825));
        pointSET.insert(new Point2D(0.785, 0.725));
        pointSET.insert(new Point2D(0.499, 0.208));
        
           // for check
//         pointSET.insert(new Point2D(0.1, 0.2));
//         pointSET.insert(new Point2D(0.5, 0.1));
//         pointSET.insert(new Point2D(0.3, 0.5));
        
        // set draw options
//         StdDraw.clear();
//         StdDraw.setPenColor(StdDraw.BLACK);
//         StdDraw.setPenRadius(0.01);
//         StdDraw.enableDoubleBuffering();

//         pointSET.draw();
//         StdDraw.show();
        
        for (Point2D p: pointSET.pointSet) {
            System.out.println(p);
        }
        
    }
}
