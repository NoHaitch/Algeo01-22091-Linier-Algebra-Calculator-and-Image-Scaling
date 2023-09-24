package operations;

import java.util.Scanner;

public class Matrix {
    private double[][] matrix = new double[1000][1000];
    private int rowEff;
    private int colEff;

    public Matrix(){
        this(0,0);
    }

    public Matrix(int rowEff, int colEff) {
        this.matrix = new double[1000][1000];
        this.rowEff = rowEff;
        this.colEff = colEff;
    }

    public int getRowEff() {
        return this.rowEff;
    }

    public void setRowEff(int newRow) {
        this.rowEff = newRow;
    }

    public int getColEff() {
        return this.colEff;
    }

    public void setColEff(int newCol) {
        this.colEff = newCol;
    }

    public double getElmt(int i, int j) {
        return this.matrix[i][j];
    }

    public void setElmt(double val, int i, int j) {
        this.matrix[i][j] = val;
    }

    public double[] getRow(int i) {
        double[] temp = new double[1000];
        int j;
        for(j = 0; j < this.getColEff(); j++) {
            temp[j] = this.getElmt(i, j);
        }
        return temp;
    }

    public void setRow(double[] newRow, int i) {
        int j;
        for(j = 0; j < this.getColEff(); ++j) {
            this.setElmt(newRow[j], i, j);
        }
    }

    public double[] getCol(int j) {
        double[] temp = new double[1000];
        int i;
        for(i = 0; i < this.rowEff; i++) {
            temp[i] = this.getElmt(i, j);
        }
        return temp;
    }

    public void setCol(double[] newCol, int j) {
        int i;
        for(i = 0; i < this.rowEff; i++) {
            this.setElmt(newCol[i], i, j);
        } 

    }

    public boolean isSquare() {
        return this.getRowEff() == this.getColEff();
    }

    public boolean isIdxValid(int i, int j) {
        return i >= 0 && i < 1000 && j >= 0 && j < 1000;
    }

    public boolean isIdxEff(int i, int j) {
        return i >= 0 && i < this.getRowEff() && j >= 0 && j < this.getColEff();
    }

    public void displayMatrix() {
        int i, j;
        for(i = 0; i < this.getRowEff(); ++i) {
            for(j = 0; j < this.getColEff(); ++j) {
                System.out.printf("%-10s",this.createString(i, j, 7));
                if (j != this.getColEff() - 1) {
                System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public String createString(int i,int j, int length){
        double temp = getElmt(i, j);
        String num = Double.toString(temp);
        if (num.length() > length){
            String finalString = "";
            for (int x = 0; x < length; x++){
                finalString += num.charAt(x);
            }
            return finalString;
        }
        return num;
    }

    public Matrix minorMatrix(int row, int col) {
        Matrix temp = new Matrix(this.getRowEff() - 1, this.getColEff() - 1);
        int ii = 0;
        int jj = 0;
        int i, j;
        for(i = 0; i < this.getRowEff(); ++i) {
            for(j = 0; j < this.getColEff(); ++j) {
                if (i != row && j != col) {
                    temp.setElmt(this.getElmt(i, j), ii, jj);
                    jj++;
                    if (jj == temp.getColEff()) {
                        ii++;
                        jj = 0;
                    }
                }
            }
        }
        return temp;
    }

    public double determinant() {
        // I.S. this.isSquare()
        double temp = 0;
        if (this.getRowEff() == 1) {
            return this.getElmt(0, 0);
        }
        double sign = 1;
        int i;
        for(i = 0; i < this.getRowEff(); i++) {
            temp += sign*this.minorMatrix(0, i).determinant()*this.getElmt(0, i);
            sign *= -1;
        }
        return temp;
    }

    public void copyMatrix(Matrix input){
        int i, j;
        for (i = 0; i < input.getRowEff(); i++){
            for (j = 0; j < input.getColEff(); j++){
                this.setElmt(input.getElmt(i, j), i, j);
            }
        }
        this.setRowEff(input.getRowEff());
        this.setColEff(input.getColEff());
    }

    public void transposeMatrix(){
        // I.S. this.isSquare()
        int i, j;
        for (i = 0; i < getRowEff()-1; i++){
            for (j = i+1; j < getColEff(); j++){
                double temp = getElmt(i, j);
                setElmt(getElmt(j, i), i, j);
                setElmt(temp, j, i);
            }
        }
    }

    public Matrix rTransposeMatrix(){
        Matrix temp = new Matrix(this.getColEff(), this.getRowEff());
        int i, j;
        for (i = 0; i < this.getRowEff(); i++){
            for (j = 0; j < this.getColEff(); j++){
                temp.setElmt(this.getElmt(i, j), j, i);
            }
        }
        return temp;
    }

    public void multiplyMatrixByConst(double k){
        int i, j;
        for (i = 0; i < getRowEff(); i++){
            for (j = 0; j < getColEff(); j++){
                setElmt(getElmt(i, j)*k, i, j);
            }
        }
    }

    public Matrix kofaktorMatrix(){
        // I.S. this.isSquare()
        Matrix temp = new Matrix(this.getRowEff(), this.getColEff());
        int i, j, sign;
        for(i = 0; i < this.getRowEff(); i++){
            for (j = 0; j < this.getColEff(); j++){
                sign = (i+j)%2 == 0 ? 1 : -1;
                temp.setElmt((double)sign*this.minorMatrix(i, j).determinant(),i,j);
            }
        }
        return temp;
    }

    public Matrix inversMatrix(){
        Matrix invers = new Matrix();
        invers.copyMatrix(this.kofaktorMatrix());
        double det = 1/this.determinant();
        invers.multiplyMatrixByConst(det);
        invers.transposeMatrix();
        return invers;
    }

    public Matrix multiplyMatrix(Matrix m){
        // I.S. Matriks yang dikali sesuai ukuranya m1 axb dan m bxc
        Matrix result = new Matrix(this.getRowEff(), m.getColEff());
        int i, j, k;
        double temp;
        for (i = 0; i < this.getRowEff(); i++){
            for (j = 0; j < m.getColEff(); j++){
                temp = 0;
                for (k = 0; k < m.getRowEff(); k++){
                    temp = temp + this.getElmt(i, k)*m.getElmt(k, j);
                }
                result.setElmt(temp, i, j);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Matrix{row: " + rowEff + ", col: " + getColEff() + "}";
    }
    public void readMatrix(int row, int col){
        int i,j;
        double elmt;
        Scanner scanelmt = new Scanner(System.in);
        System.out.println("Masukkan elemen matriks :");
        this.rowEff = row;
        this.colEff = col;
        for (i = 0; i < row; i++){
            for (j = 0; j < col; j++){
                elmt = scanelmt.nextDouble();
                this.matrix[i][j] = elmt;                
            }
            scanelmt.nextLine();
        }
    }

    public void multiplyByConst(int x){
        int i,j;
        for (i = 0; i < this.rowEff; i++){
            for (j = 0; j < this.colEff; j++){
                this.matrix[i][j] *= x;
            }
        }
    }
    
    public int countElmt(){
        int i, j, count = 0;
        for (i = 0; i < this.rowEff; i++){
            for (j = 0; j < this.colEff; j++){
                count += 1;
            }
        }
        return count;
    }
} 
