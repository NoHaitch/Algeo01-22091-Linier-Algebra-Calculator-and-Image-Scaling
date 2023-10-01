//import operations.OBE;
import models.*;
import operations.*;

public class Driver {
    public static void main(String[] args) {
        ImageBSI temp = new ImageBSI("src/kucing.jpg", "test/cat.png");
        //BicubicSpline.setStaticInvX();
        //BicubicSpline.invX.displayMatrix();
        ImageBSI.setXInvxDMat();
        //ImageBSI.XInvxDMat.displayMatrix();
        temp.scaleImage(1.5);
    }
}
