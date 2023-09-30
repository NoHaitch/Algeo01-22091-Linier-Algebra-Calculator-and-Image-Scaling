package operations;

import java.util.Scanner;

/* Class Matrix */
/* Membuat objek matrix, serta berisi fungsi-fungsi operasi matriks */
/* Seperti inverse, transpose serta determinan kofaktor */

public class Matrix {
    private double[][] matrix;
    private int rowEff;
    private int colEff;

    /* ---------- KONSTRUKTOR ---------- */
    public Matrix(int rowEff, int colEff) {
        this.matrix = new double[rowEff][colEff*2]; 
        this.rowEff = rowEff;
        this.colEff = colEff;
    }

    /* Konstruktor overloading */
    public Matrix(){
        /* Kasus Matriks kosong */
        this(0,0);
    }

    public Matrix(Matrix matrix){
        this(matrix.getRowEff(),matrix.getColEff());
        this.copyMatrix(matrix);
    }

    /* ---------- KELOMPOK Interaksi dengan IO ---------- */

    @Override
    public String toString() {
        /* Melakukan Override fungsi untuk mempermudah penunjukan hasil */
        return "Matrix{row: " + rowEff + ", col: " + getColEff() + "}";
    }

    public void readMatrix(int row, int col){
        /* I.S. Matriks terdifinisi dan kosong */
        /* F.S. Matriks bernilai*/
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
        scanelmt.close();
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

    /* ---------- SELEKTOR ---------- */
    public int getRowEff() {
        /* Mengembalikan jumlah baris efektif */
        return this.rowEff;
    }

    public void setRowEff(int newRow) {
        /* Mengubah jumlah baris efektif */
        this.rowEff = newRow;
    }

    public int getColEff() {
        /* Mengembalikan jumlah kolom efektif */
        return this.colEff;
    }

    public void setColEff(int newCol) {
        /* Mengubah jumlah kolom efektif */
        this.colEff = newCol;
    }

    public double getElmt(int i, int j) {
        /* Mengembalikan nilai element pada baris i+1 dan kolom j+1 */
        return this.matrix[i][j];
    }

    public void setElmt(double val, int i, int j) {
        /* Mengubah nilai element pada baris i+1 dan kolom j+1 */
        this.matrix[i][j] = val;
    }

    public double[] getRow(int i) {
        /* Mengembalikan isi satu baris matrix */
        double[] temp = new double[1000];
        int j;
        for(j = 0; j < this.getColEff(); j++) {
            temp[j] = this.getElmt(i, j);
        }
        return temp;
    }

    public void setRow(double[] newRow, int i) {
        /* Mengubah isi satu baris matrix */
        int j;
        for(j = 0; j < this.getColEff(); ++j) {
            this.setElmt(newRow[j], i, j);
        }
    }

    public double[] getCol(int j) {
        /* Mengembalikan isi satu kolom matrix */
        double[] temp = new double[1000];
        int i;
        for(i = 0; i < this.rowEff; i++) {
            temp[i] = this.getElmt(i, j);
        }
        return temp;
    }

    public void setCol(double[] newCol, int j) {
        /* Mmengubah isi satu kolom matrix */
        int i;
        for(i = 0; i < this.rowEff; i++) {
            this.setElmt(newCol[i], i, j);
        }
    }

    /* ---------- KELOMPOK TEST ---------- */
    public boolean isSquare() {
        /* Mengetest matriks adalah matriks persegi */
        return this.getRowEff() == this.getColEff();
    }

    public boolean isIdxValid(int i, int j) {
        /* Mengetest idx matrix adalah valid */
        return i >= 0 && i < 1000 && j >= 0 && j < 1000;
    }

    public boolean isIdxEff(int i, int j) {
        /* Mengetest idx matriks adalah idx effektif */
        return i >= 0 && i < this.getRowEff() && j >= 0 && j < this.getColEff();
    }

    /* ---------- KELOMPOK Fungsi Utama ---------- */
    public int countElmt(){
        /* Mengembalikan jumlah elemen efektif */
        int i, j, count = 0;
        for (i = 0; i < this.rowEff; i++){
            for (j = 0; j < this.colEff; j++){
                count += 1;
            }
        }
        return count;
    }

    public String createString(int i,int j, int length){
        /* Mengubah angka berlebih menjadi angka dalam bentuk string yang lebih pendek */
        /* Mirip dengan formatting */
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
        /* Mengembalikan Matrix minor */
        /* Matrix minor adalah matrix persegit yang dihilangkan satu baris dan kolom */
        /* Operasi ini digunakan unutk mencari kofaktor */
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
        // Prekondisi : this.isSquare()
        // Mengembalikan nilai determinan, menggunakan metode kofaktor
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
        /* I.S. Matrix terdefinisi */
        /* F.S. Matrix input berisi salinan isi Matrix */
        int i, j;
        for (i = 0; i < input.getRowEff(); i++){
            for (j = 0; j < input.getColEff(); j++){
                this.setElmt(input.getElmt(i, j), i, j);
            }
        }
        this.rowEff = input.getRowEff();
        this.colEff = input.getColEff();
    }

    public void transposeMatrix(){
        /* I.S. this.isSquare() */
        /* F.S. Matriks berhasil ditranspose */
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
        /* Prekondisi : this.isSquare() */
        /* Mengembalikan Matriks hasil transpose */
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
        /* I.S. Matriks terdefinisi */
        /* F.S. Setiap elemen matriks dikalikan dengan k */
        int i, j;
        for (i = 0; i < getRowEff(); i++){
            for (j = 0; j < getColEff(); j++){
                double temp = getElmt(i, j)*k;
                if (temp == -0.0){
                    temp = 0.0;
                }
                setElmt(temp, i, j);
            }
        }
    }

    public Matrix kofaktorMatrix(){
        /* Prekondisi : this.isSquare() */
        /* Mengembalikan Matriks kofaktor */
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
        /* Mengembalikan Matriks inverse */
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
                if (temp == -0.0){
                    temp = 0.0;
                }
                result.setElmt(temp, i, j);
            }
        }
        return result;
    }

    // udah ada multiplyMatrixByConst di atas kiw bisa diganti
    public void multiplyByConst(int x){
        /* I.S. */
        int i,j;
        for (i = 0; i < this.rowEff; i++){
            for (j = 0; j < this.colEff; j++){
                this.matrix[i][j] *= x;
            }
        }
    }
}