//import operations.OBE;
import models.*;
import operations.*;

public class Driver {
    public static void main(String[] args) {
        ImageBSI.setXInvxDMat();
        ImageBSI temp = new ImageBSI("src/bungabeb.png", "src/kenny.png");
        temp.proccessImage(3.215);
    }
}
