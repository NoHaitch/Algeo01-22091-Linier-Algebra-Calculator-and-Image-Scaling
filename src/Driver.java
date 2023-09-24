//import operations.OBE;
import models.SPL;

public class Driver {
    public static void main(String[] args) {
        SPL problem = new SPL();
        problem.inputSPLText();
        problem.getSPL().printAugmented();
        problem.getSPL().obeGauss();
        System.out.println("\nReturned dek\n");
        problem.getSPL().printAugmented();
    }
}