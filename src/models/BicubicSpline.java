package models;

import operations.OBE;
import operations.Matrix;

public class BicubicSpline {
    OBE contents = new OBE();
    public static Matrix invX;

    public BicubicSpline(){
    }

    public BicubicSpline(int row, int col){
        contents = new OBE(row, col);
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
}
