import models.DeterminanInvers;
import operations.Matrix;
import operations.OBE;

public class Driver2 {
    public static void main(String[] args) {
        DeterminanInvers m = new DeterminanInvers();
        m.inputMatriksText();
        //Matrix x = new Matrix();
        //x.copyMatrix(m.contents.getCopyAugmented());
        //x.inversMatrix().displayMatrix();
        //System.out.println("\n\n");
        m.inversMatrix().displayMatrix();
        DeterminanInvers temp = new DeterminanInvers(m);
        temp.contents.setAugmented(m.inversMatrix());
        temp.contents.addAugmentedToStep(4);
        temp.saveToTextFile("test/MatrixForBicubic.txt");
    }
}