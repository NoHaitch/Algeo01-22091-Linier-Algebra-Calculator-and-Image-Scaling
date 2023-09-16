package algeo;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

class MatrixType {
    Double[][] matrix = new Double[1000][1000];
    Double[] value = new Double[1000];
    String[] parameterSolution = new String[1000];
    Double[] solution = new Double[1000];
    int row;
    int col;

    MatrixType(){
        this.matrix = new Double[1000][1000];
        this.value = new Double[1000];
        this.parameterSolution = new String[1000];
        this.solution = new Double[1000];
        this.row = 0;
        this.col = 0;
    }

    void inputSPLText(){
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
                    column = saved.length - 1;
                }
                int i;
                for (i = 0; i < column; i++){
                    this.matrix[row][i] = Double.parseDouble(saved[i]);
                }
                this.value[row] = Double.parseDouble(saved[col]);
                row++;
            }
            this.row = row;
            this.col = column;
        } catch (FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            // TODO: handle exception
        }
    }
    void printAugmentedMatrix(){
        int i, j;
        for (i = 0; i < this.row; i++){
            for (j = 0; j < this.col; j++){
                System.out.printf("%.4f",this.matrix[i][j]);
                if (j != this.col-1){
                    System.out.print(" ");
                }
            }
            System.out.printf(" | %.4f", this.value[i]);
            if (i != row - 1){
                System.out.println();
            }
        }
    }
}
