import models.SPL;
import operations.OBE;
import operations.Matrix;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class driverkaidahcramer {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);
        int i,j,sampel = 3;
        int count = 1;
        String line;
        int k;
        System.out.print("Masukkan banyak persamaan: ");
        sampel = scanInt.nextInt();
        SPL temp = new SPL(sampel,sampel + 1);
        for (i = 0; i < sampel; i++){
            System.out.print("Masukkan nilai nilai persamaan ke-" + count + " : ");
            count+= 1;
            line = scan.nextLine();
            String[] saved = line.split(" ");
            for (k = 0; k < saved.length; k++){
                double tempnilai = Double.parseDouble(saved[k]);
                temp.spl.setMElmt(tempnilai, i, k);
            }
        }
        temp.spl.printAugmented();
        System.out.println("==============");
        temp.solveWithCramer(temp.spl);
        temp.listnilaivar.displayMatrix();
        // Matrix newm = new Matrix(temp.spl.getCopyAugmented());
        // newm.displayMatrix();
}   


}
