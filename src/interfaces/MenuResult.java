package interfaces;

import models.SPL;
import operations.Matrix;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class MenuResult extends JFrame{
    private JPanel mainPanel;
    private JLabel mainLabel;
    private JTextField matrixText;
    private JLabel resultLabel;
    private JTextArea inputText;
    private JTextArea resultText;
    private JLabel inputLabel;

    public MenuResult(String input, String inputType, String type){
        setContentPane(mainPanel);
        setTitle("Main Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(840, 500);
        setVisible(true);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        if(Objects.equals(type, "SPL")){
            SPL SPL = new SPL();
            if(Objects.equals(inputType, "f")){
                String path = input;
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
                            SPL.spl.setMElmt(temp, row, i);
                        }
                        row++;
                    }
                    SPL.spl.setMatrixRow(row);
                    SPL.spl.setMatrixCol(column);
                    readFile.close();
                } catch (FileNotFoundException e){
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                    // TODO: handle exception
                }
                SPL.spl.printAugmented();

            }
        }
    }

    public Matrix getMatrixFromString(Matrix matrix, String input){
        String[] lines = input.split("\n");
        for(int i=0; i<countRowInput(lines); i++){
            String[] line = lines[i].split(" ");
            for(int j=0; j<countRowInput(lines);i++){
                matrix.setElmt(Double.parseDouble(line[j]),i,j);
            }
        }
        return matrix;
    }
    public int countColInput(String[] lines){
        int count = 0;
        for(String line: lines){
            for(String elmt: line.split(" ")){
                count ++;
            }
            break;
        }
        return count;
    }
    public int countRowInput(String[] lines){
        int count = 0;
        for(String line: lines){
            count ++;
        }
        return count;
    }
}
