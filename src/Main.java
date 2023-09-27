import models.DeterminanInvers;
import operations.Matrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void println(String str){
        System.out.println(str);
    }
    public static void print(String str){
        System.out.print(str);
    }

    public static void main(String[] args) {
        println("\n***** ==================== APLIKASI MULAI ==================== *****");
        println("==== Selamat Datang pada Aplikasi Perhitungan Matriks ====");
        Scanner scanner = new Scanner(System.in);
        boolean program = true;
        while(program) {
            println("");
            println("   =============== MENU UTAMA =============== ");
            println("1. Sistem Persamaaan Linier");
            println("2. Determinan");
            println("3. Matriks balikan");
            println("4. Interpolasi Polinom");
            println("5. Interpolasi Bicubic Spline");
            println("6. Regresi linier berganda");
            println("7. Keluar");
            print(" >>> Pilih menu : ");
            try {
                int opsiMenuUtama = Integer.parseInt(scanner.nextLine());
                switch (opsiMenuUtama){
                    case 1: break;
                    case 2:
                        boolean isDeterminanMenu = true;
                        while(isDeterminanMenu){
                            println("\n   ========== Pilih Metode Determinan ========== ");
                            println("1. Metode Ekspansi Kofaktor");
                            println("2. Metode Reduksi Baris dengan OBE");
                            println("3. Kembali");
                            print(" >>> Pilih Metode Determinan : ");
                            try{
                                int opsiMenuDeterminan = Integer.parseInt(scanner.nextLine());
                                boolean isInputMenu = true;
                                switch (opsiMenuDeterminan){
                                    case 1:
                                        while(isInputMenu) {
                                            println("\n   ===== Pilih Metode Masukkan =====");
                                            println("1. Masukkan Ketik");
                                            println("2. Masukkan dalam bentuk File");
                                            println("3. Kembali");
                                            print(" >>> Pilih Metode Masukkan : ");
                                            try {
                                                int opsiMenuInput = Integer.parseInt(scanner.nextLine());
                                                switch (opsiMenuInput) {
                                                    case 1:
                                                        boolean isInputUkuranValid = true;
                                                        while(isInputUkuranValid) {
                                                            println("\n ===== Metode Ketik ===== ");
                                                            println("Ketik -1 untuk kembali ");
                                                            try {
                                                                print(" >>> Masukkan Jumlah Baris : ");
                                                                int row = Integer.parseInt(scanner.nextLine());
                                                                if(row == -1){
                                                                    isInputUkuranValid = false ;
                                                                }
                                                                else if(row == 0){
                                                                    println("Ukuran Matriks tidak bisa 0");
                                                                }
                                                                else{
                                                                    print(" >>> Masukkan Jumlah Kolom : ");
                                                                    int col = Integer.parseInt(scanner.nextLine());
                                                                    if(col == -1){
                                                                        isInputUkuranValid = false ;
                                                                    }
                                                                    else if(col == 0){
                                                                        println("Ukuran Matriks tidak bisa 0");
                                                                    }
                                                                    else if(row == col){
                                                                        DeterminanInvers determinan = new DeterminanInvers();
                                                                        Matrix matrix = new Matrix();
                                                                        determinan.contents.setMatrixRow(row);
                                                                        determinan.contents.setMatrixCol(col);
                                                                        /* Baca Isi Matrix */
                                                                        boolean isInputMatrixValid = true;
                                                                        int i = 0;
                                                                        while(i < row && isInputMatrixValid){
                                                                            int j = 0;
                                                                            while(j < col && isInputMatrixValid) {
                                                                                try {
                                                                                    double elmt = scanner.nextDouble();
                                                                                    determinan.contents.setMElmt(elmt,i,j);
                                                                                    println("" + determinan.contents.getMElmt(i,j));
                                                                                } catch (Exception InputMismatchException) {
                                                                                    println("Isi Matrix salah. Pastikan isi matriks adalah angka desimal");
                                                                                    isInputMatrixValid = false;
                                                                                }
                                                                                j++;
                                                                            }
                                                                            i++;
                                                                        }
                                                                        scanner.nextLine();
                                                                        if(isInputMatrixValid) {
                                                                            println(" ================== HASIL ================== ");
                                                                            println("   Determinan = " + determinan.determinanKofaktor());
                                                                            isInputUkuranValid = false;
                                                                            isInputMenu = false;
                                                                            isDeterminanMenu = false;
                                                                        }
                                                                    }
                                                                    else{
                                                                        println("Ukuran Matriks salah. Matriks tidak bukan matriks persegi (baris = kolom)");
                                                                    }
                                                                }
                                                            } catch (Exception InputMismatchException){
                                                                println("Ukuran Matrix salah. Pastikan ukuran adalah bilangan bulat");
                                                            }
                                                        }
                                                        break;
                                                    case 2:
                                                        DeterminanInvers determinan = new DeterminanInvers();
                                                        boolean inputValid = false;
                                                        while(!inputValid) {
                                                            println("Ketik 0 untuk kembali");
                                                            print(" >>> Masukkan alamat file: ");
                                                            String path = scanner.nextLine();
                                                            if(Objects.equals(path, "0")) {
                                                                inputValid = true;
                                                            }
                                                            else {
                                                                try {
                                                                    File inputFile = new File(path);
                                                                    Scanner readFile = new Scanner(inputFile);
                                                                    String line;
                                                                    int row = 0;
                                                                    int column = 0;
                                                                    while (readFile.hasNextLine() && (line = readFile.nextLine()) != null) {
                                                                        String[] saved = line.split(" ");
                                                                        if (row == 0) {
                                                                            column = saved.length;
                                                                        }
                                                                        int i;
                                                                        for (i = 0; i < column; i++) {
                                                                            double temp = Double.parseDouble(saved[i]);
                                                                            determinan.contents.setMElmt(temp, row, i);
                                                                        }
                                                                        row++;
                                                                    }
                                                                    determinan.contents.setMatrixRow(row);
                                                                    determinan.contents.setMatrixCol(column);
                                                                    println(" ================== HASIL ================== ");
                                                                    println("   Determinan =   " + determinan.determinanKofaktor());
                                                                    inputValid = true;
                                                                    isInputMenu = false;
                                                                    isDeterminanMenu = false;
                                                                    readFile.close();
                                                                } catch (FileNotFoundException e) {
                                                                    println("Alamat file salah. Contoh alamat benar : src/input.txt ");
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    case 3:
                                                        isInputMenu = false;
                                                        break;
                                                    default:
                                                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                                }
                                            } catch (Exception InputMismatchException) {
                                                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                            }
                                        }
                                        break;
                                    case 2:
                                        while(isInputMenu) {
                                            println("\n   ===== Pilih Metode Masukkan =====");
                                            println("1. Masukkan Ketik");
                                            println("2. Masukkan dalam bentuk File");
                                            println("3. Kembali");
                                            print(" >>> Pilih Metode Masukkan : ");
                                            try {
                                                int opsiMenuInput = Integer.parseInt(scanner.nextLine());
                                                switch (opsiMenuInput) {
                                                    case 1:

                                                        break;
                                                    case 2:

                                                        break;
                                                    case 3:
                                                        isInputMenu = false;
                                                        break;
                                                    default:
                                                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                                }
                                            } catch (Exception InputMismatchException) {
                                                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                            }
                                        }
                                        break;
                                    case 3:
                                        isDeterminanMenu = false;
                                        break;
                                    default:
                                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                }
                            } catch (Exception InputMismatchException){
                                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                            }
                        }
                        break;
                    case 3: break;
                    case 4: break;
                    case 5: break;
                    case 6: break;
                    case 7:
                        program = false;
                        println("\n***** =============== APLIKASI BERHENTI =============== *****\n");
                        break;
                    default:
                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 7");
                }
            }
            catch (Exception InputMismatchException){
                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 7");
            }
        }
        scanner.close();
    }
}

/*
2 3 -1 5
4 4 -3 3
-2 3 -1 1

1 3 -2 0 2 0 0
2 6 -5 -2 4 -3 -1
0 0 5 10 0 15 5
2 6 0 8 4 18 6

0 0 0 1
1 2 3 4
4 5 9 1
*/
