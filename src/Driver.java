import operations.OBE;

public class Driver {
    public static void main(String[] args) {
        OBE M = new OBE(3, 4);
        double[][] mtr = new double[][]{{0,1,4,4},{0,1,4,4},{0,2,4,2}};
        int i, j;
        for (i = 0; i < 3; i++){
            for (j = 0; j < 4; j++){
                M.setMElmt(mtr[i][j], i, j);
            }
        }
        M.obeGaussJordan();
        System.out.println(M);
    }
}