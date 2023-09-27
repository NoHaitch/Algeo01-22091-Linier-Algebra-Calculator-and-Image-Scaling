import operations.Matrix;
import operations.OBE;
import operations.interpolation;
import models.Point;
import java.util.Scanner;
public class Driver {
    public static void main(String[] args) {
        int i,j;
        Matrix m = interpolation.askDataPoint();
        Matrix newm = interpolation.convertPtoM(m);
        OBE obe = new OBE (newm.getRowEff(), newm.getColEff());
        for (i = 0; i < newm.getRowEff(); i++){
            for (j = 0; j < newm.getColEff();j++){
                obe.setMElmt(newm.getElmt(i,j), i, j);
            }
        }
        obe.gaussAndSolutions();
        interpolation.displayFunction(obe);
        System.out.println();
        System.out.print(interpolation.taksiran(obe,2));
    }
}