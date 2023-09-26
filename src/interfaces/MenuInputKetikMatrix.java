package interfaces;

import operations.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInputKetikMatrix extends JFrame{
    private String input;
    protected Matrix M = new Matrix();
    private JPanel mainPanel;
    private JTextField colInput;
    private JTextField rowInput;
    private JTextArea matrixInput;
    private JButton calculateButton;
    private JButton kembaliButton;
    private JPanel buttonPanel;
    private JLabel mainLabel;
    private JLabel sizeLabel;
    private JLabel matrixLabel;
    private JPanel sizePanel;

    public MenuInputKetikMatrix(String lastMenu){
        setContentPane(mainPanel);
        setTitle("Main Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setSize(840, 500);
        setVisible(true);
        matrixInput.setText("1 1\n1 2");

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        kembaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuInputMatrix menu = new MenuInputMatrix(lastMenu);
            }
        });
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean sizeValid = false;
                int rowEff = 0;
                int colEff = 0;
                try {
                    rowEff = Integer.parseInt(rowInput.getText());
                    colEff = Integer.parseInt(colInput.getText());
                    sizeValid = true;
                    M.setRowEff(rowEff);
                    M.setColEff(colEff);
                    System.out.println("Row Input : " + M.getRowEff() + " ,Col Input : " + M.getColEff());
                }
                catch (NumberFormatException nfe){
                    ErrorPopOut popOut = new ErrorPopOut("Masukkan salah format","<html>Ukuran Matriks harus dalam bentuk bilangan bulat<br>(pastikan tidak ada spasi di akhir angka)</html>");
                }
                if(sizeValid){
                    String inputText = matrixInput.getText();
                    String[] lines = inputText.split("\n");
                    if(countElmtInput(lines) == (M.getRowEff() * M.getColEff()) && M.getColEff() == countColInput(lines) && M.getRowEff() == countRowInput(lines)) {
                        dispose();
                        MenuResult menuResult = new MenuResult(inputText,"k","SPL");
                    }else {
                        ErrorPopOut popOut = new ErrorPopOut("Ukuran Matriks dan masukan isi berbeda jumlahnya","Masukan Matriks harus sesuai dengan ukurannya");
                    }
                }
            }
        });
    }

    public Matrix getMatrix(){
        /* Prekondisi Input ketik berhasil dan Matriks bernilai */
        /* Mengembalikan Matriks hasil input ketik*/
        return M;
    }

    public void setString(String input){
        this.input = input;
    }

    public String getString(){
        return input;
    }

    public int countElmtInput(String[] lines){
        int count = 0;
        for(String line: lines){
            for(String elmt: line.split(" ")){
                count ++;
            }
        }
        return count;
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