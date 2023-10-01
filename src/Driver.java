//import operations.OBE;
import models.*;
import operations.*;

public class Driver {
    public static void main(String[] args) {
        ImageBSI.setXInvxDMat();
        ImageBSI temp = new ImageBSI("src/waifumatt.jpg", "src/cewematthew.png");
        temp.scaleImage(1.789);
    }
}
