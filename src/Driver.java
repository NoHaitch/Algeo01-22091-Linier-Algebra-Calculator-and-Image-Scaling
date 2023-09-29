//import operations.OBE;
import models.*;
import operations.*;

import operations.Matrix;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) {
        ImageBSI.setXInvxDMat();
        /*File f = new File("src/bungabeb.png");
        BufferedImage img = null;
        Matrix[] temp = new Matrix[4];
        for (int i = 0; i < 4; i++){
            temp[i] = new Matrix(16, 1);
        }
        try {
            img = ImageIO.read(f);
            System.out.println(img.getWidth()+" -- "+img.getHeight());
            temp = ImageBSI.get16Points(125, 90, img);

        } catch (IOException e) {
            System.out.println(e);
        }
        temp[0].displayMatrix();
        System.out.println("\n");
        temp[1].displayMatrix();
        System.out.println("\n");
        temp[2].displayMatrix();
        System.out.println("\n");
        temp[3].displayMatrix();
        System.out.println("\n");
        Matrix[] hasil = new Matrix[4];
        for (int m = 0; m < 4; m++){
            hasil[m] = ImageBSI.XInvxDMat.multiplyMatrix(temp[m]);
            hasil[m].displayMatrix();
            System.out.println("\n");
        }
        ImageBSI.getFValueOf(1, 1, hasil);
        */
        ImageBSI temp = new ImageBSI("src/img.png","src/kawankuhehe.png");
        temp.proccessImage(2);
    }
}
