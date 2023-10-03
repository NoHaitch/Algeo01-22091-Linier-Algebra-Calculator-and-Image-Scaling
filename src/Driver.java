//import operations.OBE;
import models.*;
import operations.*;

public class Driver {
    public static void main(String[] args) {
        SPL temp = new SPL();
        temp.inputSPLText();
        temp.solveWithInverse();
        System.out.println(temp.spl.getStep());
        temp.saveToTextFile("test/spl/output/testOutput.txt","");
    }
}
