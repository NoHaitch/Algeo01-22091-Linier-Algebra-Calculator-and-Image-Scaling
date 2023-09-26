import models.Determinan;

import java.util.Scanner;

public class Main {
    public static void println(String str){
        System.out.println(str);
    }
    public static void print(String str){
        System.out.print(str);
    }

    public static void main(String[] args) {
        println("\n***** =============== APLIKASI MULAI =============== *****");
        println("==== Selamat Datang pada Aplikasi Perhitungan Matriks ====");
        Scanner scanner = new Scanner(System.in);
        boolean program = true;
        while(program) {
            println("");
            println("   ===== Menu =====");
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
                        boolean determinanMenu = true;
                        while(determinanMenu){
                            println("   ===== Pilih Metode Determinan =====");
                            println("1. Metode Ekspansi Kofaktor");
                            println("2. Metode Reduksi Baris dengan OBE");
                            println("3. Kembali");
                            print(" >>> Pilih Metode Determinan : ");
                            try{
                                int opsiMenuDeterminan = Integer.parseInt(scanner.nextLine());
                                switch (opsiMenuDeterminan){
                                    case 1:
                                        boolean inputMenu = true;
                                        Determinan determinan = new Determinan();
                                        while(inputMenu) {
                                            println("   ===== Pilih Metode Masukkan =====");
                                            println("1. Masukkan dengan Ketik");
                                            println("2. Masukkan dalam bentuk File");
                                            println("3. Kembali");
                                            print(" >>> Pilih Metode Masukkan : ");
                                            try {
                                                int opsiMenuInput = Integer.parseInt(scanner.nextLine());
                                                switch (opsiMenuInput) {
                                                    case 1:
                                                        //determinan.inputMatriksKetik();
                                                        break;
                                                    case 2:
                                                        determinan.inputMatriksFile();
                                                        println("\n ===== Determinan =   " + determinan.determinanKofaktor() + " ===== ");
                                                        scanner.nextLine();
                                                        inputMenu = false;
                                                        determinanMenu = false;
                                                        break;
                                                    case 3:
                                                        inputMenu = false;
                                                        break;
                                                    default:
                                                        println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                                }
                                            } catch (Exception InputMismatchException) {
                                                println("Masukkan salah. Silahkan memilih angka menu antara 1 dan 3");
                                            }
                                        }
                                        break;
                                    case 2: break;
                                    case 3:
                                        determinanMenu = false;
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
