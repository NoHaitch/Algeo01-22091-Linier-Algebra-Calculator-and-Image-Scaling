//import operations.OBE;
import models.Determinan;

public class Driver {
    public static void main(String[] args) {
        Determinan x = new Determinan(3,3);
        x.inputMatriksText();
        double det = x.determinanKofaktor();
        x.CalculateOBE();
        x.saveToTextFile("test/DeterminanDuluGan.txt");
        System.out.println(x.contents.getStep());
        System.out.println(x.result+"\n");
        for (int i = 0; i < x.countMul; i++){
            System.out.println(x.multiply[i]);
        }
        System.out.println("\n"+det);
    }
}