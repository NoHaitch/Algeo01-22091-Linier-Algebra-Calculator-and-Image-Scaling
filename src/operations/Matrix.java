// Source code is decompiled from a .class file using FernFlower decompiler.
package operations;

public class Matrix {
    private double[][] matrix = new double[1000][1000];
    private int rowEff;
    private int colEff;

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

    public double getElement(int i, int j) {
        return this.matrix[i][j];
    }

    public void setElement(double val, int i, int j) {
        this.matrix[i][j] = val;
    }

    public double[] getRow(int i) {
        double[] temp = new double[1000];
        int j;
        for(j = 0; j < this.getColEff(); j++) {
            temp[j] = this.getElement(i, j);
        }
        return temp;
    }

    public void setRow(double[] newRow, int i) {
        int j;
        for(j = 0; j < this.getColEff(); ++j) {
            this.setElement(newRow[j], i, j);
        }
    }

    public double[] getCol(int j) {
        double[] temp = new double[1000];
        int i;
        for(i = 0; i < this.rowEff; i++) {
            temp[i] = this.getElement(i, j);
        }
        return temp;
    }

    public void setCol(double[] newCol, int j) {
        int i;
        for(i = 0; i < this.rowEff; i++) {
            this.setElement(newCol[i], i, j);
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
                System.out.print(this.getElement(i, j));
                if (j != this.getColEff() - 1) {
                System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public Matrix minorMatrix(int row, int col) {
        Matrix temp = new Matrix(this.getRowEff() - 1, this.getColEff() - 1);
        int ii = 0;
        int jj = 0;
        int i, j;
        for(i = 0; i < this.getRowEff(); ++i) {
            for(j = 0; j < this.getColEff(); ++j) {
                if (i != row && j != col) {
                    temp.setElement(this.getElement(i, j), ii, jj);
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
            return this.getElement(0, 0);
        }
        double sign = 1;
        int i;
        for(i = 0; i < this.getRowEff(); i++) {
            temp += sign*this.minorMatrix(0, i).determinant()*this.getElement(0, i);
            sign *= -1;
        }
        return temp;
    }

    public void transposeMatrix(){
        // I.S. this.isSquare()
        
    }

    public String toString() {
        return "Matrix{row: " + rowEff + ", col: " + getColEff() + "}";
    }
} 
