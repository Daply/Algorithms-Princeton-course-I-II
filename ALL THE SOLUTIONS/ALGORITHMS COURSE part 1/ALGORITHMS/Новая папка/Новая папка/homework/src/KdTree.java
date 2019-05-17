import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class KdTree {

    private Node root = null;
    
    public KdTree() {
        // construct an empty set of points 
    }
    
    public boolean isEmpty() {
        // is the set empty? 
        return this.root == null;
    }
    
    public int size() {
        // number of points in the set 
        return this.root.size;
    }
    
    public void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        if (this.root == null) {
            this.root = new Node(p, 0, true);
            RectHV resultRect = new RectHV(0, 0, 1, 1);
            this.root.rect = resultRect;
        }
        else {
            Node newNode = new Node(p, 0, true);
            insertNode(this.root, newNode);
        }
    }
    
    private void insertNode(Node node, Node newNode) {
        newNode.color = !node.color;
        // if color RED (by y)
        if (newNode.color) {
            if (newNode.point.y() < node.point.y()) {
                if (node.left != null) {
                    insertNode(node.left, newNode);
                }
                else {
                    node.left = newNode;
                    RectHV resultRect = new RectHV(node.rect.xmin(), node.rect.ymin(),
                            node.rect.xmax(), node.point.y());
                    newNode.rect = resultRect;
                }
            }
            else if (newNode.point.y() > node.point.y()) {
                if (node.right != null) {
                    insertNode(node.right, newNode);
                }
                else {
                    node.right = newNode;
                    RectHV resultRect = new RectHV(node.rect.xmin(), node.point.y(),
                            node.rect.xmax(), node.rect.ymax());
                    newNode.rect = resultRect;
                }
            }
            // TODO check that
            else if (newNode.point.x() != node.point.x()) {
                // like color BLUE
                if (newNode.point.x() < node.point.x()) {
                    if (node.left != null) {
                        insertNode(node.left, newNode);
                    }
                    else {
                        node.left = newNode;
                        RectHV resultRect = new RectHV(node.rect.xmin(), node.rect.ymin(),
                                node.point.x(), node.rect.ymax());
                        newNode.rect = resultRect;
                    }
                }
                else if (newNode.point.x() > node.point.x()) {
                    if (node.right != null) {
                        insertNode(node.right, newNode);
                    }
                    else {
                        node.right = newNode;
                        RectHV resultRect = new RectHV(node.point.x(), node.rect.ymin(),
                                node.rect.xmax(), node.rect.ymax());
                        newNode.rect = resultRect;
                    }
                }
            }
        }
        // if color BLUE (by x)
        else {
            if (newNode.point.x() < node.point.x()) {
                if (node.left != null) {
                    insertNode(node.left, newNode);
                }
                else {
                    node.left = newNode;
                    RectHV resultRect = new RectHV(node.rect.xmin(), node.rect.ymin(),
                            node.point.x(), node.rect.ymax());
                    newNode.rect = resultRect;
                }
            }
            else if (newNode.point.x() > node.point.x()) {
                if (node.right != null) {
                    insertNode(node.right, newNode);
                }
                else {
                    node.right = newNode;
                    RectHV resultRect = new RectHV(node.point.x(), node.rect.ymin(),
                            node.rect.xmax(), node.rect.ymax());
                    newNode.rect = resultRect;
                }
            }
            // TODO check that
            else if (newNode.point.y() != node.point.y()) {
                // like color RED
                if (newNode.point.y() < node.point.y()) {
                    if (node.left != null) {
                        insertNode(node.left, newNode);
                    }
                    else {
                        node.left = newNode;
                        RectHV resultRect = new RectHV(node.rect.xmin(), node.rect.ymin(),
                                node.rect.xmax(), node.point.y());
                        newNode.rect = resultRect;
                    }
                }
                else if (newNode.point.y() > node.point.y()) {
                    if (node.right != null) {
                        insertNode(node.right, newNode);
                    }
                    else {
                        node.right = newNode;
                        RectHV resultRect = new RectHV(node.rect.xmin(), node.point.y(),
                                node.rect.xmax(), node.rect.ymax());
                        newNode.rect = resultRect;
                    }
                }
            }
        } 
    }
    
    public boolean contains(Point2D p) {
        // does the set contain point p? 
        Node node = this.root;
        while (node != null) {
            // if color RED (by y)
            if (node.color) {
                if (p.y() < node.point.y()) {
                    node = node.left;
                }
                else if (p.y() > node.point.y()) {
                    node = node.right;
                }
                else if (node.point.x() == p.x()) {
                    return true;
                }
                else {
                    return false;
                }
            }
            // if color BLUE (by x)
            else {
                if (p.x() < node.point.x()) {
                    node = node.left;
                }
                else if (p.x() > node.point.x()) {
                    node = node.right;
                }
                else if (node.point.y() == p.y()) {
                    return true;
                }
                else {
                    return false;
                }
                node = node.left;
            }
        }
        return false;
    }
    
    public void draw() {
        // draw all points to standard draw
        drawSubTree(this.root);
    }
    
    private void drawSubTree(Node node) {
        StdDraw.point(node.point.x(), node.point.y());
        if (node.left != null) {
            drawSubTree(node.left);
        }
        if (node.right != null) {
            drawSubTree(node.right);
        }
    }
    
    
    public Iterable<Point2D> range(RectHV rect) {
        // all points that are inside the rectangle (or on the boundary) 
        List<Point2D> pointsInRect = new ArrayList<>();
        
        pointsInRect = (List<Point2D>) findPoints(this.root, pointsInRect, rect);
        
        return pointsInRect;
    }
    
    private Iterable<Point2D> findPoints(Node node, List<Point2D> pointsInRect, RectHV rect) {
        // check if rect contains points
        if (rect.contains(node.point)) {
            pointsInRect.add(node.point);
        }

        RectHV resultRect = node.rect; 
        StdDraw.filledRectangle(resultRect.xmin(), resultRect.ymin(), resultRect.width()/2, resultRect.height()/2);
        if (rect.intersects(resultRect)) {
            if (node.left != null) {
                findPoints(node.left, pointsInRect, rect);
            }
            if (node.right != null) {
                findPoints(node.right, pointsInRect, rect);
            }
        }
 
        return pointsInRect; 
    }
    
    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to point p; null if the set is empty
        double minDistance = 2;
        Point2D neighbor = null;
        neighbor = searchNearestPoint(minDistance, p, neighbor, this.root); 
        return neighbor;
    }
    
    private Point2D searchNearestPoint(double minDistance, Point2D p, Point2D neighbor, Node node) {
        // a nearest neighbor in the set to point p; null if the set is empty
        double pointsDist = 0;
        
        Point2D nodePoint = node.point;
        pointsDist = nodePoint.distanceSquaredTo(p);
        if (pointsDist < minDistance) {
            minDistance = pointsDist;
            neighbor = nodePoint;
        }
        
        if (node.rect.distanceSquaredTo(p) <= pointsDist) {
            if (node.left != null) {
                neighbor = searchNearestPoint(minDistance, p, neighbor, node.left);
            }
            if (node.right != null) {
                neighbor = searchNearestPoint(minDistance, p, neighbor, node.right);
            }
        }
        
        return neighbor;
    }
    
    private class Node {
        private Point2D point;     // associated data
        private RectHV rect;       // associated rectangle
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree
        private boolean color;     // RED (by y) = true, BLUE (by x) = false

        public Node(Point2D point, int size, boolean color) {
            this.point = point;
            this.size = size;
            this.color = color;
        }
    }

    public static void main(String[] args) {
        // unit testing of the methods (optional) 
        KdTree kdTree = new KdTree();
//        kdTree.insert(new Point2D(0.372, 0.497));
//        kdTree.insert(new Point2D(0.564, 0.413));
//        kdTree.insert(new Point2D(0.226, 0.577));
//        kdTree.insert(new Point2D(0.144, 0.179));
//        kdTree.insert(new Point2D(0.083, 0.510));
//        kdTree.insert(new Point2D(0.320, 0.708));
//        kdTree.insert(new Point2D(0.417, 0.362));
//        kdTree.insert(new Point2D(0.862, 0.825));
//        kdTree.insert(new Point2D(0.785, 0.725));
//        kdTree.insert(new Point2D(0.499, 0.208));
        
        // example
        kdTree.insert(new Point2D(0.7, 0.2));
        kdTree.insert(new Point2D(0.5, 0.4));
        kdTree.insert(new Point2D(0.2, 0.3));
        kdTree.insert(new Point2D(0.4, 0.7));
        kdTree.insert(new Point2D(0.9, 0.6));
        
        RectHV rect = new RectHV(0.6, 0.1, 0.98, 0.7);
        kdTree.range(rect);
        
        for (Point2D p: kdTree.range(rect)) {
            StdOut.println(p);
        }
        
        kdTree.range(rect);
        Point2D neighborFind = new Point2D(0, 0);
        StdOut.println(kdTree.nearest(neighborFind));
        
           // for check
//         pointSET.insert(new Point2D(0.1, 0.2));
//         pointSET.insert(new Point2D(0.5, 0.1));
//         pointSET.insert(new Point2D(0.3, 0.5));
        
        // set draw options
//         StdDraw.clear();
//         StdDraw.setPenColor(StdDraw.BLACK);
//         StdDraw.setPenRadius(0.01);
//         StdDraw.enableDoubleBuffering();
//
//         kdTree.draw();
//         StdDraw.show();
        
        
        
    }
}
