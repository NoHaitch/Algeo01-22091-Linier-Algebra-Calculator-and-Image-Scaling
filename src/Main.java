import models.SPL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
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
        println("===== Selamat Datang pada Aplikasi Perhitungan Matriks dan SPL =====");
        Scanner scanner = new Scanner(System.in);
        boolean program = true;
        while(program) {
            /* ============================== MENU UTAMA ============================== */
            println("\n =============== MENU UTAMA ===============");
            println("1. Sistem Persamaaan Linier");
            println("2. Determinan");
            println("3. Matriks balikan");
            println("4. Interpolasi Polinom");
            println("5. Interpolasi Bicubic Spline");
            println("6. Regresi linier berganda");
            println("7. Keluar");
            print(" >>> Pilih menu : ");
            boolean isInputMenuUtamaValid = false;
            int opsiMenuUtama = -1;
            try {
                opsiMenuUtama = Integer.parseInt(scanner.nextLine());
                isInputMenuUtamaValid = true;
            } catch (Exception e){
                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 7");
            }
            if(isInputMenuUtamaValid){
                String tipeMenuUtama = "";
                switch (opsiMenuUtama){
                    case 1:
                        tipeMenuUtama = "SPL";
                        break;
                    case 2:
                        tipeMenuUtama = "Determinan";
                        break;
                    case 3:
                        tipeMenuUtama = "Balikan";
                        break;
                    case 4:
                        tipeMenuUtama = "Interpolasi";
                        break;
                    case 5:
                        tipeMenuUtama = "Bicubic";
                        break;
                    case 6:
                        tipeMenuUtama = "Regresi";
                        break;
                    case 7:
                        program = false;
                        break;
                    default:
                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 7");
                }

                if(tipeMenuUtama.equals("SPL")){
                    /* =========== MENU SPL =========== */
                    boolean SPLMenu = true;
                    while(SPLMenu) {
                        println("\n ========== Pilih Metode Penyelesaian SPL ==========");
                        println("1. Metode Eliminasi Gauss");
                        println("2. Metode Eliminasi Gauss-Jordan");
                        println("3. Metode Matriks Balikan ");
                        println("4. Kaidah Cramer");
                        println("5. Kembali");
                        print(" >>> Pilih Metode : ");
                        boolean isInputMenuSPLValid = false;
                        int opsiMenuSPL = -1;
                        try {
                            opsiMenuSPL = Integer.parseInt(scanner.nextLine());
                            isInputMenuSPLValid = true;
                        } catch (Exception e){
                            println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 5");
                        }

                        if(isInputMenuSPLValid){
                            String tipeMenuSPL = "";
                            switch (opsiMenuSPL){
                                case 1:
                                    tipeMenuSPL = "Gauss";
                                    break;
                                case 2:
                                    tipeMenuSPL = "Gauss-Jordan";
                                    break;
                                case 3:
                                    tipeMenuSPL = "Balikan";
                                    break;
                                case 4:
                                    tipeMenuSPL = "Cramer";
                                    break;
                                case 5:
                                    SPLMenu = false;
                                    break;
                                default:
                                    println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 5");
                            }
                            SPL spl = new SPL(1000,1000);
                            if(!tipeMenuSPL.isEmpty()) {
                                boolean inputSPL = true;
                                while (inputSPL) {
                                    /* =========== INPUT MATRIKS SPL =========== */
                                    println("\n ========== Pilih Metode Masukkan ==========");
                                    println("1. Masukkan Ketik");
                                    println("2. Masukkan dalam bentuk File");
                                    println("3. Kembali");
                                    print(" >>> Pilih Metode Masukkan : ");
                                    boolean isInputSPLValid = false;
                                    try {
                                        opsiMenuSPL = Integer.parseInt(scanner.nextLine());
                                        isInputSPLValid = true;
                                    } catch (Exception e) {
                                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                    }
                                    if (isInputSPLValid) {
                                        boolean terisiSPL = false;
                                        switch (opsiMenuSPL) {
                                            case 1:
                                                /* ============ Input Ketik =========== */
                                                boolean inputKetik = true;
                                                while (inputKetik) {
                                                    println("\n ========== Metode Ketik ==========");
                                                    print(" >>> Masukkan Ukuran Matriks baris x kolom (m x n) : ");
                                                    int row = -1;
                                                    int col = -1;
                                                    try {
                                                        row = scanner.nextInt();
                                                        col = scanner.nextInt();
                                                    } catch (Exception e) {
                                                        println("Ukuran Matriks harus bilangan bulat positif");
                                                    }
                                                    scanner.nextLine();
                                                    if (row != -1 && col != -1) {
                                                        if (row <= 0 || col <= 1) {
                                                            println("Ukuran Matriks harus bilangan bulat positif dan kolom harus lebih dari 1");
                                                        } else {
                                                            spl = new SPL(row,col);
                                                            println(">>> Masukkan Isi Matriks " + row + " x " + col + " : ");
                                                            boolean isInputMatrixValid = true;
                                                            int i = 0;
                                                            while (i < row && isInputMatrixValid) {
                                                                int j = 0;
                                                                while (j < col && isInputMatrixValid) {
                                                                    try {
                                                                        double elmt = scanner.nextDouble();
                                                                        spl.spl.setMElmt(elmt, i, j);
                                                                    } catch (Exception e) {
                                                                        println(e.toString());
                                                                        println("Isi Matrix salah. Pastikan isi matriks adalah angka desimal");
                                                                        isInputMatrixValid = false;
                                                                    }
                                                                    j++;
                                                                }
                                                                i++;
                                                            }
                                                            scanner.nextLine();
                                                            if (isInputMatrixValid) {
                                                                inputKetik = false;
                                                                terisiSPL = true;
                                                            }
                                                        }
                                                    }
                                                }
                                                break;
                                            case 2:
                                                /* ============ Input File =========== */
                                                /* ASUMSI : isi FILE BENAR */
                                                boolean inputFile = true;
                                                while (inputFile) {
                                                    println("Ketik 0 untuk kembali");
                                                    print(" >>> Masukkan alamat file: ");
                                                    String path = scanner.nextLine();
                                                    if (path.equals("0")) {
                                                        inputFile = false;
                                                    } else {
                                                        File file;
                                                        Scanner readFile = null;
                                                        boolean filefound = false;
                                                        try {
                                                            file = new File(path);
                                                            readFile = new Scanner(file);
                                                            filefound = true;
                                                        } catch (FileNotFoundException e) {
                                                            println("Alamat file salah. Contoh alamat benar : src/input.txt ");
                                                        }
                                                        if(filefound) {
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
                                                            inputFile = false;
                                                            terisiSPL = true;
                                                        }
                                                    }
                                                }
                                                break;
                                            case 3:
                                                inputSPL = false;
                                                break;
                                            default:
                                                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                        }
                                        if(terisiSPL){
                                            if (tipeMenuSPL.equals("Gauss")) {
                                                spl.spl.gaussAndSolutions();
                                            } else if (tipeMenuSPL.equals("Gauss-Jordan")) {
                                                spl.spl.obeGaussJordan();
                                            } else if (tipeMenuSPL.equals("Balikan")) {
                                                print("Balikan");
                                            } else {
                                                spl.spl.printAugmented();
                                                spl.cramer();
                                            }
                                            println(" ================== HASIL ================== ");
                                            println(spl.spl.getStep());
                                            inputSPL = false;
                                            SPLMenu = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }






        scanner.close();
    }
}
