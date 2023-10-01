import models.*;

public class Driver2 {
    public static void main(String[] args) {
        DeterminanInvers temp = new DeterminanInvers();
        temp.inputMatriksFile("src/inputSecond.txt");
        temp.CalculateOBE();
        System.out.println(temp.contents.getStep());
        for (int i = 0; i < temp.countMul; i++){
            System.out.println(temp.multiply[i]);
        }
        System.out.println("\n");
        System.out.println(temp.sign);
        System.out.println(temp.result);
    }
}