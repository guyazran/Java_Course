package appcanvas;

/**
 * Created by guyazran on 6/25/15.
 */
public class Segment {
    private Point p1;
    private Point p2;
    private double length; // a variable that exists to hold the value of length and not have to calculate it over and over (better performance, takes more space)
    private boolean lengthCalculated = false;

    public Segment(Point p1, Point p2) {
        setP1(p1);
        setP2(p2);
    }

    public Segment(Segment segment){
        this(segment.getP1(), segment.getP2());
    }

    public Point getP1() {
        return new Point(p1);
    }

    public void setP1(Point p1) {
        this.p1 = new Point(p1);
        lengthCalculated = false;
    }

    public Point getP2() {
        return new Point(p2);
    }

    public void setP2(Point p2) {
        this.p2 = new Point(p2);
        lengthCalculated = false; // if a point is changed, the length must be recalculated
    }

    private double length(){ // this method will only calculate the length of the segment on the first time
        if (!lengthCalculated)
            calculateLength();
        return length;
    }

    public void calculateLength(){
        length = p1.distanceFromPoint(p2);
        lengthCalculated = true;
    }

    public double slope(){
        double deltaX = p1.getXpos() - p2.getXpos();
        double deltaY = p1.getYpos() - p2.getYpos();
        if(deltaX == 0){
            return Double.MAX_VALUE; //"infinity"
        }
        return deltaY/deltaX;
    }

    public double distanceFromPoint(Point p){
        double x1, y1, x2, y2, x0, y0;
        x1 = this.p1.getXpos();
        y1 = this.p1.getYpos();
        x2 = this.p2.getXpos();
        y2 = this.p2.getYpos();
        x0 = p.getXpos();
        y0 = p.getYpos();
        double temp = (y2 - y1)*x0 - (x2 - x1)*y0 + x2*y1 - y2*x1;
        if(temp<0){
            temp *= -1;
        }
        return temp/length();
    }

    double perpendicularSlope(){
        return (-1/this.slope());
    }

    double yIntercept(){
        return yIntercept(p1, this.slope());
    }

    double yIntercept(Point p, double slope){
        return p.getYpos() - (p.getXpos()*slope);
    }

    double findCommonX(double slope, double yIntercept){
        return (yIntercept - this.yIntercept())/(this.slope() - slope);
    }

    double findCommonY(double x){
        return this.slope()*x + this.yIntercept();
    }

    public boolean isPointOnSegment(Point p){
        int leftX = p1.getXpos();
        int rightX = p2.getXpos()
        if(leftX > rightX) {
            leftX = p2.getXpos();
            rightX = p1.getXpos()
        }

        double distance = distanceFromPoint(p);

        if(p.getXpos() >= p1.getXpos() && p <=)
        return distance > -1 && distance < 1;
    }

    @Override
    public String toString() {
        return "The segment starts at " + p1 + "and ends at" + p2 + ". The linear equation is y = " + this.slope() + "x + " + this.yIntercept();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj instanceof Segment) {
            Segment other = new Segment((Segment) obj);
            return this.p1.equals(other.p1) && this.p2.equals(other.p2);
        }
        return false;
    }
}
