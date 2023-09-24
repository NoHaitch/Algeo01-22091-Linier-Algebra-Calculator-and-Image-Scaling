import operations.OBE;

public class Driver {
    public static void main(String[] args) {
        OBE M = new OBE(4, 7);
        double[][] mtr = new double[][]{{1,3,-2,0,2,0,0},{2,6,-5,-2,4,-3,-1},{0,0,5,10,0,15,5},{2,6,0,8,4,18,6}};
        int i, j;
        for (i = 0; i < 4; i++){
            for (j = 0; j < 7; j++){
                M.setMElmt(mtr[i][j], i, j);
            }
        }
        M.obeGaussJordan();
        System.out.println(M);
    }
}