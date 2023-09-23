import operations.Matrix;

public class Driver {
    public static void main(String[] args) {
        Matrix M = new Matrix(3, 3);
        double[][] mtr = new double[][]{{2,1,4},{1,1,1},{2,0,1}};
        int i, j;
        for (i = 0; i < 3; i++){
            for (j = 0; j < 3; j++){
                M.setElmt(mtr[i][j], i, j);
            }
        }
        M.displayMatrix();
        System.out.println();
        Matrix b = new Matrix(3, 1);
        b.setElmt(12, 0, 0);
        b.setElmt(2, 1, 0);
        b.setElmt(5, 2, 0);
        M.copyMatrix(M.inversMatrix());
        M.displayMatrix();
        System.out.println();
        b.displayMatrix();
        System.out.println();
        b.copyMatrix(M.multiplyMatrix(b));
        b.displayMatrix();
    }
}