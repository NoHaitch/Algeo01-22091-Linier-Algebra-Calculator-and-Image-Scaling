//import operations.OBE;
import models.*;
import operations.*;

public class Driver {
    public static void main(String[] args) {
        ImageBSI.setXInvxDMat();
        ImageBSI temp = new ImageBSI("src/rgbTest3.png", "src/test3.png");
        temp.proccessImage(200);
    }
}
