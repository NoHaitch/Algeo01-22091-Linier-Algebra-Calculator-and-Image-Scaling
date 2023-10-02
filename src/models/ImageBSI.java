package models;

import operations.Matrix;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;
//import java.util.Scanner;

public class ImageBSI{
    public static Matrix DMat = new Matrix(16,16);
    //public static Matrix X = new Matrix(16, 16);
    public static Matrix XInvxDMat;
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
        if (XInvxDMat == null){
            XInvxDMat = new Matrix(16, 16);
        }
        setDMat();
        if (BicubicSpline.invX == null){
            BicubicSpline.setStaticInvX();
        }
        //X = new Matrix(BicubicSpline.X);
        XInvxDMat = new Matrix(BicubicSpline.invX.multiplyMatrix(DMat));
    }

    // Has better time complexity - Recommended
    // Bad memory complexity
    public void scaleImage(double scale){
        BufferedImage sourceImg = null;
        BufferedImage tempImg = null;
        BufferedImage finalImg = null;
        File imgFile = new File(sPath);
        try {
            sourceImg = ImageIO.read(imgFile);
            int height = sourceImg.getHeight();
            int width = sourceImg.getWidth();
            //System.out.println(" ukuran sekarang "+width+" <---> "+height);
            tempImg = new BufferedImage(width+4, height+4, BufferedImage.TYPE_INT_ARGB);
            for (int i = 0; i < width; i++){
                for (int j = 0; j < height; j++){
                    tempImg.setRGB(i+2, j+2, sourceImg.getRGB(i, j));
                }
            }
            for (int i = 0; i < width+4; i++){
                if (i <= 2){
                    tempImg.setRGB(i, 0, tempImg.getRGB(2, 2));
                    tempImg.setRGB(i, 1, tempImg.getRGB(2, 2));
                    tempImg.setRGB(i, height+2, tempImg.getRGB(2, height+1));
                    tempImg.setRGB(i, height+3, tempImg.getRGB(2, height+1));
                } else if (i >= width + 1){
                    tempImg.setRGB(i, 0, tempImg.getRGB(width+1, 2));
                    tempImg.setRGB(i, 1, tempImg.getRGB(width+1, 2));
                    tempImg.setRGB(i, height+2, tempImg.getRGB(width+1, height+1));
                    tempImg.setRGB(i, height+3, tempImg.getRGB(width+1, height+1));
                } else {
                    tempImg.setRGB(i, 0, tempImg.getRGB(i, 2));
                    tempImg.setRGB(i, 1, tempImg.getRGB(i, 2));
                    tempImg.setRGB(i, height+2, tempImg.getRGB(i, height+1));
                    tempImg.setRGB(i, height+3, tempImg.getRGB(i, height+1));
                }
            }
            for (int j = 2; j < height+2; j++){
                tempImg.setRGB(0, j, tempImg.getRGB(2, j));
                tempImg.setRGB(1, j, tempImg.getRGB(2,j));
                tempImg.setRGB(width+2, j, tempImg.getRGB(width+1, j));
                tempImg.setRGB(width+3, j, tempImg.getRGB(width+1, j));
            }

            // Menyimpan Aij yang mana merupakan nilai hasil interpolasi bicubic yang akan
            // dipakai untuk menghitung kualitas gambar yang baru
            Matrix[][][] Aij = new Matrix[width+1][height+1][4];
            for (int i = 1; i < width+2; i++){
                for (int j = 1; j < height+2; j++){
                    Matrix[] image = get16Points(i, j, tempImg);
                    Aij[i-1][j-1][0] = new Matrix(XInvxDMat.multiplyMatrix(image[0]));
                    Aij[i-1][j-1][1] = new Matrix(XInvxDMat.multiplyMatrix(image[1]));
                    Aij[i-1][j-1][2] = new Matrix(XInvxDMat.multiplyMatrix(image[2]));
                    Aij[i-1][j-1][3] = new Matrix(XInvxDMat.multiplyMatrix(image[3]));
                }
            }
            // Ukuran baru untuk keluaran Image
            int fWidth = (int)(width*scale);
            int fHeight = (int)(height*scale);
            //System.out.println(" ukuran sekarang "+fWidth+" <---> "+fHeight);
            
            finalImg = new BufferedImage(fWidth, fHeight, BufferedImage.TYPE_INT_ARGB);

            for (int i = 0; i < fWidth; i++){
                for (int j = 0; j < fHeight; j++){
                    // calculate the scale position of every pixel
                    double xcon = (double)((1 + (2*i+1)*(double)width/(double)fWidth)/2);
                    double ycon = (double)((1 + (2*j+1)*(double)height/(double)fHeight)/2);

                    // calculate the following solution, which Aij should be used
                    double Ai = Math.floor(xcon);
                    double Aj = Math.floor(ycon);

                    // calculate the xtrace, ytrace used for f(xtrace,ytrace), 0 < xtrace, ytrace < 1
                    double xtrace = xcon - Ai;
                    double ytrace = ycon - Aj;
                    //System.out.println("x: "+xcon+", y: "+ycon);
                    int rgb = getFValueOf(xtrace, ytrace, Aij[(int)Ai][(int)Aj]);

                    finalImg.setRGB(i, j, rgb);
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            File output = new File(dPath);
            ImageIO.write(finalImg, "png",output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Time complexity and memory complexity are both better
    // Most Recommended 
    public void proccessImage(double scale){
        BufferedImage sourceImg = null;
        BufferedImage tempImg = null;
        BufferedImage finalImg = null;
        File imgFile = new File(sPath);
        try {
            sourceImg = ImageIO.read(imgFile);
            int height = sourceImg.getHeight();
            int width = sourceImg.getWidth();
            //System.out.println(" ukuran sekarang "+width+" <---> "+height);
            tempImg = new BufferedImage(width+4, height+4, BufferedImage.TYPE_INT_ARGB);
            for (int i = 0; i < width; i++){
                for (int j = 0; j < height; j++){
                    tempImg.setRGB(i+2, j+2, sourceImg.getRGB(i, j));
                }
            }
            for (int i = 0; i < width+4; i++){
                if (i <= 2){
                    tempImg.setRGB(i, 0, tempImg.getRGB(2, 2));
                    tempImg.setRGB(i, 1, tempImg.getRGB(2, 2));
                    tempImg.setRGB(i, height+2, tempImg.getRGB(2, height+1));
                    tempImg.setRGB(i, height+3, tempImg.getRGB(2, height+1));
                } else if (i >= width + 1){
                    tempImg.setRGB(i, 0, tempImg.getRGB(width+1, 2));
                    tempImg.setRGB(i, 1, tempImg.getRGB(width+1, 2));
                    tempImg.setRGB(i, height+2, tempImg.getRGB(width+1, height+1));
                    tempImg.setRGB(i, height+3, tempImg.getRGB(width+1, height+1));
                } else {
                    tempImg.setRGB(i, 0, tempImg.getRGB(i, 2));
                    tempImg.setRGB(i, 1, tempImg.getRGB(i, 2));
                    tempImg.setRGB(i, height+2, tempImg.getRGB(i, height+1));
                    tempImg.setRGB(i, height+3, tempImg.getRGB(i, height+1));
                }
            }
            for (int j = 2; j < height+2; j++){
                tempImg.setRGB(0, j, tempImg.getRGB(2, j));
                tempImg.setRGB(1, j, tempImg.getRGB(2,j));
                tempImg.setRGB(width+2, j, tempImg.getRGB(width+1, j));
                tempImg.setRGB(width+3, j, tempImg.getRGB(width+1, j));
            }

            // Ukuran baru untuk keluaran Image
            int fWidth = (int)(width*scale);
            int fHeight = (int)(height*scale);
            //System.out.println(" ukuran sekarang "+fWidth+" <---> "+fHeight);
            Matrix[][] aUse = new Matrix[height+1][4];
            int currentX = -1;
            int currentY = -1;
            
            finalImg = new BufferedImage(fWidth, fHeight, BufferedImage.TYPE_INT_ARGB);

            for (int i = 0; i < fWidth; i++){
                for (int j = 0; j < fHeight; j++){
                    // calculate the scale position of every pixel
                    double xcon = (double)((1 + (2*i+1)*(double)width/(double)fWidth)/2);
                    double ycon = (double)((1 + (2*j+1)*(double)height/(double)fHeight)/2);

                    // calculate the following solution, which Aij should be used
                    double Ai = Math.floor(xcon);
                    double Aj = Math.floor(ycon);

                    if (Ai > (double)currentX){
                        currentX = (int)Ai;
                        currentY = -1;
                    }

                    if (Aj > (double)currentY){
                        currentY = (int)Aj;
                        Matrix []image = get16Points((int)(Ai+1), (int)(Aj+1), tempImg);
                        aUse[currentY][0] = new Matrix(XInvxDMat.multiplyMatrix(image[0]));
                        aUse[currentY][1] = new Matrix(XInvxDMat.multiplyMatrix(image[1]));
                        aUse[currentY][2] = new Matrix(XInvxDMat.multiplyMatrix(image[2]));
                        aUse[currentY][3] = new Matrix(XInvxDMat.multiplyMatrix(image[3]));
                    }

                    // calculate the xtrace, ytrace used for f(xtrace,ytrace), 0 < xtrace, ytrace < 1
                    double xtrace = xcon - Ai;
                    double ytrace = ycon - Aj;
                    //System.out.println("x: "+xcon+", y: "+ycon);
                    int rgb = getFValueOf(xtrace, ytrace, aUse[(int)Aj]);

                    finalImg.setRGB(i, j, rgb);
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            File output = new File(dPath);
            ImageIO.write(finalImg, "png",output);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static Matrix[] get16Points(int i, int j, BufferedImage input){
        Matrix[] temp = new Matrix[4];
        for (int q = 0; q < 4; q++){
            temp[q] = new Matrix(16, 1);
        }
        int idx = 0;
        for (int y = j-1; y < j+3; y++){
            for (int x = i-1; x < i+3; x++){
                double[] rgb = getColorRGB(input.getRGB(x, y));
                temp[0].setElmt(rgb[0], idx, 0);
                temp[1].setElmt(rgb[1], idx, 0);
                temp[2].setElmt(rgb[2], idx, 0);
                temp[3].setElmt(rgb[3], idx, 0);
                idx++;
            }
        }
        return temp;
    }

    public static double[] getColorRGB (int in){
        double[] temp = new double[4];
        temp[0] = (double)((in >> 24) & 0xff);
        temp[1] = (double)((in & 0xff0000) >> 16);
        temp[2] = (double)((in & 0xff00) >> 8);
        temp[3] = (double)(in & 0xff);
        return temp;
    }

    public static int getFValueOf(double x, double y, Matrix[] a){
        int idx = 0;
        double alpha = 0,red = 0, green = 0, blue = 0;
        int al, r, g ,b;
        for (int j = 0; j < 4; j++){
            for (int i = 0; i < 4; i++){
                alpha += a[0].getElmt(idx, 0)*Math.pow(x,i)*Math.pow(y,j);
                red += a[1].getElmt(idx, 0)*Math.pow(x,i)*Math.pow(y,j);
                green += a[2].getElmt(idx, 0)*Math.pow(x,i)*Math.pow(y,j);
                blue += a[3].getElmt(idx, 0)*Math.pow(x,i)*Math.pow(y,j);
                idx++;
            }
        }
        al = (int)alpha;
        r = (int)red;
        g = (int)green;
        b = (int)blue;
        int rgb = (al << 24) | (r << 16) | (g << 8) | b ;
        return rgb;
    }
}