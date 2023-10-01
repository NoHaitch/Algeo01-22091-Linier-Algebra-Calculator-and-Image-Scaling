package models;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import operations.Matrix;
import operations.OBE;
public class regresi {
    public Matrix regresiM;
    
    public Matrix dataRegresiM;
    
    public Matrix listnilaivar;
    
    private int jumlahPeubahValid;
    
    private int banyakSampelValid;
    
    public regresi(){
        this(0,0);
        dataRegresiM = new Matrix (0,0);
        listnilaivar = new Matrix(0,0);
    }
    
    public regresi(int banyakSampel,int jumlahPeubah){
        jumlahPeubahValid = jumlahPeubah;
        banyakSampelValid = banyakSampel;
        regresiM = new Matrix(banyakSampel + 1,jumlahPeubah + 2);
        dataRegresiM = new Matrix(banyakSampel,jumlahPeubah + 1);
        listnilaivar = new Matrix (1,banyakSampel);
    }
    
    public int getjumlahPeubah(){
        return this.jumlahPeubahValid;
    }

    public int getbanyakSampel(){
        return this.banyakSampelValid;
    }
    
    public void askDatareg(int sampel,int var){
        // sebelumnya sudah dapat ukuran matrix
        int i,j,count = 1;
        String line;
        Scanner scan = new Scanner(System.in);
        for (i = 0; i < sampel; i++){
            System.out.print("Masukkan nilai nilai sampel ke-" + count + " : ");
            count+= 1;
            line = scan.nextLine();
            String[] saved = line.split(" ");
            int k;
            for (k = 0; k < saved.length; k++){
                double temp = Double.parseDouble(saved[k]);
                dataRegresiM.setElmt(temp, i, k);
            }
        }
    }
    // 1 2 3 Y
    // 4 5 6 Y
    // 7 8 9 Y
    // b0   b1  b2  b3  y

    // 1 2 3 4 Y
    // 5 6 7 8 Y
    // 9 10 11 12 Y
    // 13 14 15 16 Y
    // b0   b1  b2  b3  b4  y

    public void convertData2Reg(Matrix dataRegresiM){
        int i,j,k;
        double temp;
        regresiM = new Matrix(dataRegresiM.getColEff(),dataRegresiM.getColEff() + 1);
        for (j = 0; j < regresiM.getColEff(); j++){
            if (j == 0){
                regresiM.setElmt(dataRegresiM.getColEff() - 1,0,0);
            } else {
                temp = 0;
                for (k = 0; k < dataRegresiM.getRowEff(); k++){
                    temp += dataRegresiM.getElmt(k,j - 1);
                }
                regresiM.setElmt(temp, 0, j);
            }
        }
        for (j = 0; j < regresiM.getColEff(); j++){
            for (i = 1; i < regresiM.getRowEff(); i++){
                temp = 0;
                if (j == 0){
                    for (k = 0; k < dataRegresiM.getRowEff(); k++){
                        temp += dataRegresiM.getElmt(k,i - 1);
                    }
                } else if ( j != regresiM.getColEff()){
                    for (k = 0; k <dataRegresiM.getRowEff(); k++){
                        temp += dataRegresiM.getElmt(k,i - 1) * dataRegresiM.getElmt(k,j - 1);
                    }
                } else{
                    for (k = 0; k <dataRegresiM.getRowEff(); k++){
                        temp += dataRegresiM.getElmt(k,i - 1) * dataRegresiM.getElmt(k,j - 1);
                    }
                }
                regresiM.setElmt(temp, i, j);
            }
        }
    }
    
    public OBE convertReg2OBE(Matrix regresiM){
        // convert matrix regresi menjadi OBE untuk keperluan fungsi gaussAndSolutions agar bisa menjadi matrix eselon
        int i,j;
        OBE meselon = new OBE(regresiM.getRowEff(), regresiM.getColEff());
        for (i = 0; i < regresiM.getRowEff(); i++){
            for (j = 0; j < regresiM.getColEff();j++){
                meselon.setMElmt(regresiM.getElmt(i,j), i, j);
            }
        }
        return meselon;
    }

    public void displayFunction(OBE meselon){
        int i,j = meselon.getMatrixCol() - 1;
        System.out.print("f(x) = ");
        int count = 1;
        for (i = 0; i < meselon.getMatrixRow(); i++){
            if (i == 0){
                System.out.print(meselon.getMElmt(i, j));
            } else{
                if (meselon.getMElmt(i, j ) >= 0){
                    System.out.print(" + "+ meselon.getMElmt(i, j)+"x"+count);
                }else{
                    System.out.print(" "+meselon.getMElmt(i, j)+"x"+count);
                }
                count+=1;
            }
        }
    }

    public double taksiran(Matrix listnilaivar, OBE meselon){
        // listnilaivar itu masukan dari pengguna untuk menaksir y, kalo dari file sudah ada tinggal masukan dari keyboard itu harus ditanya dan dimasukin sendiri
        int i,j = meselon.getMatrixCol() - 1;
        Matrix Mnilai = new Matrix(1,meselon.getMatrixRow());
        for (i = 0; i < meselon.getMatrixRow(); i++){
            Mnilai.setElmt(meselon.getMElmt(i, j), 0, i);
        }
        double taksiran = Mnilai.getElmt(0, 0);
        System.out.println("taksiran: "+taksiran);
        int k = 1;
        for (i = 1; i < Mnilai.getColEff(); i++){
            taksiran += Mnilai.getElmt(0, i)*listnilaivar.getElmt(0, i - 1);
        }
        return taksiran;
    }

    public void askDataRegFromFile(String path){
        // 1 2 3 10
        // 4 5 6 20
        // 7 8 9 30
        int i,j;
        try {
            File inputFile = new File(path);
            Scanner readFile = new Scanner(inputFile);
            String line;
            int row = 0;
            boolean varpeubah = false;
            int var = 0;
            while (readFile.hasNextLine() && (line = readFile.nextLine()) != null){
                String[] saved = line.split(" ");
                int len = saved.length;
                if (varpeubah == false){
                    var = saved.length;
                    varpeubah = true;
                }
                if (len == var){
                    for (i = 0; i < len; i++){
                        double tempdouble = Double.parseDouble(saved[i]);
                        dataRegresiM.setElmt(tempdouble, row, i);
                    }
                } else if (len == var - 1){
                    for (i = 0; i < len; i++){
                        double tempdouble = Double.parseDouble(saved[i]);
                        listnilaivar.setElmt(tempdouble, 0, i);
                    }
                    listnilaivar.setColEff(var - 1);
                    listnilaivar.setRowEff(1);
                }
                row++;
                dataRegresiM.setColEff(var);
                dataRegresiM.setRowEff(row - 1);
            }
            readFile.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        // TODO: handle exception
        }
    }
}
