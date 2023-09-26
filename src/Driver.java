import operations.Matrix;
import operations.interpolation;
import models.Point;
import java.util.Scanner;
public class Driver {
    // interpolation ip = new interpolation();
    public static void main(String[] args) {
        int i, j, n;
        Point p = new Point();
        System.out.print("Masukkan banyak titik: ");
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        Matrix m = new Matrix(n,2);
        for (i = 0; i < n ; i++){
            p.readPoint();
            m.setElmt(p.getX(), i, 0);
            m.setElmt(p.getY(), i, 1);
        }
        m.displayMatrix();
        Matrix newm = interpolation.convertPtoM(m);
        newm.displayMatrix();
        // 3
        // (1,1) 
        // (2,2) 
        // (3,3)

    }
}