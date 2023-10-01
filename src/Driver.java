//import operations.OBE;
import models.*;
import operations.*;

public class Driver {
    public static void main(String[] args) {
        ImageBSI.setXInvxDMat();
        ImageBSI temp = new ImageBSI("src/pacarmatt.png", "src/cewematthew.png");
        temp.scaleImage(3.789);
    }
}
