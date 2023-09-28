package models;

import operations.Matrix;

public class ImageBSI{
    public static Matrix DMat = new Matrix(16,16);
    //public static Matrix XInv = new Matrix(16, 16);
    public static Matrix XInvxDMat = new Matrix(16, 16);
    public double[] aValue = new double[16];
    public String sPath;
    public String dPath;
    //if(XInv == null){
    //    BicubicSpline.setStaticInvX();
    //    XInv = new Matrix(BicubicSpline.invX);
    //}
    public ImageBSI(){
        this("","");
    }

    public ImageBSI(String sPath, String dPath){
        this.sPath = sPath;
        this.dPath = dPath;
    }

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
                                DMat.setElmt(1, row, idx);
                            }
                        } else if (x == 4){
                            if (arr[0]+1 == i && arr[1] == j){
                                DMat.setElmt(0.5, row, idx);
                            } else if (arr[0]-1 == i && arr[1] == j){
                                DMat.setElmt(-0.5, row, idx);
                            }
                        } else if (x == 8){
                            if (arr[0] == i && arr[1] + 1 == j){
                                DMat.setElmt(0.5, row, idx);
                            } else if (arr[0] == i && arr[1] - 1 == j){
                                DMat.setElmt(-0.5, row, idx);
                            }
                        } else {
                            if (arr[0]+1 == i && arr[1]+1 == j){
                                DMat.setElmt(0.25, row, idx);
                            } else if (arr[0] == i && arr[1] - 1 == j){
                                DMat.setElmt(-0.25, row, idx);
                            } else if (arr[0]-1 == i && arr[1] == j){
                                DMat.setElmt(-0.25, row, idx);
                            } else if (arr[0] == i && arr[1] == j){
                                DMat.setElmt(0.25, row, idx);
                            }
                        }
                        idx++;
                    }
                }
            }
        }
    }

    public static void setXInvxDMat(){
        setDMat();
        if (BicubicSpline.invX == null){
            BicubicSpline.setStaticInvX();
        }
        XInvxDMat = new Matrix(BicubicSpline.invX.multiplyMatrix(DMat));
    }
}