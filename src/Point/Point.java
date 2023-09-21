package Point;

public class Point {
    private double x, y;
    public Point(){
        // construct
        // you can put default values here
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void printPoint() {
        System.out.printf("<%f,", x);
        System.out.printf("%f>\n", y);
    }
    public  boolean isOrigin(){
        return (x == 0 && y == 0);
    }
    public  boolean isOnXAxis(){
        return y == 0;
    }
    public  boolean isOnYAxis(){
        return x == 0;
    }
    public int kuadran(){
        if (x> 0) {
            if (y > 0) {
                return 1;
            }
            else{
                return 4;
            }
        }
        else {
            if (y > 0) {
                return 2;
            }
            else {
                return 3;
            }
        }
    }
    public double jarakKe(Point P){
        // Prekondisi : Point this != Point P
        return Math.sqrt((x*x) + (y*y));
    }
}
