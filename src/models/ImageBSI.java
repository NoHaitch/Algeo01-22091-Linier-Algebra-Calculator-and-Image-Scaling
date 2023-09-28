package models;

import operations.Matrix;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Scanner;

public class ImageBSI{
    public static Matrix DMat = new Matrix(16,16);
    public static Matrix X = new Matrix(16, 16);
    public static Matrix XInvxDMat = new Matrix(16, 16);
    public double[] aValue = new double[16];
    public static Matrix Fxy = new Matrix(1, 16);
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
        Fxy.setElmt(1, 0, 0);
        if (BicubicSpline.invX == null){
            BicubicSpline.setStaticInvX();
        }
        X = new Matrix(BicubicSpline.X);
        XInvxDMat = new Matrix(BicubicSpline.invX.multiplyMatrix(DMat));
    }

    public void proccessImage(float scale){
        if ((scale*10)%5 == 0 && scale > 1){
            Scanner input = new Scanner(System.in);
            System.out.println("Input source path Image: ");
            String path = input.nextLine();
            sPath = new String(path);
            System.out.println("Input destination path Image: ");
            path = input.nextLine();
            dPath = new String(path);
            input.close();
            BufferedImage sourceImg = null;
            BufferedImage tempImg = null;
            BufferedImage finalImg = null;

            File imgFile = new File(sPath);

            try {
                sourceImg = ImageIO.read(imgFile);
                int height = sourceImg.getHeight();
                int width = sourceImg.getWidth();
                tempImg = new BufferedImage(width+2, height+2, BufferedImage.TYPE_INT_RGB);
                int fWidth = ((int)(scale*2))*(width-1) - width + 2;
                int fHeight = ((int)(scale*2))*(height-1) - height + 2;
                finalImg = new BufferedImage(fWidth, fHeight, BufferedImage.TYPE_INT_RGB);

                for (int i = 0; i < width; i++){
                    for (int j = 0; j < height; j++){
                        tempImg.setRGB(i+1, j+1, sourceImg.getRGB(i, j));
                    }
                }
                tempImg.setRGB(0, 0, sourceImg.getRGB(0, 0));
                tempImg.setRGB(width+1, 0, sourceImg.getRGB(width-1, 0));
                tempImg.setRGB(0, height+1, sourceImg.getRGB(0, height-1));
                tempImg.setRGB(width+1, height+1, sourceImg.getRGB(width-1, height-1));

                for (int i = 1; i < width+1; i++){
                    tempImg.setRGB(i, 0, tempImg.getRGB(i, 1));
                    tempImg.setRGB(i, height+1, tempImg.getRGB(i, height));
                }
                for (int i = 1; i < height+1; i++){
                    tempImg.setRGB(0, i, tempImg.getRGB(1, i));
                    tempImg.setRGB(width+1, i, tempImg.getRGB(width, i));
                }
                for (int i = 1; i < width; i++){
                    for (int j = 1; j < height; j++){
                        Matrix temp = get16Points(i, j, tempImg);
                        Matrix hasil = X.multiplyMatrix(temp);

                    }
                }

            } catch (IOException e) {
                System.out.println(e);
            }
            try {
                File output = new File(dPath);
                ImageIO.write(finalImg, "jpg",output);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public Matrix get16Points(int i, int j, BufferedImage input){
        Matrix temp = new Matrix(16, 1);
        int idx = 0;
        for (int y = j; y < j+4; y++){
            for (int x = i; x < i+4; x++){
                temp.setElmt((double)input.getRGB(x, y), idx, 0);
            }
        }
        return temp;
    }
}