//import operations.OBE;
import models.SPL;

public class Driver {
    public static void main(String[] args) {
        SPL kont = new SPL();
        kont.inputSPLText();
        kont.spl.gaussAndSolutions();
        //kont.spl.setParameterSolutions();
        //System.out.println("\nSolusi dek \n");
        //for (int i = 0; i < 6; i++){
            //System.out.println(kont.spl.getParameter(i));
        //}
        System.out.print(kont.spl.getStep());
        kont.saveToTextFile("test/StepGauss.txt");
    }

}