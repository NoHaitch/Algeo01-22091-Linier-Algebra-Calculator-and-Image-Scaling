package models;

import operations.Matrix;

public class ImageBSI{
    public static Matrix DMat = new Matrix(16,16);

    public static void setDMat(){
        double[][] var = new double[][]{{0,0},{1,0},{0,1},{1,1}};
        int[] rows = new int[]{0,4,8,12};
        int row, idx;
        for (int x : rows){
            for (double[] arr : var){
                idx = 0;
                for (int j = -1; j < 3; j++){
                    for (int i = -1; i < 3; i++){
                        row = x + (int)(arr[0] + arr[1]*2);
                        if (x == 0){
                            if (arr[0] == i && arr[1] == j){
                                DMat.setElmt(4, row, idx);
                            }
                        } else if (x == 4){
                            if (arr[0]+1 == i && arr[1] == j){
                                DMat.setElmt(2, row, idx);
                            } else if (arr[0]-1 == i && arr[1] == j){
                                DMat.setElmt(-2, row, idx);
                            }
                        } else if (x == 8){
                            if (arr[0] == i && arr[1] + 1 == j){
                                DMat.setElmt(2, row, idx);
                            } else if (arr[0] == i && arr[1] - 1 == j){
                                DMat.setElmt(-2, row, idx);
                            }
                        } else {
                            if (arr[0]+1 == i && arr[1]+1 == j){
                                DMat.setElmt(1, row, idx);
                            } else if (arr[0] == i && arr[1] - 1 == j){
                                DMat.setElmt(-1, row, idx);
                            } else if (arr[0]-1 == i && arr[1] == j){
                                DMat.setElmt(-1, row, idx);
                            } else if (arr[0] == i && arr[1] == j){
                                DMat.setElmt(1, row, idx);
                            }
                        }
                        idx++;
                    }
                }
            }
        }
    }
}