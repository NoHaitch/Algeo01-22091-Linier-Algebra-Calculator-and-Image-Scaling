import operations.Matrix;
import operations.OBE;
import models.regresi;
import models.Point;
import models.SPL;
import java.util.Scanner;
public class driverregresi {
    public static void main(String[] args) {
        int jumlahPeubah, banyaksampel;
        Scanner scan = new Scanner(System.in);
        System.out.print("Masukkan banyak variabel bebas: ");
        int var = scan.nextInt();

        System.out.print("Masukkan banyak sampel: ");
        int sampel = scan.nextInt();

        regresi reg = new regresi(sampel,var);
        System.out.println();
        reg.askDatareg(sampel, var);
        reg.dataRegresiM.displayMatrix();
        reg.convertData2Reg(reg.dataRegresiM);
        System.out.println();
        reg.regresiM.displayMatrix();
    }
}
