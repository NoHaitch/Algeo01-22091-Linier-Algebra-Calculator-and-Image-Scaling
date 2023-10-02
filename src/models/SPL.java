package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import operations.Matrix;
import operations.OBE;

/* Class SPL */
/* Membuat objek SPL, untuk permasalahan Sistem Persamaan Linier */
public class SPL {

    /* ---------- GLOBAL VARIABLES ---------- */
    public OBE spl;
    public Matrix listnilaivar;
    // public Matrix listnilaivar;
    /* ---------- KONSTRUKTOR ---------- */
    public SPL(){
        this(200,150);
    }

    /* Konstruktor overloading */
    public SPL(int row, int col){
        this.spl = new OBE(row, col);
}

    /* ---------- KELOMPOK Interaksi dengan IO ---------- */
    public void inputSPLText(){
        /* Membaca file txt untuk dilakukan operasi SPL */
        String path;
        Scanner input = new Scanner (System.in);
        System.out.print("Masukkan source file: ");
        path = input.nextLine();
        try {
            File inputFile = new File(path);
            Scanner readFile = new Scanner(inputFile);
            String line;
            int row = 0;
            int column = 0;
            while (readFile.hasNextLine() && (line = readFile.nextLine()) != null){
                String[] saved = line.split(" ");
                if (row == 0){
                    column = saved.length;
                }
                int i;
                for (i = 0; i < column; i++){
                    double temp = Double.parseDouble(saved[i]);
                    spl.setMElmt(temp, row, i);
                }
                row++;
            }
            spl.setMatrixRow(row);
            spl.setMatrixCol(column);
            readFile.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            // TODO: handle exception
        }
        //input.close();
    }

    public void saveToTextFile(String path){
        /* Menyimpan hasil Operasi Ke dalam file text */
        String fPath = "";
        int i = 0;
        while (path.charAt(i) != '.'){
            fPath += path.charAt(i);
            i++;
        }
        String add = "";
        int idx = 0;
        File testFile = new File(fPath+".txt");
        while (testFile.exists()){
            idx ++;
            add = "("+idx+")";
            testFile = new File(fPath+add+".txt");
        }
        fPath += add + ".txt";
        try {
            FileWriter writer = new FileWriter(fPath);
            writer.write(this.spl.getStep());
            writer.close();
            System.out.println("\nPenyelesaian berhasil disimpan ke :"+fPath+"\n\n");
        } catch (Exception e) {
            System.out.println("An error occurred\n");
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    /* ---------- SELEKTOR ---------- */
    public OBE getSPL(){
        return new OBE(this.spl);
    }

    public void solveWithCramer(OBE mdata){
        // menerima input matrix yang berisikan data SPL
        double temp;
        int i,count = 0;
        Matrix original = new Matrix (mdata.getCopyAugmented());
        original.setColEff(3);
        Matrix dupe = new Matrix(original);
        listnilaivar = new Matrix(1,dupe.getColEff());
        if (original.isSquare()){
            if (original.determinant() != 0){
                double detcramer = original.determinant();
                for (i = 0; i < dupe.getColEff(); i++){
                    dupe.setCol(mdata.getAllCol(original.getColEff()), i);
                    temp = dupe.determinant()/detcramer;
                    listnilaivar.setElmt(temp, 0, i);
                    dupe = new Matrix(original);
                }

            } else{
                System.out.println("Tidak bisa melakukan kaidah cramer karena determinant Matrix bernilai 0.");
            }
        } else{
            System.out.println("Tidak bisa melakukan kaidah cramer karena Matrix tidak berbentuk persegi.");
        }
    }
}
