import operations.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // menu
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        Matrix M = new Matrix(row,col);
        M.readMatrix(row,col);
        OBE MOBE = new OBE(M);
        MOBE.operasiOBE();
        MOBE.displayMatrix();
        MOBE.displaySolusi();
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
