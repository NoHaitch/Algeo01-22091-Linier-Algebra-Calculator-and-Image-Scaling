//import operations.OBE;
import models.BicubicSpline;
import models.SPL;

public class Driver {
    public static void main(String[] args) {
        BicubicSpline.setStaticInvX();
        SPL temp = new SPL();
        temp.inputSPLText("test/testCase.txt");
        boolean isDiff = false;
        for (int i = 0; i < 16; i++){
            for (int j = 0; j < 16; j++){
                if (BicubicSpline.invX.getElmt(i, j) != temp.spl.getMElmt(i,j)){
                    isDiff = true;
                }
            }
        }
        System.out.println(isDiff);

        //BicubicSpline.invX.displayMatrix();
        //DeterminanInvers temp = new DeterminanInvers();
        //temp.contents.setAugmented(BicubicSpline.invX);
        //temp.contents.addAugmentedToStep(4);
        //temp.saveToTextFile("test/testCase.txt");
        //System.out.println(temp.contents.getStep());
    }
}