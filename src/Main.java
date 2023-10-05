import models.*;
import operations.Matrix;
import operations.OBE;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;


public class Main {

    public static void println(String str) {
        System.out.println(str);
    }

    public static void print(String str) {
        System.out.print(str);
    }

    public static boolean imageFile(String str) {
        // Regex to check valid image file extension.
        String regex = "([^ ]+(\\.(?i)(jpeg|jpg|png))$)";
        Pattern p = Pattern.compile(regex);
        if (str == null) {
            return false;
        }
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean textFile(String str) {
        // Regex to check valid image file extension.
        String regex = "([^ ]+(\\.(?i)(txt))$)";
        Pattern p = Pattern.compile(regex);
        if (str == null) {
            return false;
        }
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static void printHeader() {
        String accum = "\n\n";
        File input = new File("../src/header.txt");
        try {
            Scanner readFile = new Scanner(input);
            String line;
            while (readFile.hasNextLine() && (line = readFile.nextLine()) != null) {
                accum += line + "\n";
            }
            readFile.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        System.out.println(accum);
    }

    public static void main(String[] args) {
        BicubicSpline.setStaticInvX();
        ImageBSI.setXInvxDMat();
        printHeader();
        println("\n***** ==================== APLIKASI MULAI ==================== *****");
        println("===== Selamat Datang pada Aplikasi Perhitungan Matriks dan SPL =====");
        Scanner scanner = new Scanner(System.in);
        boolean program = true;
        while (program) {
            /* ============================== MENU UTAMA ============================== */
            println("\n =============== MENU UTAMA ===============");
            println("1. Sistem Persamaaan Linier");
            println("2. Determinan");
            println("3. Matriks balikan");
            println("4. Interpolasi Polinom");
            println("5. Interpolasi Bicubic Spline");
            println("6. Regresi linier berganda");
            println("7. Pembesaran Gambar dengan Interpolasi Bicubic Spline");
            println("8. Keluar");
            print(" >>> Pilih menu : ");
            boolean isInputMenuUtamaValid = false;
            int opsiMenuUtama = -1;
            try {
                opsiMenuUtama = Integer.parseInt(scanner.nextLine());
                isInputMenuUtamaValid = true;
            } catch (Exception e) {
                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 7");
            }
            if (isInputMenuUtamaValid) {
                String tipeMenuUtama = "";
                switch (opsiMenuUtama) {
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
                        tipeMenuUtama = "ImageBSI";
                        break;
                    case 8:
                        program = false;
                        break;
                    default:
                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 7");
                }

                switch (tipeMenuUtama) {
                    case "SPL" : {
                        /* =========== MENU SPL =========== */
                        boolean SPLMenu = true;
                        while (SPLMenu) {
                            println("\n ========== Pilih Metode Penyelesaian SPL ==========");
                            println("1. Metode Eliminasi Gauss");
                            println("2. Metode Eliminasi Gauss-Jordan");
                            println("3. Metode Matriks Balikan ");
                            println("4. Kaidah Kramer");
                            println("5. Kembali");
                            print(" >>> Pilih Metode : ");
                            boolean isInputMenuSPLValid = false;
                            int opsiMenuSPL = -1;
                            try {
                                opsiMenuSPL = Integer.parseInt(scanner.nextLine());
                                isInputMenuSPLValid = true;
                            } catch (Exception e) {
                                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 5");
                            }

                            if (isInputMenuSPLValid) {
                                String tipeMenuSPL = "";
                                switch (opsiMenuSPL) {
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
                                        tipeMenuSPL = "Kramer";
                                        break;
                                    case 5:
                                        SPLMenu = false;
                                        break;
                                    default:
                                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 5");
                                }
                                SPL spl = new SPL(1000, 500);
                                if (!tipeMenuSPL.isEmpty()) {
                                    boolean inputSPL = true;
                                    while (inputSPL) {
                                        /* =========== INPUT MATRIKS SPL =========== */
                                        println("\n ========== Pilih Metode Masukkan ==========");
                                        println("1. Masukkan Ketik");
                                        println("2. Masukkan dalam bentuk File");
                                        println("3. Kembali");
                                        print(" >>> Pilih Metode Masukkan : ");
                                        boolean isInputSPLValid = false;
                                        int opsiMenuInputSPL = -1;
                                        try {
                                            opsiMenuInputSPL = Integer.parseInt(scanner.nextLine());
                                            isInputSPLValid = true;
                                        } catch (Exception e) {
                                            println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                        }
                                        if (isInputSPLValid) {
                                            boolean terisiSPL = false;
                                            String inputText = "";
                                            switch (opsiMenuInputSPL) {
                                                case 1:
                                                    /* ============ Input Ketik =========== */
                                                    boolean inputKetik = true;
                                                    while (inputKetik) {
                                                        println("\n ========== Metode Ketik ==========");
                                                        println("Ketik -1 untuk kembali");
                                                        if (tipeMenuSPL.equals("Balikan") || tipeMenuSPL.equals("Kramer")) {
                                                            print(" >>> Masukkan Ukuran Matriks (n x (n+1)), n : ");
                                                        } else {
                                                            print(" >>> Masukkan Ukuran Matriks baris x kolom (m x n) : ");
                                                        }
                                                        int row = -2;
                                                        int col = -2;
                                                        try {
                                                            row = scanner.nextInt();
                                                            if (tipeMenuSPL.equals("Balikan") || tipeMenuSPL.equals("Kramer")) {
                                                                col = row+1;
                                                            } else if (row != -1) {
                                                                col = scanner.nextInt();
                                                            }
                                                            scanner.nextLine();

                                                        } catch (Exception e) {
                                                            println("Ukuran Matriks harus bilangan bulat positif");
                                                        }
                                                        if (row == -1 || col == -1) {
                                                            inputKetik = false;
                                                        } else if (row != -2 && col != -2) {
                                                            if (row <= 0 || col <= 1) {
                                                                println("Ukuran Matriks harus bilangan bulat positif dan kolom harus lebih dari 1");
                                                            } else {
                                                                spl = new SPL(row, col);
                                                                println(" >>> Masukkan Isi Matriks " + row + " x " + col + " : ");
                                                                boolean isInputMatrixValid = true;
                                                                int i = 0;
                                                                while (i < row && isInputMatrixValid) {
                                                                    int j = 0;
                                                                    while (j < col && isInputMatrixValid) {
                                                                        try {
                                                                            double elmt = scanner.nextDouble();
                                                                            spl.spl.setMElmt(elmt, i, j);
                                                                        } catch (Exception e) {
                                                                            println("Isi Matrix salah. Pastikan isi matriks adalah bilangan real");
                                                                            isInputMatrixValid = false;
                                                                        }
                                                                        j++;
                                                                    }
                                                                    i++;
                                                                }
                                                                scanner.nextLine();
                                                                if (isInputMatrixValid) {
                                                                    if (tipeMenuSPL.equals("Balikan") || tipeMenuSPL.equals("Kramer")) {
                                                                        inputText += " >>> Masukkan Ukuran Matriks (n x (n + 1)), n : " + row + "\n";
                                                                    } else {
                                                                        inputText += " >>> Masukkan Ukuran Matriks (m x n) : " + row + " " + col + "\n";
                                                                    }
                                                                    inputText += " >>> Masukkan Isi Matriks " + row + " x " + col + " : \n";
                                                                    for (i = 0; i < row; i++) {
                                                                        for (int j = 0; j < col; j++) {
                                                                            String temp = String.valueOf(spl.spl.getMElmt(i, j));
                                                                            inputText += temp;
                                                                            inputText += " ";
                                                                        }
                                                                        inputText += "\n";
                                                                    }
                                                                    spl.spl.addAugmentedToStep(9);
                                                                    inputKetik = false;
                                                                    terisiSPL = true;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 2:
                                                    /* ============ Input File =========== */
                                                    /* Pengecekan File hanya dilakukan untukk kasus masalah isi yang bukan angka */
                                                    boolean inputFile = true;
                                                    while (inputFile) {
                                                        println("Ketik 0 untuk kembali");
                                                        print(" >>> Masukkan nama file: ");
                                                        String path = scanner.nextLine();
                                                        if (path.equals("0")) {
                                                            inputFile = false;
                                                        } else if(!textFile(path)){
                                                            println("File bukan text");
                                                        }
                                                        else {
                                                            File file;
                                                            Scanner readFile = null;
                                                            boolean filefound = false;
                                                            try {
                                                                file = new File("../test/spl/input/" + path);
                                                                readFile = new Scanner(file);
                                                                filefound = true;
                                                            } catch (FileNotFoundException e) {
                                                                println("Nama file salah. Contoh nama benar : baby.txt");
                                                            }
                                                            if (filefound)
                                                            {
                                                                boolean valid = true;
                                                                String line;
                                                                int row = 0;
                                                                int column = 0;
                                                                while (readFile.hasNextLine() && (line = readFile.nextLine()) != null && valid) {
                                                                    String[] saved = line.split(" ");
                                                                    if (row == 0) {
                                                                        column = saved.length;
                                                                    }
                                                                    int i;
                                                                    for (i = 0; i < column; i++) {
                                                                        try {
                                                                            double temp = Double.parseDouble(saved[i]);
                                                                            spl.spl.setMElmt(temp, row, i);
                                                                        } catch (Exception e) {
                                                                            valid = false;
                                                                            break;
                                                                        }
                                                                    }
                                                                    row++;
                                                                }
                                                                if (!valid) {
                                                                    println("Isi File Salah! Pastikan file adalah text yang diisi oleh matriks berisi bilangan real yang dipisahkan spasi dan enter");
                                                                } else {
                                                                    inputText += " >>> Masukkan nama file: " + path + "\n";
                                                                    spl.spl.setMatrixRow(row);
                                                                    spl.spl.setMatrixCol(column);
                                                                    inputFile = false;
                                                                    terisiSPL = true;
                                                                }
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
                                            if (terisiSPL) {
                                                boolean inverseSuccess = true;
                                                boolean crammerSuccess = true;
                                                switch (tipeMenuSPL) {
                                                    case "Gauss" : spl.spl.gaussAndSolutions();
                                                    break;
                                                    case "Gauss-Jordan" : spl.spl.obeGaussJordan();
                                                    break;
                                                    case "Balikan" : {
                                                        inverseSuccess = spl.solveWithInverse();}
                                                    break;
                                                    default : {
                                                        crammerSuccess = spl.solveWithCramer(spl.spl);
                                                    }
                                                }

                                                println(" ================== HASIL ================== ");
                                                if(tipeMenuSPL.equals("Kramer") && crammerSuccess){
                                                    spl.spl.addStringToStep("\nSolusi : \n");
                                                    for(int i=0;i<spl.listnilaivar.getColEff();i++){
                                                            spl.spl.addStringToStep("--> X" + (i+1) + " = " + spl.listnilaivar.getElmt(0,i) + "\n");
                                                    }
                                                    spl.spl.addNewLineToStep();
                                                    spl.spl.addNewLineToStep();
                                                } 
                                                if(!inverseSuccess || !crammerSuccess){
                                                    spl.spl.addStringToStep("Tidak dapat menghitung solusi karena matriks tidak memiliki Invers.\n");
                                                }
                                                println(spl.spl.getStep());
                                                boolean menuSimpan = true;
                                                while (menuSimpan) {
                                                    print(" >>> Apakah ingin disimpan dalam file? [y/n]: ");
                                                    String opsiSimpan = scanner.nextLine();
                                                    if (opsiSimpan.equals("y") || opsiSimpan.equals("Y")) {
                                                        inputText += "\n\n ================== HASIL ================== \n";
                                                        print(" >>> Masukkan nama file hasil: ");
                                                        String name = scanner.nextLine();
                                                        while(!textFile(name)){
                                                            println("Nama file salah!");
                                                            print(" >>> Masukkan nama file hasil: ");
                                                            name = scanner.nextLine();
                                                        }
                                                        spl.saveToTextFile(("../test/spl/output/" + name), inputText);
                                                        menuSimpan = false;
                                                    } else if (opsiSimpan.equals("n") || opsiSimpan.equals("N")) {
                                                        menuSimpan = false;
                                                    } else {
                                                        println("Masukkan salah pilih y / n");
                                                    }
                                                }
                                                inputSPL = false;
                                                SPLMenu = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                    case "Determinan" : {
                        /* =========== MENU Determinan =========== */
                        boolean detMenu = true;
                        while (detMenu) {
                            println("\n   ========== Pilih Metode Determinan ========== ");
                            println("1. Metode Ekspansi Kofaktor");
                            println("2. Metode Reduksi Baris dengan OBE");
                            println("3. Kembali");
                            print(" >>> Pilih Metode Determinan : ");
                            boolean isInputMenuDetValid = false;
                            int opsiMenuDet = -1;
                            try {
                                opsiMenuDet = Integer.parseInt(scanner.nextLine());
                                isInputMenuDetValid = true;
                            } catch (Exception e) {
                                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                            }
                            if (isInputMenuDetValid) {
                                String tipeMenuDet = "";
                                switch (opsiMenuDet) {
                                    case 1:
                                        tipeMenuDet = "Kofaktor";
                                        break;
                                    case 2:
                                        tipeMenuDet = "OBE";
                                        break;
                                    case 3:
                                        detMenu = false;
                                        break;
                                    default:
                                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                }
                                DeterminanInvers det = new DeterminanInvers(1000, 1000);
                                if (!tipeMenuDet.isEmpty()) {
                                    boolean inputDet = true;
                                    String inputText = "";
                                    while (inputDet) {
                                        /* =========== INPUT MATRIKS Determinan =========== */
                                        println("\n ========== Pilih Metode Masukkan ==========");
                                        println("1. Masukkan Ketik");
                                        println("2. Masukkan dalam bentuk File");
                                        println("3. Kembali");
                                        print(" >>> Pilih Metode Masukkan : ");
                                        boolean isInputDetValid = false;
                                        int opsiMenuInputDet = -1;
                                        try {
                                            opsiMenuInputDet = Integer.parseInt(scanner.nextLine());
                                            isInputDetValid = true;
                                        } catch (Exception e) {
                                            println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                        }
                                        if (isInputDetValid) {
                                            boolean terisiMatriks = false;
                                            switch (opsiMenuInputDet) {
                                                case 1:
                                                    /* ============ Input Ketik =========== */
                                                    boolean inputKetik = true;
                                                    while (inputKetik) {
                                                        println("\n ========== Metode Ketik ==========");
                                                        println("Ketik -1 untuk kembali");
                                                        print(" >>> Masukkan Ukuran Matriks (n x n) : ");
                                                        int row = -2;
                                                        int col = -2;
                                                        try {
                                                            row = scanner.nextInt();
                                                            col = row;
                                                        } catch (Exception e) {
                                                            println("Ukuran Matriks harus bilangan bulat positif");
                                                        }
                                                        scanner.nextLine();
                                                        if (row == -1) {
                                                            inputKetik = false;
                                                        } else if (row != -2) {
                                                            if (row <= 1) {
                                                                println("Ukuran Matriks harus bilangan bulat positif");
                                                            } else if (tipeMenuDet.equals("Kofaktor") && row > 7) {
                                                                println("Untuk Ukuran lebih dari 7 x 7, Tidak dapat menggunakan kofaktor. \nHal tersebut dikarenakan kurang efisien sehingga lama");
                                                            } else {
                                                                det = new DeterminanInvers(row, col);
                                                                println(">>> Masukkan Isi Matriks " + row + " x " + col + " : ");
                                                                boolean isInputMatrixValid = true;
                                                                int i = 0;
                                                                while (i < row && isInputMatrixValid) {
                                                                    int j = 0;
                                                                    while (j < col && isInputMatrixValid) {
                                                                        try {
                                                                            double elmt = scanner.nextDouble();
                                                                            det.contents.setMElmt(elmt, i, j);
                                                                        } catch (Exception e) {
                                                                            println("Isi Matrix salah. Pastikan isi matriks adalah bilangan real");
                                                                            isInputMatrixValid = false;
                                                                        }
                                                                        j++;
                                                                    }
                                                                    i++;
                                                                }
                                                                scanner.nextLine();
                                                                if (isInputMatrixValid) {
                                                                    inputText += " >>> Masukkan Ukuran Matriks (n x n) : " + row + "\n";
                                                                    inputText += " >>> Masukkan Isi Matriks " + row + " x " + col + " : \n";
                                                                    for (i = 0; i < row; i++) {
                                                                        for (int j = 0; j < col; j++) {
                                                                            String temp = String.valueOf(det.contents.getMElmt(i, j));
                                                                            inputText += temp;
                                                                            inputText += " ";
                                                                        }
                                                                        inputText += "\n";
                                                                    }
                                                                    inputKetik = false;
                                                                    terisiMatriks = true;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 2:
                                                    /* ============ Input File =========== */
                                                    /* Pengecekan File hanya dilakukan untukk kasus masalah isi yang bukan angka */
                                                    boolean inputFile = true;
                                                    while (inputFile) {
                                                        println("Ketik 0 untuk kembali");
                                                        print(" >>> Masukkan nama file: ");
                                                        String path = scanner.nextLine();
                                                        if (path.equals("0")) {
                                                            inputFile = false;
                                                        } else if(!textFile(path)){
                                                            println("File bukan text");
                                                        }else {
                                                            File file;
                                                            Scanner readFile = null;
                                                            boolean filefound = false;
                                                            try {
                                                                file = new File("../test/determinan/input/"+path);
                                                                readFile = new Scanner(file);
                                                                filefound = true;
                                                            } catch (FileNotFoundException e) {
                                                                println("Nama file salah. Contoh nama benar : baby.txt ");
                                                            }
                                                            if (filefound) {
                                                                boolean valid = true;
                                                                String line;
                                                                int row = 0;
                                                                int column = 0;
                                                                while (readFile.hasNextLine() && (line = readFile.nextLine()) != null && valid) {
                                                                    String[] saved = line.split(" ");
                                                                    if (row == 0) {
                                                                        column = saved.length;
                                                                    }
                                                                    int i;
                                                                    for (i = 0; i < column; i++) {
                                                                        try {
                                                                            double temp = Double.parseDouble(saved[i]);
                                                                            det.contents.setMElmt(temp, row, i);
                                                                        } catch (Exception e) {
                                                                            valid = false;
                                                                            break;
                                                                        }
                                                                    }
                                                                    row++;
                                                                }
                                                                if (!valid) {
                                                                    println("Isi File Salah! Pastikan file adalah text yang diisi oleh matriks berisi bilangan real yang dipisahkan spasi dan enter");
                                                                } else {
                                                                    inputText += " >>> Masukkan nama file: " + path + "\n";
                                                                    det.contents.setMatrixRow(row);
                                                                    det.contents.setMatrixCol(column);
                                                                    inputFile = false;
                                                                    terisiMatriks = true;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 3:
                                                    inputDet = false;
                                                    break;
                                                default:
                                                    println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                            }
                                            if (terisiMatriks) {
                                                println(" ================== HASIL ================== ");
                                                if (tipeMenuDet.equals("Kofaktor")) {
                                                    println(" Determinan = " + det.determinanKofaktor());
                                                } else {
                                                    det.CalculateOBE();
                                                    println(det.contents.getStep());
                                                }
                                                boolean menuSimpan = true;
                                                while (menuSimpan) {
                                                    print(" >>> Apakah ingin disimpan dalam file? [y/n]: ");
                                                    String opsiSimpan = scanner.nextLine();
                                                    if (opsiSimpan.equals("y") || opsiSimpan.equals("Y")) {
                                                        inputText += "\n\n ================== HASIL ================== \n\n";
                                                        if(tipeMenuDet.equals("Kofaktor")) {
                                                            inputText += " Determinan = " + det.determinanKofaktor();
                                                        }
                                                        print(" >>> Masukkan nama file hasil: ");
                                                        String name = scanner.nextLine();
                                                        while(!textFile(name)){
                                                            println("Nama file salah. Contoh nama benar : baby.txt ");
                                                            print(" >>> Masukkan nama file hasil: ");
                                                            name = scanner.nextLine();
                                                        }
                                                        det.saveToTextFile(("../test/determinan/output/" + name),inputText);
                                                        menuSimpan = false;
                                                    } else if (opsiSimpan.equals("n") || opsiSimpan.equals("N")) {
                                                        menuSimpan = false;
                                                    } else {
                                                        println("Masukkan salah pilih y / n");
                                                    }
                                                }
                                                inputDet = false;
                                                detMenu = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                    case "Balikan" : {
                        /* =========== MENU Matiks Balikan =========== */
                        boolean BalikanMenu = true;
                        while (BalikanMenu) {
                            println("\n   ========== Pilih Metode Matriks Balikan ========== ");
                            println("1. Metode Matriks Balikan");
                            println("2. Metode Adjoin");
                            println("3. Kembali");
                            print(" >>> Pilih Metode Determinan : ");
                            boolean isInputMenuBalikanValid = false;
                            int opsiMenuBalikan = -1;
                            try {
                                opsiMenuBalikan = Integer.parseInt(scanner.nextLine());
                                isInputMenuBalikanValid = true;
                            } catch (Exception e) {
                                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                            }

                            if (isInputMenuBalikanValid) {
                                String tipeMenuBalikan = "";
                                switch (opsiMenuBalikan) {
                                    case 1:
                                        tipeMenuBalikan = "Balikan";
                                        break;
                                    case 2:
                                        tipeMenuBalikan = "Adjoin";
                                        break;
                                    case 3:
                                        BalikanMenu = false;
                                        break;
                                    default:
                                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                }
                                DeterminanInvers invers = new DeterminanInvers(1000, 1000);
                                if (!tipeMenuBalikan.isEmpty()) {
                                    boolean inputBalikan = true;
                                    String inputText = "";
                                    while (inputBalikan) {
                                        /* =========== INPUT MATRIKS Balikan =========== */
                                        println("\n ========== Pilih Metode Masukkan ==========");
                                        println("1. Masukkan Ketik");
                                        println("2. Masukkan dalam bentuk File");
                                        println("3. Kembali");
                                        print(" >>> Pilih Metode Masukkan : ");
                                        boolean isInputInvValid = false;
                                        int opsiMenuInputBalikan = -1;
                                        try {
                                            opsiMenuInputBalikan = Integer.parseInt(scanner.nextLine());
                                            isInputInvValid = true;
                                        } catch (Exception e) {
                                            println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                        }
                                        if (isInputInvValid) {
                                            boolean terisiMatriks = false;
                                            switch (opsiMenuInputBalikan) {
                                                case 1:
                                                    /* ============ Input Ketik =========== */
                                                    boolean inputKetik = true;
                                                    while (inputKetik) {
                                                        println("\n ========== Metode Ketik ==========");
                                                        println("Ketik -1 Kembali ");
                                                        print(" >>> Masukkan Ukuran Matriks (n x n) : ");
                                                        int row = -2;
                                                        int col = -2;
                                                        try {
                                                            row = scanner.nextInt();
                                                            col = row;
                                                        } catch (Exception e) {
                                                            println("Ukuran Matriks harus bilangan bulat positif");
                                                        }
                                                        scanner.nextLine();
                                                        if (row != -1) {
                                                            if (row <= 1) {
                                                                println("Ukuran Matriks harus bilangan bulat positif");
                                                            } else {
                                                                invers = new DeterminanInvers(row, col);
                                                                println(">>> Masukkan Isi Matriks " + row + " x " + col + " : ");
                                                                boolean isInputMatrixValid = true;
                                                                int i = 0;
                                                                while (i < row && isInputMatrixValid) {
                                                                    int j = 0;
                                                                    while (j < col && isInputMatrixValid) {
                                                                        try {
                                                                            double elmt = scanner.nextDouble();
                                                                            invers.contents.setMElmt(elmt, i, j);
                                                                        } catch (Exception e) {
                                                                            println("Isi Matrix salah. Pastikan isi matriks adalah bilangan real");
                                                                            isInputMatrixValid = false;
                                                                        }
                                                                        j++;
                                                                    }
                                                                    i++;
                                                                }
                                                                scanner.nextLine();
                                                                if (isInputMatrixValid) {
                                                                    inputText += " >>> Masukkan Ukuran Matriks (n x n) : " + row + "\n";
                                                                    inputText += " >>> Masukkan Isi Matriks " + row + " x " + col + " : \n";
                                                                    for (i = 0; i < row; i++) {
                                                                        for (int j = 0; j < col; j++) {
                                                                            String temp = String.valueOf(invers.contents.getMElmt(i, j));
                                                                            inputText += temp;
                                                                            inputText += " ";
                                                                        }
                                                                        inputText += "\n";
                                                                    }
                                                                    inputKetik = false;
                                                                    terisiMatriks = true;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 2:
                                                    /* ============ Input File =========== */
                                                    boolean inputFile = true;
                                                    while (inputFile) {
                                                        println("Ketik 0 untuk kembali");
                                                        print(" >>> Masukkan nama file: ");
                                                        String path = scanner.nextLine();
                                                        if (path.equals("0")) {
                                                            inputFile = false;
                                                        } else if(!textFile(path)){
                                                            println("File bukan text");
                                                        } else {
                                                            File file;
                                                            Scanner readFile = null;
                                                            boolean filefound = false;
                                                            try {
                                                                file = new File("../test/inverse/input/"+path);
                                                                readFile = new Scanner(file);
                                                                filefound = true;
                                                            } catch (FileNotFoundException e) {
                                                                println("Nama file salah. Contoh nama benar : baby.txt ");
                                                            }
                                                            if (filefound) {
                                                                boolean valid = true;
                                                                String line;
                                                                int row = 0;
                                                                int column = 0;
                                                                while (readFile.hasNextLine() && (line = readFile.nextLine()) != null && valid) {
                                                                    String[] saved = line.split(" ");
                                                                    if (row == 0) {
                                                                        column = saved.length;
                                                                    }
                                                                    int i;
                                                                    for (i = 0; i < column; i++) {
                                                                        try {
                                                                            double temp = Double.parseDouble(saved[i]);
                                                                            invers.contents.setMElmt(temp, row, i);
                                                                        } catch (Exception e) {
                                                                            valid = false;
                                                                            break;
                                                                        }
                                                                    }
                                                                    row++;
                                                                }
                                                                if (!valid) {
                                                                    println("Isi File Salah! Pastikan file adalah text yang diisi oleh matriks berisi bilangan real yang dipisahkan spasi dan enter");
                                                                } else {
                                                                    inputText += " >>> Masukkan alamat file: " + path + "\n";
                                                                    invers.contents.setMatrixRow(row);
                                                                    invers.contents.setMatrixCol(column);
                                                                    inputFile = false;
                                                                    terisiMatriks = true;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    break;
                                                case 3:
                                                    inputBalikan = false;
                                                    break;
                                                default:
                                                    println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                            }
                                            if (terisiMatriks) {
                                                println(" ================== HASIL ================== ");
                                                inputText += "\n\n ================== HASIL ================== \n\n";
                                                if (invers.determinanKofaktor() == 0) {
                                                    println("  Determinan = 0");
                                                    println("  Matriks Tidak Memiliki Matriks\n");
                                                } else if (tipeMenuBalikan.equals("Balikan")) {
                                                    Matrix result = invers.inversMatrix();
                                                    println("  Hasil Inverse :");
                                                    result.displayMatrix();
                                                } else {
                                                    Matrix result = invers.contents.getCopyAugmented().inversMatrix();
                                                    println("  Hasil Inverse :");
                                                    result.displayMatrix();
                                                    for (int i = 0; i < invers.contents.getMatrixRow(); i++) {
                                                        for (int j = 0; j < invers.contents.getMatrixCol(); j++) {
                                                            inputText += result.createString(i, j, 7);
                                                            inputText += " ";
                                                        }
                                                        inputText += "\n";
                                                    }
                                                }
                                                boolean menuSimpan = true;
                                                while (menuSimpan) {
                                                    print("\n >>> Apakah ingin disimpan dalam file? [y/n]: ");
                                                    String opsiSimpan = scanner.nextLine();
                                                    if (opsiSimpan.equals("y") || opsiSimpan.equals("Y")) {
                                                        print(" >>> Masukkan nama file hasil: ");
                                                        String name = scanner.nextLine();
                                                        while(!textFile(name)){
                                                            println("Nama file salah. Contoh nama benar : baby.txt ");
                                                            print(" >>> Masukkan nama file hasil: ");
                                                            name = scanner.nextLine();
                                                        }
                                                        invers.saveToTextFile(("../test/inverse/output/" + name),inputText);
                                                        menuSimpan = false;
                                                    } else if (opsiSimpan.equals("n") || opsiSimpan.equals("N")) {
                                                        menuSimpan = false;
                                                    } else {
                                                        println("Masukkan salah pilih y / n");
                                                    }
                                                }
                                                inputBalikan = false;
                                                BalikanMenu = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                    case "Interpolasi" : {
                        /* =========== MENU Interpolasi =========== */
                        boolean InterpolasiMenu = true;
                        while (InterpolasiMenu) {
                            /* =========== INPUT Interpolasi =========== */
                            println("\n ========== Pilih Metode Masukkan ==========");
                            println("1. Masukkan Ketik");
                            println("2. Masukkan dalam bentuk File");
                            println("3. Kembali");
                            print(" >>> Pilih Metode Masukkan : ");
                            boolean isInputInterpolasiValid = false;
                            int opsiMenuInputInterpolasi = -1;
                            try {
                                opsiMenuInputInterpolasi = Integer.parseInt(scanner.nextLine());
                                isInputInterpolasiValid = true;
                            } catch (Exception e) {
                                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                            }
                            if (isInputInterpolasiValid) {
                                boolean terisiMatriks = false;
                                Interpolation interpolasi = new Interpolation(1000);
                                Matrix pointMatrix = new Matrix(1000, 1000);
                                double xInterpolasi = 0;
                                String inputText = "";
                                switch (opsiMenuInputInterpolasi) {
                                    case 1:
                                        /* ============ Input Ketik =========== */
                                        boolean inputKetik = true;
                                        while (inputKetik) {
                                            println("\n ========== Metode Ketik ==========");
                                            println("Ketik -1 Kembali ");
                                            print(" >>> Masukkan Jumlah Titik : ");
                                            int n = -100;
                                            try {
                                                n = scanner.nextInt();
                                                scanner.nextLine();
                                            } catch (Exception e) {
                                                println("Jumlah titik harus bilangan bulat positif");
                                                scanner.nextLine();
                                            }
                                            if (n == -1) {
                                                inputKetik = false;
                                            } else if (n != -100) {
                                                if (n < 1) {
                                                    println("Jumlah titik harus bilangan bulat positif");
                                                } else {
                                                    inputText = " >>> Masukkan Jumlah Titik : " + n + "\n";
                                                    interpolasi = new Interpolation(n);
                                                    println("Masukkan Titik - Titik (format: x y)");
                                                    int i = 0;
                                                    pointMatrix = new Matrix(n, 2);
                                                    while (i < n) {
                                                        try {
                                                            double x = scanner.nextDouble();
                                                            double y = scanner.nextDouble();
                                                            int j = 0;
                                                            boolean foundp = false;
                                                            while(j < i && !foundp){
                                                                if (x == pointMatrix.getElmt(j, 0)){
                                                                    foundp = true;
                                                                }
                                                                j++;
                                                            }
                                                            if(!foundp) {
                                                                inputText += x + " " + y + "\n";
                                                                pointMatrix.setElmt(x, i, 0);
                                                                pointMatrix.setElmt(y, i, 1);
                                                                i++;
                                                            } else{
                                                                println("Titik dengan absis " + x + " sudah pernah diberikan. Masukkan titik lain.");
                                                            }
                                                        } catch (Exception e) {
                                                            println("Masukkan Titik salah.");
                                                            scanner.nextLine();
                                                        }

                                                    }
                                                    scanner.nextLine();
                                                    boolean isNotvalid = true;
                                                    while (isNotvalid) {
                                                        print(" >>> Masukkan x yang akan ditaksir : ");
                                                        try {
                                                            xInterpolasi = scanner.nextDouble();
                                                            isNotvalid = false;
                                                            inputText += " >>> Masukkan x yang akan ditaksir : " + xInterpolasi + "\n";
                                                        } catch (Exception e) {
                                                            println("Masukkan x taksiran salah");
                                                            scanner.nextLine();
                                                        }
                                                    }
                                                    scanner.nextLine();
                                                    interpolasi.point = interpolasi.convertPtoM(pointMatrix);
                                                    inputKetik = false;
                                                    terisiMatriks = true;
                                                }
                                            }
                                        }
                                    break;
                                    case 2:
                                        /* ============ Input File =========== */
                                        boolean inputFile = true;
                                        while (inputFile) {
                                            println("Ketik 0 untuk kembali");
                                            print(" >>> Masukkan nama file: ");
                                            String path = scanner.nextLine();
                                            if (path.equals("0")) {
                                                inputFile = false;
                                            } else if(!textFile(path)){
                                                println("File bukan text");
                                            } else {
                                                File file;
                                                Scanner readFile = null;
                                                boolean filefound = false;
                                                try {
                                                    file = new File("../test/interpolasi/input/"+path);
                                                    readFile = new Scanner(file);
                                                    filefound = true;
                                                    inputFile = false;
                                                } catch (FileNotFoundException e) {
                                                    println("Nama file salah. Contoh nama benar : baby.txt ");
                                                }
                                                if (filefound) {
                                                    inputText += ">>> Masukkan nama file: " + path + "\n";
                                                    interpolasi.askDataPointFromFile("../test/interpolasi/input/" + path);
                                                    inputFile = false;
                                                    terisiMatriks = true;
                                                }
                                            }
                                        }
                                    break;
                                    case 3:
                                        InterpolasiMenu = false;
                                    break;
                                    default:
                                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                }
                                if (terisiMatriks) {
                                    Matrix matrix = interpolasi.point;
                                    OBE mEselon = new OBE(matrix.getRowEff(), matrix.getColEff());
                                    for (int i = 0; i < matrix.getRowEff(); i++) {
                                        for (int j = 0; j < matrix.getColEff(); j++) {
                                            mEselon.setMElmt(matrix.getElmt(i, j), i, j);
                                        }
                                    }
                                    mEselon.gaussAndSolutions();
                                    println(" ================== HASIL ================== ");
                                    print("  Fungsi :  ");
                                    interpolasi.displayFunction(mEselon);
                                    println("\n  Taksiran f(" + interpolasi.xRequest + ") =  " + interpolasi.taksiran(mEselon, interpolasi.xRequest) + "\n\n");
                                    boolean menuSimpan = true;
                                    while (menuSimpan) {
                                        print(" >>> Apakah ingin disimpan dalam file? [y/n]: ");
                                        String opsiSimpan = scanner.nextLine();
                                        if (opsiSimpan.equals("y") || opsiSimpan.equals("Y")) {
                                            inputText += "\n\n ================== HASIL ================== \n";
                                            print(" >>> Masukkan nama file hasil: ");
                                            String name = scanner.nextLine();
                                            while(!textFile(name)){
                                                println("Nama file salah. Contoh nama benar : baby.txt ");
                                                print(" >>> Masukkan nama file hasil: ");
                                                name = scanner.nextLine();
                                            }
                                            interpolasi.uploadhasil2File(interpolasi.taksiran(mEselon, xInterpolasi), xInterpolasi, ("../test/interpolasi/output/" + name), inputText);
                                            menuSimpan = false;
                                        } else if (opsiSimpan.equals("n") || opsiSimpan.equals("N")) {
                                            menuSimpan = false;
                                        } else {
                                            println("Masukkan salah pilih y / n");
                                        }
                                    }
                                    InterpolasiMenu = false;
                                }
                            }

                        }
                    }
                    break;
                    case "Bicubic" : {
                        /* =========== MENU Bicubic =========== */
                        println("\n ========== Masukkan Bicubic Spline ==========");
                        boolean BicubicMenu = true;
                        BicubicSpline bicubic = new BicubicSpline();
                        boolean isiValid = true;
                        boolean valid = false;
                        String inputText = " >>> Masukkan nama file: ";
                        while (BicubicMenu) {
                            /* =========== INPUT Interpolasi Bicubic Spline =========== */
                            println("Ketik 0 untuk kembali");
                            print(" >>> Masukkan nama file: ");
                            String path = scanner.nextLine();
                            if (path.equals("0")) {
                                BicubicMenu = false;
                            } else if(!textFile(path)){
                                println("File bukan text");
                            } else {
                                File file;
                                Scanner readFile = null;
                                boolean filefound = false;
                                try {
                                    file = new File("../test/bicubic/input/" + path);
                                    readFile = new Scanner(file);
                                    filefound = true;
                                } catch (FileNotFoundException e) {
                                    println("Nama file salah. Contoh nama benar : baby.txt ");
                                }
                                if (filefound) {
                                    inputText += path + "\n";
                                    String line;
                                    int idx = 0;
                                    while (readFile.hasNextLine() && (line = readFile.nextLine()) != null && isiValid) {
                                        String[] saved = line.split(" ");
                                        int len = saved.length;
                                        try {
                                            if (len == 4) {
                                                for (String s : saved) {
                                                    double temp = Double.parseDouble(s);
                                                    bicubic.initF.setElmt(temp, idx, 0);
                                                    idx++;
                                                }
                                            } else {
                                                bicubic.request[0] = Double.parseDouble(saved[0]);
                                                bicubic.request[1] = Double.parseDouble(saved[1]);
                                            }
                                        }
                                        catch (Exception e){
                                            println("Isi File Salah! Pastikan formatnya benar.");
                                            isiValid = false;
                                        }
                                    }
                                    if(isiValid) {
                                        BicubicMenu = false;
                                        valid = true;
                                    }
                                }
                            }
                        }
                        if (isiValid && valid) {
                            inputText += "\n\n ================== HASIL ================== \n";
                            println(" ================== HASIL ================== ");
                            bicubic.solveBicubic();
                            bicubic.getRequestAnswer();
                            println(bicubic.function);
                            boolean menuSimpan = true;
                            while (menuSimpan) {
                                print("\n >>> Apakah ingin disimpan dalam file? [y/n]: ");
                                String opsiSimpan = scanner.nextLine();
                                if (opsiSimpan.equals("y") || opsiSimpan.equals("Y")) {
                                    print(" >>> Masukkan nama file hasil: ");
                                    String name = scanner.nextLine();
                                    while(!textFile(name)){
                                        println("Nama file salah. Contoh nama benar : baby.txt ");
                                        print(" >>> Masukkan nama file hasil: ");
                                        name = scanner.nextLine();
                                    }
                                    bicubic.saveProccessesToText(("../test/bicubic/output/" + name), inputText);
                                    menuSimpan = false;
                                } else if (opsiSimpan.equals("n") || opsiSimpan.equals("N")) {
                                    menuSimpan = false;
                                } else {
                                    println("Masukkan salah pilih y / n");
                                }
                            }
                        }
                    }
                    break;
                    case "Regresi" : {
                        /* =========== MENU Regresi Linier Berganda =========== */
                        boolean RegresiMenu = true;
                        while (RegresiMenu) {
                            Regresi regresi = new Regresi(500, 500);
                            /* =========== INPUT MATRIKS Balikan =========== */
                            println("\n ========== Pilih Metode Masukkan ==========");
                            println("1. Masukkan Ketik");
                            println("2. Masukkan dalam bentuk File");
                            println("3. Kembali");
                            print(" >>> Pilih Metode Masukkan : ");
                            boolean isInputRegresiValid = false;
                            int opsiMenuInputRegresi = -1;
                            try {
                                opsiMenuInputRegresi = Integer.parseInt(scanner.nextLine());
                                isInputRegresiValid = true;
                            } catch (Exception e) {
                                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                            }
                            if (isInputRegresiValid) {
                                boolean terisiMatriks = false;
                                String inputText = "";
                                switch (opsiMenuInputRegresi) {
                                    case 1:
                                        /* ============ Input Ketik =========== */
                                        boolean inputKetik = true;
                                        while (inputKetik) {
                                            inputText = " >>> Masukkan jumlah peubah : ";
                                            println("\n ========== Metode Ketik ==========");
                                            println("Ketik -1 Kembali ");
                                            print(" >>> Masukkan jumlah peubah : ");
                                            int n = -2;
                                            int m = -2;
                                            try {
                                                n = scanner.nextInt();
                                                if (n == -1) {
                                                    inputKetik = false;
                                                } else {
                                                    scanner.nextLine();
                                                    print(" >>> Masukkan jumlah sampel : ");
                                                    m = scanner.nextInt();
                                                    if (m == -1) {
                                                        inputKetik = false;
                                                    }
                                                }
                                            } catch (Exception e) {
                                                println("Jumlah peubah dan sampel harus bilangan bulat positif");
                                            }
                                            scanner.nextLine();
                                            if (n != -2 && m != -2 && m != -1) {
                                                if (n < 1 || m < 1) {
                                                    println("Jumlah peubah dan sampel harus bilangan bulat positif");
                                                } else {
                                                    inputText += n + "\n";
                                                    inputText += " >>> Masukkan jumlah sampel : " + m + "\n";
                                                    inputText += "Masukkan Nilai-Nilai sampel (" + m + " x " + (n+1) + "): \n";
                                                    regresi = new Regresi(m, n);
                                                    println("Masukkan Nilai-Nilai sampel (" + m + " x " + (n+1) + "): ");
                                                    boolean isInputSampleValid = true;
                                                    int i = 0;
                                                    while (i < m && isInputSampleValid) {
                                                        String line = scanner.nextLine();
                                                        String[] saved = line.split(" ");
                                                        try {
                                                            for (int j = 0; j < saved.length; j++) {
                                                                double temp = Double.parseDouble(saved[j]);
                                                                regresi.dataRegresiM.setElmt(temp, i, j);
                                                                inputText += temp + " ";
                                                            }
                                                        } catch (Exception e) {
                                                            println("Isi Matrix salah. Pastikan isi matriks adalah bilangan real");
                                                            isInputSampleValid = false;
                                                        }
                                                        inputText += "\n";
                                                        i++;
                                                    }
                                                    if (isInputSampleValid) {
                                                        inputKetik = false;
                                                        terisiMatriks = true;
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    case 2:
                                        /* ============ Input File =========== */
                                        boolean inputFile = true;
                                        while (inputFile) {
                                            inputText = " >>> Masukkan nama file: ";
                                            println("Ketik 0 untuk kembali");
                                            print(" >>> Masukkan nama file: ");
                                            String path = scanner.nextLine();
                                            if (path.equals("0")) {
                                                inputFile = false;
                                            } else if(!textFile(path)){
                                                println("File bukan text");
                                            } else {
                                                File file;
                                                Scanner readFile = null;
                                                boolean filefound = false;
                                                try {
                                                    file = new File("../test/regresi/input/" + path);
                                                    readFile = new Scanner(file);
                                                    filefound = true;
                                                } catch (FileNotFoundException e) {
                                                    println("Nama file salah. Contoh nama benar : baby.txt ");
                                                }
                                                if (filefound) {
                                                    String line;
                                                    int row = 0;
                                                    boolean varpeubah = false;
                                                    int var = 0;
                                                    boolean isiValid = true;
                                                    while (readFile.hasNextLine() && (line = readFile.nextLine()) != null && isiValid) {
                                                        String[] saved = line.split(" ");
                                                        int len = saved.length;
                                                        if (!varpeubah) {
                                                            var = saved.length;
                                                            varpeubah = true;
                                                        }
                                                        try {
                                                            if (len == var) {
                                                                for (int i = 0; i < len; i++) {
                                                                    double tempdouble = Double.parseDouble(saved[i]);
                                                                    regresi.dataRegresiM.setElmt(tempdouble, row, i);
                                                                }
                                                            } else if (len == var - 1) {
                                                                for (int i = 0; i < len; i++) {
                                                                    double tempdouble = Double.parseDouble(saved[i]);
                                                                    regresi.listnilaivar.setElmt(tempdouble, 0, i);
                                                                }
                                                                regresi.listnilaivar.setColEff(var - 1);
                                                                regresi.listnilaivar.setRowEff(1);
                                                            }
                                                            row++;
                                                        } catch (Exception e){
                                                            println("Isi File Salah! Pastikan format isi file benar!");
                                                            isiValid = false;
                                                        }
                                                    }
                                                    if(isiValid) {
                                                        inputText += path + "\n";
                                                        regresi.dataRegresiM.setColEff(var);
                                                        regresi.dataRegresiM.setRowEff(row - 1);
                                                        inputFile = false;
                                                        terisiMatriks = true;
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    case 3:
                                        RegresiMenu = false;
                                        break;
                                    default:
                                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                }
                                if (terisiMatriks) {
                                    println(" ================== HASIL ================== ");
                                    inputText += "\n\n ================== HASIL ================== \n";
                                    regresi.dataRegresiM.displayMatrix();
                                    println("");
                                    regresi.convertData2Reg(regresi.dataRegresiM);
                                    OBE mEselon = regresi.convertReg2OBE(regresi.regresiM);
                                    regresi.displayFunction(mEselon);
                                    println("");
                                    println("Taksiran = " + regresi.taksiran(regresi.listnilaivar, mEselon) + "\n");
                                    boolean menuSimpan = true;
                                    while (menuSimpan) {
                                        print(" >>> Apakah ingin disimpan dalam file? [y/n]: ");
                                        String opsiSimpan = scanner.nextLine();
                                        if (opsiSimpan.equals("y") || opsiSimpan.equals("Y")) {
                                            print(" >>> Masukkan nama file hasil: ");
                                            String name = scanner.nextLine();
                                            while(!textFile(name)){
                                                println("Nama file salah. Contoh nama benar : baby.txt ");
                                                print(" >>> Masukkan nama file hasil: ");
                                                name = scanner.nextLine();
                                            }
                                            regresi.uploadhasil2File(regresi.taksiran(regresi.listnilaivar, mEselon),mEselon, ("../test/regresi/output/" + name), inputText);
                                            menuSimpan = false;
                                        } else if (opsiSimpan.equals("n") || opsiSimpan.equals("N")) {
                                            menuSimpan = false;
                                        } else {
                                            println("Masukkan salah pilih y / n");
                                        }
                                    }
                                    RegresiMenu = false;
                                }
                            }
                        }
                    }
                    break;
                    case "ImageBSI" : {
                        /* =========== MENU ImageBSI =========== */
                        println("\n ========== MENU Pembesaran Gambar ==========");
                        boolean ImageMenu = true;
                        boolean valid = false;
                        String path = null;
                        String outputPath = null;
                        double skala = 0;
                        while (ImageMenu) {
                            /* =========== INPUT ImageBSI =========== */
                            println("Ketik 0 untuk kembali");
                            print(" >>> Masukkan Nama file sumber: ");
                            path = scanner.nextLine();
                            if (path.equals("0")) {
                                ImageMenu = false;
                            } else {
                                if(imageFile(path)) {
                                    print(" >>> Masukkan nama file output: ");
                                    outputPath = scanner.nextLine();
                                    if (outputPath.equals("0")) {
                                        ImageMenu = false;
                                    } else {
                                        if(imageFile(outputPath)) {
                                            File file;
                                            boolean filefound = false;
                                            try {
                                                file = new File("../test/imgBSI/input/" + path);
                                                new Scanner(file);
                                                filefound = true;
                                            } catch (FileNotFoundException e) {
                                                println("Nama file salah. Contoh nama benar : gambar.jpg ");
                                            }

                                            if (filefound) {
                                                boolean isNotSkalaValid = true;
                                                skala = -1;
                                                while (isNotSkalaValid) {
                                                    print(" >>> Masukkan skala pembesaran gambar: ");
                                                    try {
                                                        skala = scanner.nextDouble();
                                                    } catch (Exception e) {
                                                        println("Skala harus dalam bentuk bilangan real positif");
                                                    }
                                                    scanner.nextLine();
                                                    if (skala != -1 && skala < 0) {
                                                        println("Skala harus dalam bentuk bilangan real positif");
                                                    } else {
                                                        isNotSkalaValid = false;
                                                        ImageMenu = false;
                                                        valid = true;
                                                    }

                                                }
                                            }
                                        }
                                        else {
                                            println("Nama file bukan ciri-ciri suatu gambar pastikan ekstensinya jpg,jpeg,png");
                                        }
                                    }
                                }
                                else {
                                    println("File bukan gambar. Ciri-ciri gambar adalah ekstensinya jpg,jpeg,png");
                                }
                            }
                        }
                        if (valid) {
                            println(" ================== HASIL ================== ");
                            println(" Gambar sedang diproses ... ");
                            println("( Hasil mungkin membutuhkan waktu lama bergantung pada skale)");
                            ImageBSI temp = new ImageBSI(path, outputPath);
                            temp.proccessImage(skala);
                            println(" Gambar Berhasil dibesarkan");
                            println(" Gambar hasil : ../test/imgBSI/output/" + outputPath);
                            //println(" ( Hasil mungkin gagal karena keterbatasan memori )");
                        }
                    }
                    break;
                }
            }
        }
        scanner.close();
    }
}