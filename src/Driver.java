//import operations.OBE;
import models.DeterminanInvers;
import models.SPL;

public class Driver {
    public static void main(String[] args) {
        SPL temp = new SPL();
        temp.inputSPLText("src/inputSecond.txt");
        temp.spl.obeGaussJordan();
        System.out.println(temp.spl.getStep());
        //DeterminanInvers x = new DeterminanInvers(3,3);
        //double [][] mtr = new double[][]{{1,2,1},{1,2,1},{1,2,3}};
        //for (int i = 0; i < 3; i++){
        //    for (int j = 0; j < 3; j++){
        //        //x.contents.setMElmt(mtr[i][j],i,j);
        //    }
        //}
        //x.inputMatriksFile("xxx");
        //x.addIndentity();
        //x.contents.addAugmentedToStep(1);
        //System.out.println(x.contents.getStep());
        //double det = x.determinanKofaktor();
        //x.CalculateOBE();
        //x.saveToTextFile("test/NewOne.txt");
        //System.out.println(x.contents.getStep());
        //System.out.println(x.result+"\n");
        //for (int i = 0; i < x.countMul; i++){
        //    System.out.println(x.multiply[i]);
        //}
        //System.out.println("\n"+det);
    }
}