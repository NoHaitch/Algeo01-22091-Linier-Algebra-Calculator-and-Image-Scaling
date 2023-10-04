package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import operations.Matrix;
import operations.OBE;

public class DeterminanInvers {
    //public static Matrix xInvers;
    public OBE contents;
    public boolean isDetZero = false;
    public int sign = 1;
    public double[] multiply  = new double[1000];
    public int countMul = 0;
    public double result;

    public DeterminanInvers(){
        this(200,150);
    }

    public DeterminanInvers(int row, int col){
        contents = new OBE(row, col);
    }

    public DeterminanInvers(DeterminanInvers det){
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

    public void inputMatriksFile(String path){
        if (contents.getMatrixRow() == 0 || contents.getMatrixCol() == 0){
            contents = new OBE(1000, 1000);
        }
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
    }

    public void saveToTextFile(String path, String text){
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
            writer.write(text + "\n");
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

    public void refreshIMain(int start){
        for (int i = start; i < contents.getMatrixRow(); i++){
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
        return false;
    }

    public boolean zeroDeterminan(){
        DeterminanInvers temp = new DeterminanInvers(this);
        temp.CalculateOBE();
        return temp.isDetZero;
    }

    public boolean isLanjutForInvers(){
        for (int i = 0; i < contents.getMatrixRow()-1; i++){
            int pass = contents.getIndexMain(i);
            if (pass == contents.getMatrixCol()/2){
                isDetZero = true;
                return false;
            }
            for (int j = i+1; j < contents.getMatrixRow()/2; j++){
                if (pass == contents.getIndexMain(j)){
                    return true;
                }
            }
        }
        return false;
    }

    public void makeOneRow(int row){
        int iMain = findIMain(row);
        if (iMain < contents.getMatrixCol()){
            double val = contents.getMElmt(row, iMain);
            if (val != 1){
                contents.addMkOnetoStep(row, val);
                multiply[countMul] = 1/val;
                countMul ++;
                for (int i = iMain; i < contents.getMatrixCol(); i++){
                    double temp = contents.getMElmt(row, i)/val;
                    if (temp == -0.0){
                        temp = 0.0;
                    }
                    contents.setMElmt(temp, row, i);
                }
            }
        }
    }

    public void makeOneAny(int start){
        for (int i = start; i < contents.getMatrixRow(); i++){
            int iMain = findIMain(i);
            if (iMain < contents.getMatrixCol()){
                double val = contents.getMElmt(i, iMain);
                if (val != 1){
                    contents.addMkOnetoStep(i, val);
                    multiply[countMul] = 1/val;
                    countMul++;
                    for (int j = iMain; j < contents.getMatrixCol(); j++){
                        double temp = contents.getMElmt(i, j)/val;
                        if (temp == -0.0){
                            temp = 0.0;
                        }
                        contents.setMElmt(temp, i, j);
                    }
                }
            }
        }
    }

    public void CalculateOBE(){
        contents.addTitleStep();
        contents.addAugmentedToStep(7);
        boolean swapped = false;
        refreshIMain(0);
        sortIMain(0, swapped);
        makeOneRow(0);
        contents.addAugmentedToStep(7);
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
            refreshIMain(pass);
            sortIMain(pass, swapped);
            makeOneAny(pass);
            contents.addAugmentedToStep(7);
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
            result = temp*sign;
        }
        contents.addStringToStep("\nDeterminan = "+result+"\n");

    }
    
    public double determinanKofaktor(){
        return contents.determinant();
    }

    public void addIndentity(){
        int length = contents.getMatrixCol()*2;
        for (int i = 0; i < contents.getMatrixRow(); i++){
            for (int j = contents.getMatrixCol(); j < length; j++){
                if (i == j - contents.getMatrixCol()){
                    contents.setMElmt(1, i, j);
                }
            }
        }
        contents.setMatrixCol(length);
    }

    public void substractJrdn(int row1, int row2){
        int idx = findIMain(row2);
        double left = contents.getMElmt(row1, idx);
        double right = contents.getMElmt(row2, idx);
        //System.out.println(left+" ---- "+right);
        if (left == right){
            //System.out.println("Sama");
            for (int i = idx; i < contents.getMatrixCol(); i++){
                double temp = contents.getMElmt(row1, i)-contents.getMElmt(row2, i);
                if (temp == -0.0){
                    temp = 0.0;
                }
                contents.setMElmt(temp, row1, i);
            }
        } else if (left%right == 0){
            //System.out.println("Case 2");
            double mul = left/right;
            for (int i = idx; i < contents.getMatrixCol(); i++){
                double temp = contents.getMElmt(row1, i)-contents.getMElmt(row2, i)*mul;
                if (temp == -0.0){
                    temp = 0.0;
                }
                contents.setMElmt(temp, row1, i);
            }
        } else {
            //System.out.println("Case 3");
            for (int i = idx; i < contents.getMatrixCol(); i++){
                double temp = contents.getMElmt(row1, i)-contents.getMElmt(row2, i)*left;
                if (temp == -0.0){
                    temp = 0.0;
                }
                contents.setMElmt(temp, row1, i);
            }
        }

    }

    public Matrix inversMatrix(){
        //I.S. Matriks mempunyai invers : determinan != 0;
        Matrix temp = new Matrix(contents.getMatrixRow(),contents.getMatrixRow());
        DeterminanInvers oper = new DeterminanInvers(this);
        oper.addIndentity();
        boolean swapped = false;
        oper.refreshIMain(0);
        oper.sortIMain(0, swapped);
        oper.makeOneRow(0);
        swapped = false;
        int pass = 1;
        boolean lanjut = oper.isLanjutForInvers();
        while (lanjut && !oper.isDetZero && pass < oper.contents.getMatrixRow()){
            for (int i = pass; i < oper.contents.getMatrixRow(); i++){
                if (oper.contents.getIndexMain(i) == oper.contents.getIndexMain(pass-1)){
                    oper.contents.substractRow(i, pass-1);
                }
            }
            oper.refreshIMain(pass);
            oper.sortIMain(pass, swapped);
            oper.makeOneAny(pass);
            pass++;
            lanjut = isLanjutForInvers();
        }
        if (!oper.isDetZero){
            for (int i = oper.contents.getMatrixRow()-2; i >= 0; i--){
                for(int j = oper.contents.getMatrixRow()-1; j > i; j--){
                    oper.substractJrdn(i, j);
                }
            }
            for (int i = 0; i < oper.contents.getMatrixRow(); i++){
                for (int j = 0; j < oper.contents.getMatrixRow(); j++){
                    temp.setElmt(oper.contents.getMElmt(i, j+oper.contents.getMatrixRow()), i, j);
                }
            }
            //oper.contents.printAugmented();
        }
        return temp;
    }
}