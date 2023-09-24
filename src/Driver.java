//import operations.OBE;
import models.SPL;

public class Driver {
    public static void main(String[] args) {
        SPL now = new SPL();
        now.inputSPLText();
        now.spl.obeGaussJordan();
        String path = "test/Penyelesaian_Mas.txt";
        now.saveToTextFile(path);
    }
}