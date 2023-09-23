import operations.Matrix;

public class Driver {
    public static void main(String[] args) {
        Matrix M = new Matrix(3, 3);
        double[][] mtr = new double[][]{{2,1,3},{4,1,2},{1,0,1}};
        int i, j;
        for (i = 0; i < 3; i++){
            for (j = 0; j < 3; j++){
                M.setElmt(mtr[i][j], i, j);
            }
        }
        M.displayMatrix();
        System.out.println();
        Matrix inversM = new Matrix(3,3);
        inversM.copyMatrix(M.inversMatrix());
        inversM.displayMatrix();
        System.out.println();
        M.displayMatrix();
        System.out.println(x);
    }
}