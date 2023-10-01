package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import operations.Matrix;
import operations.OBE;

public class interpolation {
    public Matrix point;
    public double xRequest;
    public interpolation(){
        this(0);
    }

    public interpolation(int JmlhPoint){
        point = new Matrix(JmlhPoint, 2);
    }

    public Matrix convertPtoM (Matrix m){ 
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
    
    public Matrix askDataPoint() {
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
    
    public void displayFunction(OBE meselon){
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
    
    public double taksiran(OBE meselon,double input){
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

    public void askDataPointFromFile(String path){
        SPL temp = new SPL();
        int i,j;
        try {
            File inputFile = new File(path);
            Scanner readFile = new Scanner(inputFile);
            String line;
            int row = 0;
            int column = 2;
            while (readFile.hasNextLine() && (line = readFile.nextLine()) != null){
                String[] saved = line.split(" ");
                int len = saved.length;
                if (len == 2){
                    for (i = 0; i < column; i++){
                        double tempdouble = Double.parseDouble(saved[i]);
                        temp.getSPL().setMElmt(tempdouble, row, i);
                    }
                    row++;
                } else{
                    xRequest = Double.parseDouble(saved[0]);
                }
            }
            temp.getSPL().setMatrixRow(row);
            temp.getSPL().setMatrixCol(column);
            readFile.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        // TODO: handle exception
        }
        this.point = new Matrix(temp.spl.getMatrixRow(), 2);
        for (i = 0; i <= temp.spl.getMatrixRow(); i++ ){
            for (j = 0; j < 2; j++){
                point.setElmt(temp.spl.getMElmt(i, j), i, j);
            }
        }
        point.displayMatrix();
    }
}
