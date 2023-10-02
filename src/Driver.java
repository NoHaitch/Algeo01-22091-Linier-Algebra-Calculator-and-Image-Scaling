//import operations.OBE;
import models.*;
import operations.*;

public class Driver {
    public static void main(String[] args) {
        SPL temp = new SPL();
        temp.inputSPLText();
        temp.spl.obeGaussJordan();
        System.out.println(temp.spl.getStep());
    }
}
