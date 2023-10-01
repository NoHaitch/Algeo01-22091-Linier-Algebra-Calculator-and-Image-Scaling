//import operations.OBE;
import models.*;
import operations.*;

public class Driver {
    public static void main(String[] args) {
        ImageBSI.setXInvxDMat();
        ImageBSI temp = new ImageBSI("src/kucing.jpg", "test/test1.png");
        temp.proccessImage(4.789);
    }
}
