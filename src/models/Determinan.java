package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import operations.OBE;

public class Determinan {
    public OBE contents;
    public boolean isDetZero = false;
    public int sign = 1;
    public double[] multiply  = new double[1000];
    public int countMul = 0;
    public double result;

    public Determinan(){
        this(0,0);
    }

    public Determinan(int row, int col){
        contents = new OBE(row, col);
    }

    public Determinan(Determinan det){
        this.contents = new OBE(det.contents);
        this.isDetZero = det.isDetZero;
        this.sign = det.sign;
        this.countMul = det.countMul;
        this.result = det.result;
        this.multiply = new double[1000];
        for (int i = 0; i < this.countMul; i++){
            this.multiply[i] = det.multiply[i];
        }
    }

    public void inputMatriksText(){
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
                    contents.setMElmt(temp, row, i);
                }
                row++;
            }
            contents.setMatrixRow(row);
            contents.setMatrixCol(column);
            readFile.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            // TODO: handle exception
        }
        input.close();
    }

    public void saveToTextFile(String path){
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
            writer.write(this.contents.getStep());
            writer.close();
            System.out.println("\nPenyelesaian berhasil disimpan ke :"+fPath+"\n\n");
        } catch (Exception e) {
            System.out.println("An error occurred\n");
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    public int findIMain(int row){
        for (int i = 0; i < contents.getMatrixCol(); i++){
            if(contents.getMElmt(row, i) != 0){
                contents.setIndexMain(i, row);
                return i;
            }
        }
        isDetZero = true;
        contents.setIndexMain(contents.getMatrixCol(), row);
        return contents.getMatrixCol();
    }

    public void refreshIMain(){
        for (int i = 0; i < contents.getMatrixRow(); i++){
            findIMain(i);
        }
    }

    public void swapforDet(int row1, int row2){
        double temp;
        for (int i = 0; i < contents.getMatrixCol(); i++){
            temp = contents.getMElmt(row1, i);
            contents.setMElmt(contents.getMElmt(row2, i), row1, i);
            contents.setMElmt(temp, row2, i);
        }
        int tempIMain = contents.getIndexMain(row1);
        contents.setIndexMain(contents.getIndexMain(row2), row1);
        contents.setIndexMain(tempIMain, row2);
        contents.addSwaptoStep(row1, row2);
    }

    public void sortIMain(int start, boolean wasSwapped){
        for (int i = start; i < contents.getMatrixRow()-1; i++){
            int temp = contents.getIndexMain(i);
            int idx = i;
            for (int j = i+1; j < contents.getMatrixRow(); j++){
                if (temp > contents.getIndexMain(j)){
                    temp = contents.getIndexMain(j);
                    idx = j;
                }
            }
            if (idx != i){
                wasSwapped = true;
                swapforDet(idx, i);
                sign *= -1;
            }
        }
        if (wasSwapped){
            contents.addAugmentedToStep(7);
        }
    }

    public boolean isLanjut(){
        for (int i = 0; i < contents.getMatrixRow()-1; i++){
            int pass = contents.getIndexMain(i);
            if (pass == contents.getMatrixCol()){
                isDetZero = true;
                return false;
            }
            for (int j = i+1; j < contents.getMatrixRow(); j++){
                if (pass == contents.getIndexMain(j)){
                    return true;
                }
            }
        }
        return true;
    }

    public void CalculateOBE(){
        contents.addTitleStep();
        contents.addAugmentedToStep(7);
        boolean swapped = false;
        contents.refreshIdxMain(0);
        contents.sortIdxMain(0, swapped);
        swapped = false;
        int pass = 1;
        boolean lanjut = isLanjut();
        while (lanjut && !isDetZero && pass < contents.getMatrixRow()){
            for (int i = pass; i < contents.getMatrixRow(); i++){
                if (contents.getIndexMain(i) == contents.getIndexMain(pass-1)){
                    double left = contents.getMElmt(pass-1, contents.getIndexMain(pass-1));
                    double right = contents.getMElmt(i, contents.getIndexMain(pass-1));
                    if (left != 1 && left != right && right%left != 0){
                        multiply[countMul] = left;
                        countMul++;
                    }
                    contents.substractRow(i, pass-1);
                }
            }
            contents.addAugmentedToStep(7);
            refreshIMain();
            sortIMain(pass, swapped);
            pass++;
            lanjut = isLanjut();
        }
        if (isDetZero){
            result = 0;
        } else {
            double temp = 1;
            for (int i = 0; i < contents.getMatrixRow(); i++){
                temp *= contents.getMElmt(i, i);
            }
            for (int i = 0; i < countMul; i++){
                temp /= multiply[i];
            }
            result = temp;
        }
        contents.addStringToStep("\nDeterminan = "+result+"\n");

    }
    
    public double determinanKofaktor(){
        return contents.determinant();
    }
}