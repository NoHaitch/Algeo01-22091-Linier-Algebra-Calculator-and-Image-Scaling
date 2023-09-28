//import operations.OBE;
import models.*;
import operations.*;

public class Driver {
    public static void main(String[] args) {
        ImageBSI.setXInvxDMat();
        ImageBSI.Fxy.displayMatrix();
        System.out.println("\n\n");
        ImageBSI.X.displayMatrix();
    }
}