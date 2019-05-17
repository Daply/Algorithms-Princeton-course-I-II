package course;
public class LineSegment {
    
    private Point p;
    private Point q;
    
    public LineSegment(Point p, Point q) {
        // constructs the line segment between points p and q
        this.p = p;
        this.q = q;
    }
    public void draw() {
        // draws this line segment
        p.drawTo(q);
    }
    public String toString() {
        // string representation
        return p.toString() + " -> " + q.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LineSegment other = (LineSegment) obj;
        if (p == null) {
            if (other.p != null)
                return false;
        } else if (p.compareTo(other.p) != 0)
            return false;
        if (q == null) {
            if (other.q != null)
                return false;
        } else if (q.compareTo(other.q) != 0)
            return false;
        return true;
    }
 
}
