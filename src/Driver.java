//import operations.OBE;
import models.*;
import operations.*;

public class Driver {
    public static void main(String[] args) {
        ImageBSI temp = new ImageBSI("src/img.png", "test/cobacoba.png");
        BicubicSpline.setStaticInvX();
        BicubicSpline.invX.displayMatrix();
        ImageBSI.setXInvxDMat();
        ImageBSI.XInvxDMat.displayMatrix();
        temp.scaleImage(8);
    }
}
