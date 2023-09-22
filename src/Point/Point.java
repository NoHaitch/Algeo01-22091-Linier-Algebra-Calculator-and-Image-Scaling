package Point;

import java.util.Scanner;

public class Point {
    private double x, y;
    public Point(){
        // construct
        // you can also put default values here
    }
    public void readPoint(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan X dan Y: ");
        String input1 = sc.next();
        String input2 = sc.next();
        boolean valid = true;
        try {
            this.x = Double.parseDouble(input1);
            this.y = Double.parseDouble(input2);
        } catch (NumberFormatException nfe) {
            valid = false;
            System.out.println("Masukkan tidak valid");
            System.out.println("contoh valid : 4.11 -1.33");
        }
        while (!valid){
            System.out.print("Masukkan X dan Y: ");
            input1 = sc.next();
            input2 = sc.next();
            try {
                this.x = Double.parseDouble(input1);
                this.y = Double.parseDouble(input2);
                valid = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Masukkan tidak valid");
                System.out.println("contoh valid : 4.11 -1.33");
            }
        }
    }
    public void printPoint() {
        /* Print <x,y> without \n */
        System.out.printf("<%f,", x);
        System.out.printf("%f>", y);
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

    public  boolean isOrigin(){
        return (x == 0 && y == 0);
    }

    public boolean isPointEqual(Point P){
        return (this.x == P.x && this.y == P.y);
    }

    public boolean isPointNotEqual(Point P){
        return !(this.x == P.x && this.y == P.y);
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