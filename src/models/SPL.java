package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import operations.OBE;

public class SPL {
    private OBE spl;
    
    public SPL(){
        this(0,0);
    }
    public SPL(int row, int col){
        this.spl = new OBE(row, col);
    }

    public OBE getSPL(){
        return new OBE(this.spl);
    }

    public void inputSPLText(){
        String path;
        Scanner input = new Scanner (System.in);
        System.out.print("Masukkan source file: ");
        path = input.nextLine();
        try {
            File inputFile = new File(path);
            Scanner readFile = new Scanner(inputFile);
            String line;
            int row = 0;
            int column = 0;
            while (readFile.hasNextLine() && (line = readFile.nextLine()) != null){
                String[] saved = line.split(" ");
                if (row == 0){
                    column = saved.length;
                }
                int i;
                for (i = 0; i < column; i++){
                    double temp = Double.parseDouble(saved[i]);
                    spl.setMElmt(temp, row, i);
                }
                row++;
            }
            spl.setMatrixRow(row);
            spl.setMatrixCol(column);
            readFile.close();
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            // TODO: handle exception
        }
        input.close();
    }


}
