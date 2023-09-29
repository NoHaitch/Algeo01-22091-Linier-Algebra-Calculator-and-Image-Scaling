//import operations.OBE;
import models.*;
import operations.*;

public class Driver {
    public static void main(String[] args) {
        ImageBSI temp = new ImageBSI("src/gauss.jpeg", "test/gaussScaled.png");
        //BicubicSpline.setStaticInvX();
        //BicubicSpline.invX.displayMatrix();
        ImageBSI.setXInvxDMat();
        //ImageBSI.XInvxDMat.displayMatrix();
        temp.scaleImage(9.93);
    }
}
