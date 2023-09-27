import models.DeterminanInvers;
import operations.Matrix;

public class Driver2 {
    public static void main(String[] args) {
        DeterminanInvers m = new DeterminanInvers();
        m.inputMatriksText();
        //Matrix x = new Matrix();
        //x.copyMatrix(m.contents.getCopyAugmented());
        //x.inversMatrix().displayMatrix();
        //System.out.println("\n\n");
        m.inversMatrix().displayMatrix();
    }
}