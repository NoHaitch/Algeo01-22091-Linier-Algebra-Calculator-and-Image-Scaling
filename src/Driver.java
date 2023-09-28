//import operations.OBE;
import models.*;
import operations.*;

public class Driver {
    public static void main(String[] args) {
        //ImageBSI.setDMat();
        //DeterminanInvers det = new DeterminanInvers(16,16);
        //det.inputMatriksFile("src/operations/D.txt");
        //ImageBSI.DMat.displayMatrix();
        //boolean adaBeda = false;
        //for (int i = 0; i < 16; i++){
        //    for (int j = 0; j < 16; j++){
        //        if (ImageBSI.DMat.getElmt(i, j) != det.contents.getMElmt(i, j)){
        //            adaBeda = true;
        //            System.out.println(i+1 + " " + (j+1));
        //        }
        //    }
        //}
        //System.out.println(adaBeda);
        ImageBSI.setXInvxDMat();
        Matrix temp = new Matrix(ImageBSI.XInvxDMat);
        temp.multiplyByConst(16);
        temp.displayMatrix();

    }
}