import operations.Matrix;
import operations.OBE;
import models.regresi;
import models.Point;
import models.SPL;
import java.util.Scanner;
public class driverregresi {
    public static void main(String[] args){
        int i,j;
        Scanner scan = new Scanner(System.in);

        // menerima input dari file
        String path;
        regresi reg = new regresi(1000,501);
        System.out.print("Masukkan path: ");
        path = scan.nextLine();
        reg.askDataRegFromFile(path);
        reg.dataRegresiM.displayMatrix();
        reg.convertData2Reg(reg.dataRegresiM);
        reg.regresiM.displayMatrix();
        OBE meselon = reg.convertReg2OBE(reg.regresiM);
        meselon.printAugmented();
        meselon.gaussAndSolutions();
        reg.displayFunction(meselon);
        System.out.println("");
        reg.taksiran(reg.listnilaivar, meselon);
        reg.listnilaivar.displayMatrix();
        
        // menerima input dari keyboard
        // System.out.print("Masukkan banyak variabel bebas: ");
        // int var = scan.nextInt();

        // System.out.print("Masukkan banyak sampel: ");
        // int sampel = scan.nextInt();

        // regresi reg = new regresi(sampel,var);
        // System.out.println();
        // reg.askDatareg(sampel, var);
        // reg.dataRegresiM.displayMatrix();
        // reg.convertData2Reg(reg.dataRegresiM);
        // System.out.println();
        // reg.regresiM.displayMatrix();
        // OBE meselon = reg.convertReg2OBE(reg.regresiM);
        // meselon.gaussAndSolutions();
        // meselon.printAugmented();
    }
}
