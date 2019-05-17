import edu.princeton.cs.algs4.In;
// import edu.princeton.cs.algs4.StdDraw;
// import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    
    private final LineSegment[] lineSegments;
    private int numberOfSegments;
    
   public BruteCollinearPoints(Point[] points) {
       // finds all line segments containing 4 points
       if (points == null)
           throw new IllegalArgumentException("array of points is null");
       Point[] pointsUsed = new Point[points.length];
       for (int i = 0; i < points.length; i++) {
           if (points[i] == null)
               throw new IllegalArgumentException("point is null");
           for (int j = i+1; j < points.length; j++) {
               if (points[j] != null && points[i].compareTo(points[j]) == 0)
                   throw new IllegalArgumentException("duplicate points are exist");
           }
           pointsUsed[i] = points[i];
       }
       
       int size = pointsUsed.length;
       Point[] pointsMin = new Point[size];
       Point[] pointsMax = new Point[size];
       int indexOfPoints = 0;
       
       for (int i = 0; i < pointsUsed.length; i++) {
            for (int j = i+1; j < pointsUsed.length; j++) {
                 for (int k = j+1; k < pointsUsed.length; k++) {
                      for (int t = k+1; t < pointsUsed.length; t++) {
                               double slope1 = pointsUsed[i].slopeTo(pointsUsed[j]);
                               double slope2 = pointsUsed[i].slopeTo(pointsUsed[k]);
                               double slope3 = pointsUsed[i].slopeTo(pointsUsed[t]);
                               if (slope1 == slope2 && slope2 == slope3) {
                                                   
                                   Point p1, p2;
                                   p1 = null;
                                   p2 = null;
                                                 
                                   if (pointsUsed[i].compareTo(pointsUsed[j]) < 0 &&
                                           pointsUsed[i].compareTo(pointsUsed[k]) < 0 &&
                                           pointsUsed[i].compareTo(pointsUsed[t]) < 0) {
                                       p1 = pointsUsed[i];
                                   }
                                   if (pointsUsed[j].compareTo(pointsUsed[i]) < 0 &&
                                           pointsUsed[j].compareTo(pointsUsed[k]) < 0 &&
                                           pointsUsed[j].compareTo(pointsUsed[t]) < 0) {
                                       p1 = pointsUsed[j];
                                   }
                                   if (pointsUsed[k].compareTo(pointsUsed[i]) < 0 &&
                                           pointsUsed[k].compareTo(pointsUsed[j]) < 0 &&
                                           pointsUsed[k].compareTo(pointsUsed[t]) < 0) {
                                       p1 = pointsUsed[k];
                                   }
                                   if (pointsUsed[t].compareTo(pointsUsed[i]) < 0 &&
                                           pointsUsed[t].compareTo(pointsUsed[j]) < 0 &&
                                           pointsUsed[t].compareTo(pointsUsed[k]) < 0) {
                                       p1 = pointsUsed[t];
                                   }  
                                   
                                   if (pointsUsed[i].compareTo(pointsUsed[j]) > 0 &&
                                           pointsUsed[i].compareTo(pointsUsed[k]) > 0 &&
                                           pointsUsed[i].compareTo(pointsUsed[t]) > 0) {
                                       p2 = pointsUsed[i];
                                   }
                                   if (pointsUsed[j].compareTo(pointsUsed[i]) > 0 &&
                                           pointsUsed[j].compareTo(pointsUsed[k]) > 0 &&
                                           pointsUsed[j].compareTo(pointsUsed[t]) > 0) {
                                       p2 = pointsUsed[j];
                                   }
                                   if (pointsUsed[k].compareTo(pointsUsed[i]) > 0 &&
                                           pointsUsed[k].compareTo(pointsUsed[j]) > 0 &&
                                           pointsUsed[k].compareTo(pointsUsed[t]) > 0) {
                                       p2 = pointsUsed[k];
                                   }
                                   if (pointsUsed[t].compareTo(pointsUsed[i]) > 0 &&
                                           pointsUsed[t].compareTo(pointsUsed[j]) > 0 &&
                                           pointsUsed[t].compareTo(pointsUsed[k]) > 0) {
                                       p2 = pointsUsed[t];
                                   }      

                                   if (p1 != null && p2 != null) {
                                       pointsMin[indexOfPoints] = p1;
                                       pointsMax[indexOfPoints] = p2;
                                       indexOfPoints++;
                                   }
                           } 
                      }
                  }
              }
          }
       
       
       for (int i = 0; i < pointsUsed.length; i++) {
            if (pointsUsed[i] != null) {
                for (int j = 0; j < indexOfPoints; j++) {
                     double slope1 = pointsUsed[i].slopeTo(pointsMin[j]);
                     double slope2 = pointsUsed[i].slopeTo(pointsMax[j]);
                       
                     if (slope1 == slope2) {                         
                         if (pointsUsed[i].compareTo(pointsMin[j]) < 0) {
                             pointsMin[j] = pointsUsed[i];
                         }                       
                         if (pointsUsed[i].compareTo(pointsMax[j]) > 0) {
                             pointsMax[j] = pointsUsed[i];
                         }
                     }  
                }
            }
       }
       
       Point p1, p2;
       lineSegments = new LineSegment[indexOfPoints];
       numberOfSegments = 0;
       for (int i = 0; i < indexOfPoints; i++) {
           p1 = pointsMin[i];
           p2 = pointsMax[i];
           // StdOut.println("-----------------------------");
           // double slope = pointsMin[i].slopeTo(pointsMax[i]);
           // StdOut.println(" slope = " + slope);
           // StdOut.println();
           LineSegment line = new LineSegment(p1, p2);
           lineSegments[numberOfSegments] = line;
           numberOfSegments++;
       }
       
       
   }
   public int numberOfSegments() {
       // the number of line segments
       return numberOfSegments;
   }
   public LineSegment[] segments() {
       // the line segments
       LineSegment[] copy = lineSegments.clone();
       return copy;
   }
   
   public static void main(String[] args) {

       // read the n points from a file
       In in = new In(args[0]);
       int n = in.readInt();
       Point[] points = new Point[n];
       for (int i = 0; i < n; i++) {
           int x = in.readInt();
           int y = in.readInt();
           points[i] = new Point(x, y);
       }

       // draw the points
       // StdDraw.enableDoubleBuffering();
       // StdDraw.setXscale(0, 32768);
       // StdDraw.setYscale(0, 32768);
       // for (Point p : points) {
       //     p.draw();
       // }
       // StdDraw.show();

       // print and draw the line segments
       BruteCollinearPoints collinear = new BruteCollinearPoints(points);
       for (LineSegment segment : collinear.segments()) {
           if (segment != null) {
               StdOut.println(segment);
               // segment.draw();
           }
       }
       // StdDraw.show();
   }
}