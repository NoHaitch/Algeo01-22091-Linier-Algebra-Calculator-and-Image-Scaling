package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import operations.Matrix;

public class BicubicSpline {
    public Matrix initF = new Matrix(16, 1);
    public static Matrix invX;
    public double[] solveA = new double[16];
    public String function = "";
    public double[] request = new double[2];

    public BicubicSpline(){
    }

    public static void funcF(double x, double y){
        int row;
        if (x == 0 && y == 0){
            row = 0;
        } else if (x == 1 && y == 0){
            row = 1;
        } else if (x == 0 && y == 1){
            row = 2;
        } else {
            row = 3;
        }
        int idx = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                invX.setElmt(Math.pow(x,j)*Math.pow(y,i), row, idx);
                idx++;
            }
        }
    }

    public static void funcFx(double x, double y){
        int row;
        if (x == 0 && y == 0){
            row = 0;
        } else if (x == 1 && y == 0){
            row = 1;
        } else if (x == 0 && y == 1){
            row = 2;
        } else {
            row = 3;
        }
        row += 4;
        int idx = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                double temp = (double) j -1;
                if (temp < 0){
                    temp = 0;
                }
                invX.setElmt(j*Math.pow(x,temp)*Math.pow(y,i), row, idx);
                idx++;
            }
        }
    }

    public static void funcFy(double x, double y){
        int row;
        if (x == 0 && y == 0){
            row = 0;
        } else if (x == 1 && y == 0){
            row = 1;
        } else if (x == 0 && y == 1){
            row = 2;
        } else {
            row = 3;
        }
        row += 8;
        int idx = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                double temp = (double)i-1;
                if (temp < 0){
                    temp = 0;
                }
                invX.setElmt(Math.pow(x,j)*Math.pow(y,temp)*i, row, idx);
                idx++;
            }
        }
    }

    public static void funcFxy(double x, double y){
        int row;
        if (x == 0 && y == 0){
            row = 0;
        } else if (x == 1 && y == 0){
            row = 1;
        } else if (x == 0 && y == 1){
            row = 2;
        } else {
            row = 3;
        }
        row += 12;
        int idx = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                double ltmp = (double) j - 1;
                double rtmp = (double) i - 1;
                if (ltmp < 0){
                    ltmp = 0;
                }
                if (rtmp < 0){
                    rtmp = 0;
                }
                invX.setElmt(Math.pow(x,ltmp)*Math.pow(y,rtmp)*i*j, row, idx);
                idx++;
            }
        }
    }
    
    public static void setStaticInvX(){
        invX = new Matrix(16, 16);
        DeterminanInvers temp = new DeterminanInvers(16, 16);
        double [][] var = new double[][]{{0,0},{1,0},{0,1},{1,1}};
        for (double[] sect : var){
            funcF(sect[0], sect[1]);
            funcFx(sect[0], sect[1]);
            funcFy(sect[0], sect[1]);
            funcFxy(sect[0], sect[1]);
        }
        temp.contents.setAugmented(invX);
        invX.copyMatrix(temp.inversMatrix());
    } 

    public void inputInitFromText(String path){
        try {
            File inputFile = new File(path);
            Scanner readFile = new Scanner(inputFile);
            String line;
            int idx = 0;
            while (readFile.hasNextLine() && (line = readFile.nextLine()) != null){
                String[] saved = line.split(" ");
                int len = saved.length;
                if (len == 4){
                    for (int i = 0; i < len; i++){
                        double temp = Double.parseDouble(saved[i]);
                        initF.setElmt(temp, idx, 0);
                        idx ++;
                    }
                } else {
                    request[0] = Double.parseDouble(saved[0]);
                    request[1] = Double.parseDouble(saved[1]);
                }
            }
            readFile.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    public void solveBicubic(){
        Matrix temp = new Matrix(16, 1);
        if (invX == null){
            setStaticInvX();
        }
        temp.copyMatrix(invX.multiplyMatrix(initF));
        //temp.displayMatrix();
        for (int i = 0; i < 16; i++){
            solveA[i] = temp.getElmt(i, 0);
        }
        addFunctionText();
    }

    public double getFValueOf(double x, double y){
        //I.S. 0 <= x <= 1 && 0 <= y <= 1
        double value = 0;
        int idx = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                value += solveA[idx]*Math.pow(x,j)*Math.pow(y,i);
                idx ++;
            }
        }
        String temp = "\nf("+x+","+y+") = "+value+"\n";
        function += temp;
        return value;
    }

    public double getRequestAnswer(){
        double value = 0;
        int idx = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                value += solveA[idx]*Math.pow(request[0],j)*Math.pow(request[1],i);
                idx ++;
            }
        }
        String temp = "\nf("+request[0]+","+request[1]+") = "+value+"\n";
        function += temp;
        return value;
    }

    public void addFunctionText(){
        String temp = "";
        int idx = 0;
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                temp += "a" + j+""+i+" = "+solveA[idx]+"\n";
                idx++;
            }
        }
        function += temp;
    }

}