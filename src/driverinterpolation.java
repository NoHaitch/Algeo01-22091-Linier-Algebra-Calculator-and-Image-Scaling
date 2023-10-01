import models.Interpolation;

import java.util.Scanner;
public class driverinterpolation {
    public static void main(String[] args) {
        int i,j;
        String path; 
        Scanner input = new Scanner (System.in);
        System.out.print("Masukkan source file: ");
        path = input.nextLine();
        Interpolation temp = new Interpolation();
        temp.askDataPointFromFile(path);
        System.out.println();
        System.out.println("Taksiran: " +temp.xRequest);
        // Matrix mdata = temp.askDataPoint();
        // Matrix mdata2 = temp.convertPtoM(mdata);
        // OBE meselon = new OBE (mdata2.getRowEff(), mdata2.getColEff());
        // for (i = 0; i < mdata2.getRowEff(); i++){
        //     for (j = 0; j < mdata2.getColEff();j++){
        //         meselon.setMElmt(mdata2.getElmt(i,j), i, j);
        //     }
        // }
        // meselon.gaussAndSolutions();
        // temp.displayFunction(meselon);
        // System.out.println();
        // int xRequest = 2;
        // System.out.println("Taksiran: "+temp.taksiran(meselon,xRequest));
    }
}
