package operations;
import operations.Matrix;
import models.Point;
import java.util.Scanner;
public class interpolation {
    Matrix m = new Matrix();
    public static Matrix convertPtoM (Matrix m){ 
        // convert Matrix point to Matrix SPL
        int i, j, n, k;
        double x,y;
        n = m.getRowEff();
        Matrix newm = new Matrix(n,n + 1);
        for (i = 0; i < n;i++){
            x = m.getElmt(i, 0);
            y = m.getElmt(i, 1); 
            k = 1;
            for (j = n; j >= 0; j--){
                if (j == n){
                    newm.setElmt(y, i, j);
                } else{
                    newm.setElmt(k,i,j);
                    k*= x;
                }
            }
        }
        return newm;
    }
    
    public static Matrix askDataPoint() {
        // ask how many data and store them in Matrix for Point
        int i, j, n;
        Point p = new Point();
        Scanner scan = new Scanner(System.in);
        System.out.print("Masukkan banyak titik: ");
        n = scan.nextInt();
        Matrix m = new Matrix(n,2);
        for (i = 0; i < n ; i++){
            p.readPoint();
            m.setElmt(p.getX(), i, 0);
            m.setElmt(p.getY(), i, 1);
        }
        return m;
    }
    
    public static void displayFunction(OBE meselon){
        // display f(x)
        int i,j = meselon.getMatrixCol() - 1,n;
        n = meselon.getMatrixRow() - 1;
        System.out.print("f(x) = ");
        for (i = 0; i < meselon.getMatrixRow(); i++){
            if (i == meselon.getMatrixRow() - 1 && i != 0){
                if (meselon.getMElmt(i,j) >= 0){
                    System.out.print(" + " + meselon.getMElmt(i,j));
                } else{
                    System.out.print(" "+ meselon.getMElmt(i,j));
                }
            } else if (i == 0){
                System.out.print(meselon.getMElmt(i,j) + "x^" + n);
            } else{
                if (meselon.getMElmt(i,j) >= 0){
                    System.out.print(" + " + meselon.getMElmt(i,j) + "x^" + n);
                } else{
                    System.out.print(" " + meselon.getMElmt(i,j) + "x^" + n);
                }
            }
            n -= 1;
        }
    }
    
    public static double taksiran(OBE meselon,double input){
        // return f(x) with x = input
        int i,j = meselon.getMatrixCol() - 1;
        Matrix Mnilai = new Matrix(1,meselon.getMatrixRow());
        for (i = 0; i < meselon.getMatrixRow(); i++){
            Mnilai.setElmt(meselon.getMElmt(i, j), 0, i);
        }
        double taksiran = 0;
        int k = 1;
        for (j = Mnilai.getColEff() - 1; j >= 0; j--){
            if (j == Mnilai.getColEff() - 1){
                taksiran += Mnilai.getElmt(0, j);
            }else{
                taksiran += Mnilai.getElmt(0, j) * input * k;
                k *= input;
            }
        }
        return taksiran;
    }
}
