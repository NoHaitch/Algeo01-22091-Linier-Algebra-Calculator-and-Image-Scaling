package models;
import java.util.Scanner;

import operations.Matrix;
import operations.OBE;
public class regresi {
    public Matrix regresiM;
    public Matrix dataRegresiM;
    private int jumlahPeubahValid;
    private int banyakSampelValid;
    public regresi(){
        this(0,0);
    }
    
    public regresi(int banyakSampel,int jumlahPeubah){
        jumlahPeubahValid = jumlahPeubah;
        banyakSampelValid = banyakSampel;
        regresiM = new Matrix(banyakSampel,jumlahPeubah + 2);
        dataRegresiM = new Matrix(banyakSampel,jumlahPeubah + 1);
    }
    
    public int getjumlahPeubah(){
        return this.jumlahPeubahValid;
    }

    public int getbanyakSampel(){
        return this.banyakSampelValid;
    }
    public void askDatareg(int sampel,int var){
        // sebelumnya sudah dapat ukuran matrix
        int i,j;
        String line;
        Scanner scan = new Scanner(System.in);
        for (i = 0; i < sampel; i++){
            System.out.print("Masukkan sampel (nilai x saja): ");
            line = scan.nextLine();
            String[] saved = line.split(" ");
            int k;
            for (k = 0; k < saved.length; k++){
                double temp = Double.parseDouble(saved[k]);
                dataRegresiM.setElmt(temp, i, k);
            }
        }
        for (i = 0; i < sampel; i++){
            System.out.print("Masukkan sampel hasil (nilai y saja): ");
            double hasil = scan.nextDouble();
            dataRegresiM.setElmt(hasil, i, var);
        }
    }

    public void convertData2Reg(Matrix dataRegresiM){
        int i,j,k;
        double temp;
        for (j = 0; j < this.getjumlahPeubah() + 2; j++){
            if (j == 0){
                regresiM.setElmt(this.getbanyakSampel(),0,0);
            } else {
                temp = 0;
                for (k = 0; k < this.getjumlahPeubah(); k++){
                    temp += dataRegresiM.getElmt(k,j - 1);
                }
                regresiM.setElmt(temp, 0, j);
            }
        }
        // for (j = 0; j < this.getjumlahPeubah(); j++){
        //     for (i = 0; i < this.getbanyakSampel(); i++){
        //         if (i == 0 && j == 0){
        //             regresiM.setElmt(getbanyakSampel(), i, j);
        //         } else if (i == 0){
        //             temp = 0;
        //             for (k = 0; k < this.getbanyakSampel(); k++){
        //                 temp += dataRegresiM.getElmt(k,j);
        //                 System.out.println("data berhasil di set " + dataRegresiM.getElmt(k, j));
        //             }
        //             System.out.println("Masukan: " + temp);
        //             regresiM.setElmt(temp, i, j);
        //         }
        //     }
        // }
    }
}
