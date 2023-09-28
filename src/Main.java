import models.DeterminanInvers;
import models.SPL;
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
            /* ============================== MENU UTAMA ============================== */
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
                    case 1:
                        /* =========== MENU SPL =========== */
                        boolean isSPLMenu = true;
                        while(isSPLMenu){
                            println("\n   ========== Pilih Metode Penyelesaian SPL ========== ");
                            println("1. Metode Eliminasi Gauss");
                            println("2. Metode Eliminasi Gauss-Jordan");
                            println("3. Metode Matriks Balikan ");
                            println("4. Kaidah Cramer");
                            println("5. Kembali");
                            print(" >>> Pilih Metode : ");
                            try{
                                int opsiMenuSPL = Integer.parseInt(scanner.nextLine());
                                boolean isInputMenu = true;
                                switch (opsiMenuSPL){
                                    case 1:
                                        /* ========== Metode Eliminasi Gauss ========== */
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
                                                        /* ============ Input Ketik =========== */
                                                        boolean isInputUkuranValid = true;
                                                        while(isInputUkuranValid) {
                                                            println("\n ===== Metode Ketik ===== ");
                                                            try {
                                                                print(" >>> Masukkan Ukuran Matriks (m x n) : ");
                                                                int row = scanner.nextInt();
                                                                int col = scanner.nextInt();
                                                                scanner.nextLine();
                                                                if(row <= 0 || col <= 0){
                                                                    println("Ukuran Matriks harus bilangan bulat positif");
                                                                }
                                                                else {
                                                                    println(">>> Masukkan Nilai Matriks : ");
                                                                    SPL spl = new SPL(row,col);
                                                                    /* Baca Isi Matrix */
                                                                    boolean isInputMatrixValid = true;
                                                                    int i = 0;
                                                                    while(i < row && isInputMatrixValid){
                                                                        int j = 0;
                                                                        while(j < col && isInputMatrixValid) {
                                                                            try {
                                                                                double elmt = scanner.nextDouble();
                                                                                spl.spl.setMElmt(elmt,i,j);
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
                                                                        spl.spl.gaussAndSolutions();
                                                                        println(" ================== HASIL ================== ");
                                                                        println(spl.spl.getStep());
                                                                        isInputUkuranValid = false;
                                                                        isInputMenu = false;
                                                                        isSPLMenu = false;
                                                                    }
                                                                }
                                                            } catch (Exception InputMismatchException){
                                                                println("Ukuran Matrix salah. Pastikan ukuran adalah bilangan bulat");
                                                            }
                                                        }
                                                        break;
                                                    case 2:
                                                        /* ============ Input File =========== */
                                                        SPL spl = new SPL();
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
                                                                            spl.spl.setMElmt(temp, row, i);
                                                                        }
                                                                        row++;
                                                                    }
                                                                    spl.spl.setMatrixRow(row);
                                                                    spl.spl.setMatrixCol(column);
                                                                    spl.spl.gaussAndSolutions();
                                                                    println(" ================== HASIL ================== ");
                                                                    println(spl.spl.getStep());
                                                                    inputValid = true;
                                                                    isInputMenu = false;
                                                                    isSPLMenu = false;
                                                                    readFile.close();
                                                                } catch (FileNotFoundException e) {
                                                                    println("Alamat file salah. Contoh alamat benar : src/input.txt ");
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    case 3:
                                                        /* ============ Kembali ============ */
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
                                        /* ========== Metode Eliminasi Gauss-Jordan ========== */
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
                                                        /* ============ Input Ketik =========== */
                                                        boolean isInputUkuranValid = false;
                                                        while(!isInputUkuranValid) {
                                                            println("\n ===== Metode Ketik ===== ");
                                                            try {
                                                                print(" >>> Masukkan Ukuran Matriks (m x n) : ");
                                                                int row = scanner.nextInt();
                                                                int col = scanner.nextInt();
                                                                scanner.nextLine();
                                                                if(row <= 0 || col <= 0){
                                                                    println("Ukuran Matriks harus bilangan bulat positif");
                                                                }
                                                                else {
                                                                    println(">>> Masukkan Nilai Matriks : ");
                                                                    SPL spl = new SPL(row,col);
                                                                    /* Baca Isi Matrix */
                                                                    boolean isInputMatrixValid = true;
                                                                    int i = 0;
                                                                    while(i < row && isInputMatrixValid){
                                                                        int j = 0;
                                                                        while(j < col && isInputMatrixValid) {
                                                                            try {
                                                                                double elmt = scanner.nextDouble();
                                                                                spl.spl.setMElmt(elmt,i,j);
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
                                                                        spl.spl.obeGaussJordan();
                                                                        println(" ================== HASIL ================== ");
                                                                        println(spl.spl.getStep());
                                                                        isInputUkuranValid = true;
                                                                        isInputMenu = false;
                                                                        isSPLMenu = false;
                                                                    }
                                                                }
                                                            } catch (Exception InputMismatchException){
                                                                println("Ukuran Matrix salah. Pastikan ukuran adalah bilangan bulat");
                                                            }
                                                        }
                                                        break;
                                                    case 2:
                                                        /* ============ Input File =========== */
                                                        SPL spl = new SPL();
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
                                                                            spl.spl.setMElmt(temp, row, i);
                                                                        }
                                                                        row++;
                                                                    }
                                                                    spl.spl.setMatrixRow(row);
                                                                    spl.spl.setMatrixCol(column);
                                                                    spl.spl.obeGaussJordan();
                                                                    println(" ================== HASIL ================== ");
                                                                    println(spl.spl.getStep());
                                                                    inputValid = true;
                                                                    isInputMenu = false;
                                                                    isSPLMenu = false;
                                                                    readFile.close();
                                                                } catch (FileNotFoundException e) {
                                                                    println("Alamat file salah. Contoh alamat benar : src/input.txt ");
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    case 3:
                                                        /* ============ Kembali ============ */
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
                                    /* TODO CASE 3 CASE 4*/
                                    case 3:
                                        /* ========== Metode Matriks Balikan ========== */
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
                                                        /* ============ Input Ketik =========== */
                                                        boolean isInputUkuranValid = true;
                                                        while(isInputUkuranValid) {
                                                            println("\n ===== Metode Ketik ===== ");
                                                            try {
                                                                print(" >>> Masukkan Ukuran Matriks (m x n) : ");
                                                                int row = scanner.nextInt();
                                                                int col = scanner.nextInt();
                                                                scanner.nextLine();
                                                                if(row <= 0 || col <= 0){
                                                                    println("Ukuran Matriks harus bilangan bulat positif");
                                                                }
                                                                else {
                                                                    println(">>> Masukkan Nilai Matriks : ");
                                                                    SPL spl = new SPL(row,col);
                                                                    /* Baca Isi Matrix */
                                                                    boolean isInputMatrixValid = true;
                                                                    int i = 0;
                                                                    while(i < row && isInputMatrixValid){
                                                                        int j = 0;
                                                                        while(j < col && isInputMatrixValid) {
                                                                            try {
                                                                                double elmt = scanner.nextDouble();
                                                                                spl.spl.setMElmt(elmt,i,j);
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
                                                                        spl.spl.gaussAndSolutions();
                                                                        println(" ================== HASIL ================== ");
                                                                        println(spl.spl.getStep());
                                                                        isInputUkuranValid = false;
                                                                        isInputMenu = false;
                                                                        isSPLMenu = false;
                                                                    }
                                                                }
                                                            } catch (Exception InputMismatchException){
                                                                println("Ukuran Matrix salah. Pastikan ukuran adalah bilangan bulat");
                                                            }
                                                        }
                                                        break;
                                                    case 2:
                                                        /* ============ Input File ============ */
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
                                                                    isSPLMenu = false;
                                                                    readFile.close();
                                                                } catch (FileNotFoundException e) {
                                                                    println("Alamat file salah. Contoh alamat benar : src/input.txt ");
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    case 3:
                                                        /* ============ Kembali ============ */
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
                                    case 4:
                                        /* ========== Kaidah Cramer ========== */
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
                                                        /* ============ Input Ketik ============ */
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
                                                                            isSPLMenu = false;
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
                                                        /* ============ Input File ============ */
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
                                                                    isSPLMenu = false;
                                                                    readFile.close();
                                                                } catch (FileNotFoundException e) {
                                                                    println("Alamat file salah. Contoh alamat benar : src/input.txt ");
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    case 3:
                                                        /* ============ Kembali ============ */
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
                                    case 5:
                                        /* ========== Kembali ========== */
                                        isSPLMenu = false;
                                        break;
                                    default:
                                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 5");
                                }
                            } catch (Exception InputMismatchException){
                                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 5");
                            }
                        }
                        break;
                    case 2:
                        /* =========== MENU DETERMINAN =========== */
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
                                        /* ========== Metode Ekspansi Kofaktor ========== */
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
                                                        /* ========== Input Ketik ========== */
                                                        boolean isInputUkuranValid = true;
                                                        while(isInputUkuranValid) {
                                                            println("\n ===== Metode Ketik ===== ");
                                                            println("Ketik -1 untuk kembali ");
                                                            try {
                                                                print(" >>> Masukkan Ukuran Matriks (n x n) : ");
                                                                int n = Integer.parseInt(scanner.nextLine());
                                                                if(n <= 0){
                                                                    isInputUkuranValid = false ;
                                                                }
                                                                else {
                                                                    println(" >>> Masukkan Nilai Matriks :");
                                                                    DeterminanInvers determinan = new DeterminanInvers(n,n);
                                                                    /* Baca Isi Matrix */
                                                                    boolean isInputMatrixValid = true;
                                                                    int i = 0;
                                                                    while (i < n && isInputMatrixValid) {
                                                                        int j = 0;
                                                                        while (j < n && isInputMatrixValid) {
                                                                            try {
                                                                                double elmt = scanner.nextDouble();
                                                                                determinan.contents.setMElmt(elmt, i, j);
                                                                            } catch (Exception InputMismatchException) {
                                                                                println("Isi Matrix salah. Pastikan isi matriks adalah angka desimal");
                                                                                isInputMatrixValid = false;
                                                                            }
                                                                            j++;
                                                                        }
                                                                        i++;
                                                                    }
                                                                    scanner.nextLine();
                                                                    if (isInputMatrixValid) {
                                                                        println(" ================== HASIL ================== ");
                                                                        println("   Determinan = " + determinan.determinanKofaktor());
                                                                        isInputUkuranValid = false;
                                                                        isInputMenu = false;
                                                                        isDeterminanMenu = false;
                                                                    }
                                                                }
                                                            } catch (Exception InputMismatchException){
                                                                println("Ukuran Matrix salah. Pastikan ukuran adalah bilangan bulat");
                                                            }
                                                        }
                                                        break;
                                                    case 2:
                                                        /* ========== Input File ========== */
                                                        DeterminanInvers determinan = new DeterminanInvers();
                                                        boolean isinputValid = true;
                                                        while(isinputValid) {
                                                            boolean isInputMatrixValid = true;
                                                            println("Ketik 0 untuk kembali");
                                                            print(" >>> Masukkan alamat file: ");
                                                            String path = scanner.nextLine();
                                                            if(Objects.equals(path, "0")) {
                                                                isinputValid = false;
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
                                                                    readFile.close();
                                                                    if(isInputMatrixValid) {
                                                                        determinan.contents.setMatrixRow(row);
                                                                        determinan.contents.setMatrixCol(column);
                                                                        println(" ================== HASIL ================== ");
                                                                        println("   Determinan =   " + determinan.determinanKofaktor());
                                                                        isinputValid = false;
                                                                        isInputMenu = false;
                                                                        isDeterminanMenu = false;
                                                                    }
                                                                } catch (FileNotFoundException e) {
                                                                    println("Alamat file salah. Contoh alamat benar : src/input.txt ");
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    case 3:
                                                        /* ========== Kembali ========== */
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
                                        /* ========== Metode Reduksi Baris dengan OBE ========== */
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
                                                        /* ========== Input Ketik ========== */
                                                        boolean isInputUkuranValid = true;
                                                        while(isInputUkuranValid) {
                                                            println("\n ===== Metode Ketik ===== ");
                                                            println("Ketik -1 untuk kembali ");
                                                            try {
                                                                print(" >>> Masukkan Ukuran Matriks (n x n) : ");
                                                                int n = Integer.parseInt(scanner.nextLine());
                                                                if(n <= 0){
                                                                    isInputUkuranValid = false ;
                                                                }
                                                                else {
                                                                    println(" >>> Masukkan Nilai Matriks :");
                                                                    DeterminanInvers determinan = new DeterminanInvers(n,n);
                                                                    /* Baca Isi Matrix */
                                                                    boolean isInputMatrixValid = true;
                                                                    int i = 0;
                                                                    while (i < n && isInputMatrixValid) {
                                                                        int j = 0;
                                                                        while (j < n && isInputMatrixValid) {
                                                                            try {
                                                                                double elmt = scanner.nextDouble();
                                                                                determinan.contents.setMElmt(elmt, i, j);
                                                                            } catch (Exception InputMismatchException) {
                                                                                println("Isi Matrix salah. Pastikan isi matriks adalah angka desimal");
                                                                                isInputMatrixValid = false;
                                                                            }
                                                                            j++;
                                                                        }
                                                                        i++;
                                                                    }
                                                                    scanner.nextLine();
                                                                    if (isInputMatrixValid) {
                                                                        determinan.CalculateOBE();
                                                                        println(" ================== HASIL ================== ");
                                                                        println("   Determinan = " + determinan.result);
                                                                        isInputUkuranValid = false;
                                                                        isInputMenu = false;
                                                                        isDeterminanMenu = false;
                                                                    }
                                                                }
                                                            } catch (Exception InputMismatchException){
                                                                println("Ukuran Matrix salah. Pastikan ukuran adalah bilangan bulat");
                                                            }
                                                        }
                                                        break;
                                                    case 2:
                                                        /* ========== Input File ========== */
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
                                                                    determinan.CalculateOBE();
                                                                    println(" ================== HASIL ================== ");
                                                                    println("   Determinan =   " + determinan.result);
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
                                                        /* ========== Kembali ========== */
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
                    case 3:
                        /* =========== MENU MATRIX BALIKAN =========== */

                        break;
                    case 4:
                        /* =========== MENU INTERPOLASI POLINOM =========== */
                        break;
                    case 5:
                        /* =========== MENU INTERPOLASI BICUBIC SPLINE =========== */
                        break;
                    case 6:
                        /* =========== MENU REGRESI LINIER BERGANDA =========== */
                        break;
                    case 7:
                        /* =========== KELUAR PROGRAM =========== */
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