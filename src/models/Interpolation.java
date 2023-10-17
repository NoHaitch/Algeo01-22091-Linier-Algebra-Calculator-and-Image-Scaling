package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import operations.Matrix;
import operations.OBE;
import operations.Point;

public class Interpolation {
    public Matrix point;
    public double xRequest;
    public Interpolation(){
        this(0);
    }

    public Interpolation(int JmlhPoint){
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
        int i, j, n,itemp;
        boolean foundp;
        Point p = new Point();
        Scanner scan = new Scanner(System.in);
        System.out.print("Masukkan banyak titik: ");
        n = scan.nextInt();
        Matrix m = new Matrix(n,2);
        for (i = 0; i < n ; i++){
            foundp = false;
            while (!foundp){
                p.readPoint();
                if (i == 0){
                    foundp = true;
                }
                for (itemp = 0; itemp < i; itemp++){
                    if (p.getX() == m.getElmt(itemp, 0) || p.getY() == m.getElmt(itemp, 1)){
                        foundp = true;
                    }
                }
            }
            m.setElmt(p.getX(), i, 0);
            m.setElmt(p.getY(), i, 1);
        }
        return m;
    }
    
    public void displayFunction(OBE meselon){
        // display f(x)
        int i,j = meselon.getMatrixCol() - 1,n;
        n = meselon.getMatrixRow() - 1;
        System.out.print("f(X) = ");
        for (i = 0; i < meselon.getMatrixRow(); i++){
            if (i == meselon.getMatrixRow() - 1 && i != 0){
                if (meselon.getMElmt(i,j) >= 0){
                    System.out.print(" + " + meselon.getMElmt(i,j));
                } else{
                    System.out.print(" "+ meselon.getMElmt(i,j));
                }
            } else if (i == 0){
                System.out.print(meselon.getMElmt(i,j) + "X^" + n);
            } else{
                if (meselon.getMElmt(i,j) >= 0){
                    System.out.print(" + " + meselon.getMElmt(i,j) + "X^" + n);
                } else{
                    System.out.print(" " + meselon.getMElmt(i,j) + "X^" + n);
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

    public boolean askDataPointFromFile(String path){
        Matrix temp = new Matrix(200, 150);
        int i,j;
        int row = 0;
        int column = 2;
        try {
            File inputFile = new File(path);
            Scanner readFile = new Scanner(inputFile);
            String line;
            boolean endLine = false;
            while (readFile.hasNextLine() && (line = readFile.nextLine()) != null && !endLine){
                String[] saved = line.split(" ");
                int len = saved.length;
                if (len == 2){
                    for (i = 0; i < 2; i++){
                        double tempdouble = Double.parseDouble(saved[i]);
                        temp.setElmt(tempdouble, row, i);
                    }
                    row++;
                } else if(len == 1){
                    xRequest = Double.parseDouble(saved[0]);
                    endLine = true;
                } else {
                    return false;
                }
            }
            if(endLine && readFile.hasNextLine()){
                return false;
            } else if(!endLine){
                return false; // tidak ada nilai untuk ditaksir
            } else {
                temp.setRowEff(row);
                temp.setColEff(column);
                readFile.close();
            }
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        // TODO: handle exception
        }
        point = new Matrix(temp.getRowEff(), 2);
        for (i = 0; i < temp.getRowEff(); i++ ){
            for (j = 0; j < 2; j++){
                point.setElmt(temp.getElmt(i, j), i, j);
            }
        }
        return true;
    }

    public void uploadhasil2File(double taksiran, double xRequest,String filehasil, String text){
        try {
            PrintWriter write = new PrintWriter(filehasil);
            write.write(text);
            write.write("f(");
            write.print(xRequest);
            write.write(") = ");
            write.print(taksiran);
            write.close();
            System.out.println("\nPenyelesaian berhasil disimpan ke : "+filehasil+"\n\n");
        } catch (FileNotFoundException e) {
            System.out.println("File tidak dapat disimpan");
        }
    }
}
