//import operations.OBE;
import models.Determinan;

public class Driver {
    public static void main(String[] args) {
        Determinan x = new Determinan(3,3);
        //double [][] mtr = new double[][]{{1,2,1},{1,2,1},{1,2,3}};
        //for (int i = 0; i < 3; i++){
        //    for (int j = 0; j < 3; j++){
        //        //x.contents.setMElmt(mtr[i][j],i,j);
        //    }
        //}
        x.inputMatriksFile();
        double det = x.determinanKofaktor();
        x.CalculateOBE();
        x.saveToTextFile("test/DeterminanDuluGan.txt");
        System.out.println(x.contents.getStep());
        System.out.println(x.result+"\n");
        for (int i = 0; i < x.countMul; i++){
            System.out.println(x.multiply[i]);
        }
        System.out.println("\n"+det);
    }
}
/*
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
*/